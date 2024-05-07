package com.xworkz.realestatemanagement.controller;

import com.xworkz.realestatemanagement.dto.*;
import com.xworkz.realestatemanagement.entity.PropertyEntity;
import com.xworkz.realestatemanagement.resorce.OtpGenerator;
import com.xworkz.realestatemanagement.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class RealEstateManagementController {
    @Autowired
    private RealestateManagementService service;
    @Autowired
    EmailSend emailSend;

    @GetMapping("/home")
    public String home() {
        return "index.jsp";
    }

    @GetMapping("/homeToRegister")
    public String toRegister() {
        return "register.jsp";
    }

    @GetMapping("/homeToLogin")
    public String toLogin() {
        return "login.jsp";
    }

    @PostMapping("/creatingRealEstate")
    public String createRealEstate(@ModelAttribute RegisterDto dto, HttpServletRequest req) {
        if (service.validateSaveRegisterInfo(dto)){
            req.setAttribute("message", "Congratulations! Your registration was successful. Log in to view your Profile.");
            return "mesg.jsp";
        }else {
            req.setAttribute("message", "Please try again");
            return "mesg.jsp";
        }

    }

    LocalDateTime otpGenerationTime;
    String emailToOTp;

    @PostMapping("/generateOTP")
    public String getEmailToGetOtp(@RequestParam("email") String email, HttpServletRequest request) {
        if (service.validateToGetEmail(email)) {
            String otp = OtpGenerator.generateOTP();
            emailSend.mailSend(email, otp);
            service.validateUpdateOTPByEmail(otp, email);
            this.otpGenerationTime = LocalDateTime.now();
            this.emailToOTp = email;
            request.setAttribute("mail", email);
            return "Otp.jsp";
        } else {
            request.setAttribute("msg", "Yor Account is blocked Contact Service Provider");
            return "login.jsp";
        }
    }

    int logIn;
    int count = 0;
    @PostMapping("/SetOTP")
    public String loginToProfile(@RequestParam("generatedOtp") String generatedOtp, HttpServletRequest request) {
        if (count < 3) {
            if (LocalDateTime.now().isBefore(otpGenerationTime.plusMinutes(2))) {
                if (generatedOtp.equals(service.validateGetOtpByEmail(emailToOTp))) {
                    RegisterDto dto = service.validateGetInfoByEmail(emailToOTp);
                    this.logIn = dto.getRid();
                    System.out.println(logIn);
                    if (dto != null && logIn != 0) {
                        System.out.println("in " + logIn);
                        request.setAttribute("inforef", service.validateGetRegisterInfo(logIn));

                        request.setAttribute("infoList", service.validateGetProperty(logIn));
                        return "viewProfile.jsp";
                    } else {
                        request.setAttribute("msg", "User Info not found Register Before Logging In ");
                        return "login.jsp";
                    }
                } else {
                    count += 1;
                    request.setAttribute("msg", "Entered OTP is Incorrect please Enter Valid OTP." +
                            " You have entered wrong OTP " + count + " time(s).");
                    return "Otp.jsp";
                }
            } else {
                request.setAttribute("msg", "OTP Expired Regenerate");
                return "login.jsp";
            }
        } else {
            service.validateUpdateAccountStatusByEmail("Blocked", emailToOTp);
            request.setAttribute("message", "Yor Account is blocked Contact Service Provider");
            return "mesg.jsp";
        }
    }

    @GetMapping("/display")
    public void displayUserImage(HttpServletResponse response, @RequestParam String imagepath) throws IOException {
        System.out.println("display image"+imagepath);
        File file = new File("C:\\Users\\lalup\\OneDrive\\Desktop\\realEsate\\image" + imagepath);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ServletOutputStream out = response.getOutputStream();
        IOUtils.copy(in, out);
        response.flushBuffer();

    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, HttpServletRequest req) {
        req.setAttribute("inforef", service.validateGetRegisterInfo(id));
        System.out.println(service.validateGetRegisterInfo(id) + "update");
        return "updateRealEstate.jsp";
    }

    @PostMapping("/updateInfoById")
    public String updateProfile(@ModelAttribute RegisterDto dto, HttpServletRequest req) {
        if(service.validateUpdateById(dto, logIn)){
            service.validateUpdateById(dto, logIn);
            req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
            req.setAttribute("message", "Updated");
            req.setAttribute("infoList", service.validateGetProperty(logIn));
            return "viewProfile.jsp";
        }else {
            req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
            req.setAttribute("message", " not Updated");
            req.setAttribute("infoList", service.validateGetProperty(logIn));
            return "viewProfile.jsp";
        }
    }

    @GetMapping("/delete")
    public RedirectView deleteRealEstate(@RequestParam("id") int id, HttpServletRequest request) {
        request.setAttribute("inforef", service.validateDeleteById(id));
        return new RedirectView("/home", true);
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") int id, HttpServletRequest req) {
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        return "profileView.jsp";
    }

    @GetMapping("/toViewProfile")
    public String toViewProfile(@RequestParam("id") int id, HttpServletRequest req) {
        req.setAttribute("inforef", service.validateGetRegisterInfo(id));
        req.setAttribute("infoList", service.validateGetProperty(id));
        return "viewProfile.jsp";
    }
    @GetMapping("/viewProfileToSell")
    public String viewProfileToSell(@RequestParam("id") int id, HttpServletRequest req) {
        req.setAttribute("inforef", service.validateGetRegisterInfo(id));
        return "property.jsp";
    }

    @PostMapping("/creatingPropertyDto")
    public String createPropertyDto(@ModelAttribute PropertyDto dto, HttpServletRequest req) throws IOException {
        System.out.println("creatingPropertyDtoABC"+logIn);
        service.savePropertyDto(logIn,dto);
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        req.setAttribute("message", "Congratulations! Your Property  Details saved");
        req.setAttribute("infoList", service.validateGetProperty(logIn));
        return "viewProfile.jsp";
    }
    int pid;
    @GetMapping("/bid")
    public String dasBordToBidForm(@RequestParam("id") int id, HttpServletRequest req) {
        PropertyEntity dto = service.validateGetPropertyTypeById(id);
         this.pid=id;
        req.setAttribute("propertyType", dto.getPropertyType());
        req.setAttribute("basePrice",dto.getPrice());
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        return "biddingForm.jsp";
    }

    @PostMapping("/biddingAmount")
    public String biddingFromSave(@ModelAttribute BiddingDto dto, HttpServletRequest req) {
        service.saveBidding(pid,logIn,dto);
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        req.setAttribute("infoList", service.validateGetProperty(logIn));
        return "viewProfile.jsp";
    }

    @GetMapping("/viewProfileToBidding")
    public String getBiddingTable(@RequestParam("id") int id, HttpServletRequest req) {
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        req.setAttribute("bidding", service.validateGetBiddingInfoById(id));
        return "biddingTable.jsp";
    }



    @GetMapping("/sell")
    public String sellProperty(@RequestParam("id") int id, HttpServletRequest req) {
        service.saveSoldBought(id);
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        req.setAttribute("message", "Congratulation your Property is Sold");
        req.setAttribute("infoList", service.validateGetProperty(logIn));
        return "viewProfile.jsp";
    }
    @GetMapping("/sold")
    public String sold(@RequestParam("id") int id, HttpServletRequest req){
        req.setAttribute("details",service.validateGetSellerDetailsById(id));
        req.setAttribute("inforef", service.validateGetRegisterInfo(id));
        return "soldProperty.jsp";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam("id") int id, HttpServletRequest req){
        req.setAttribute("details",service.validateGetBuyerDetailsById(id));
        req.setAttribute("inforef", service.validateGetRegisterInfo(id));
        return "buyProperty.jsp";
    }

}
