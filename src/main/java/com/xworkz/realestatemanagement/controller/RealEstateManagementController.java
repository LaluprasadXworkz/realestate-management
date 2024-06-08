package com.xworkz.realestatemanagement.controller;

import com.xworkz.realestatemanagement.dto.*;
import com.xworkz.realestatemanagement.entity.PropertyEntity;
import com.xworkz.realestatemanagement.resorce.OtpGenerator;
import com.xworkz.realestatemanagement.service.*;
import com.xworkz.realestatemanagement.util.OtpScheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/")
public class RealEstateManagementController {

    @Autowired
    private RealestateManagementService service;
    @Autowired
    EmailSend emailSend;

    @Autowired
    OtpScheduler scheduler;

    @GetMapping("/home")
    public String home() {
        log.info("Returning Home page");
        return "index";
    }

    @GetMapping("/homeToRegister")
    public String toRegister() {
        log.info("Home Page To Register page");
        return "register";
    }

    @GetMapping("/homeToLogin")
    public String toLogin() {
        log.info("Home Page to Login Page");
        return "login";
    }

    @PostMapping("/creatingRealEstate")
    public String createRealEstate(@ModelAttribute RegisterDto dto, HttpServletRequest req) {
        if (service.validateSaveRegisterInfo(dto)) {
            log.info("RegisterDto Saved");
            req.setAttribute("message", "Congratulations! Your registration was successful. Log in to view your Profile.");
            return "mesg";
        } else {
            log.warn("RegisterDto Not Saved");
            req.setAttribute("message", "Please try again");
            return "mesg";
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
            log.info("Otp sent to this " + email + "-" + otp);
            request.setAttribute("mail", email);
            return "Otp";
        } else {
            request.setAttribute("msg", "Your Account is blocked Contact Service Provider");
            return "login";
        }
    }

    int logIn;
    int count = 0;

    @PostMapping("/SetOTP")
    public String loginToProfile(@RequestParam("generatedOtp") String generatedOtp, HttpServletRequest request) {
        log.info("Received request to verify OTP for email: {}", emailToOTp);
        if (count < 3) {
            log.debug("Checking OTP attempt count: {}", count);
            if (LocalDateTime.now().isBefore(otpGenerationTime.plusMinutes(2))) {
                if (generatedOtp.equals(service.validateGetOtpByEmail(emailToOTp))) {
                    RegisterDto dto = service.validateGetInfoByEmail(emailToOTp);
                    this.logIn = dto.getRid();
                    if (dto != null && logIn != 0) {
                        log.info("User logged in successfully with ID: {}", logIn);
                        request.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
                        request.setAttribute("infoList", service.validateGetProperty(logIn));
                        return "viewProfile";
                    } else {
                        log.warn("User info not found for email: {}", emailToOTp);
                        request.setAttribute("msg", "User Info not found Register Before Logging In ");
                        return "login";
                    }
                } else {
                    count += 1;
                    log.warn("Incorrect OTP entered for email: {}. Attempt: {}", emailToOTp, count);
                    request.setAttribute("msg", "Entered OTP is Incorrect please Enter Valid OTP." +
                            " You have entered wrong OTP " + count + " time(s).");
                    return "Otp";
                }
            } else {
                log.warn("OTP expired for email: {}", emailToOTp);
                request.setAttribute("msg", "OTP Expired Regenerate");
                return "login";
            }
        } else {
            log.warn("Max OTP attempts reached for email: {}. Blocking account.", emailToOTp);
            service.validateUpdateAccountStatusByEmail("Blocked", emailToOTp);
            request.setAttribute("message", "Your Account is blocked Contact Service Provider");
            return "mesg";
        }
    }

    @Value("${image.directory}")
    private String imageDirectory;

    @GetMapping("/display")
    public void displayUserImage(HttpServletResponse response, @RequestParam String imagepath) throws IOException {
        File file = new File(imageDirectory + File.separator + imagepath);
        log.warn("Image file found at path: {}", file.getAbsolutePath());

        try (InputStream in = new BufferedInputStream(new FileInputStream(file));
             ServletOutputStream out = response.getOutputStream()) {
            IOUtils.copy(in, out);
            log.info("Image displayed");
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            log.error("Image file not found", e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
        }
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, HttpServletRequest req) {
        req.setAttribute("inforef", service.validateGetRegisterInfo(id));
        log.info("RegisterDto retrieved successfully for ID: {}", id);
        return "updateRealEstate";
    }

    @PostMapping("/updateInfoById")
    public String updateProfile(@ModelAttribute RegisterDto dto, HttpServletRequest req) {
        if (service.validateUpdateById(dto, logIn)) {
            service.validateUpdateById(dto, logIn);
            req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
            log.info("Profile updated successfully for ID: {}", logIn);
            req.setAttribute("message", "Updated");
            req.setAttribute("infoList", service.validateGetProperty(logIn));
            return "viewProfile";
        } else {
            req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
            log.warn("Failed to update profile for ID: {}", logIn);
            req.setAttribute("message", "Not Updated");
            req.setAttribute("infoList", service.validateGetProperty(logIn));
            return "viewProfile";
        }
    }

    @GetMapping("/delete")
    public RedirectView deleteRealEstate(@RequestParam("id") int id, HttpServletRequest request) {
        log.info("Received request to delete real estate entry with ID: {}", id);
        boolean deleteResult = service.validateDeleteById(id);
        if (deleteResult) {
            log.info("Real estate entry with ID {} deleted successfully", id);
        } else {
            log.warn("Failed to delete real estate entry with ID: {}", id);
        }
        request.setAttribute("inforef", deleteResult);
        return new RedirectView("/home", true);
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") int id, HttpServletRequest req) {
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        log.info("Viewing profile for ID: {}", id);
        return "profileView";
    }

    @GetMapping("/toViewProfile")
    public String toViewProfile(@RequestParam("id") int id, HttpServletRequest req) {
        log.info("Viewing profile for ID: {}", id);
        req.setAttribute("inforef", service.validateGetRegisterInfo(id));
        req.setAttribute("infoList", service.validateGetProperty(id));
        return "viewProfile";
    }

    @GetMapping("/viewProfileToSell")
    public String viewProfileToSell(@RequestParam("id") int id, HttpServletRequest req) {
        log.info("Viewing profile to sell for ID: {}", id);
        req.setAttribute("inforef", service.validateGetRegisterInfo(id));
        return "property";
    }

    @PostMapping("/creatingPropertyDto")
    public String createPropertyDto(@ModelAttribute PropertyDto dto, HttpServletRequest req) throws IOException {
        log.info("Creating property DTO for user ID: {}", logIn);
        service.savePropertyDto(logIn, dto);
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        req.setAttribute("message", "Congratulations! Your Property Details saved");
        req.setAttribute("infoList", service.validateGetProperty(logIn));
        return "viewProfile";
    }

    int pid;

    @GetMapping("/bid")
    public String dasBordToBidForm(@RequestParam("id") int id, HttpServletRequest req) {
        PropertyEntity dto = service.validateGetPropertyTypeById(id);
        this.pid = id;
        log.info("Preparing bid form for property ID: {}", id);
        req.setAttribute("propertyType", dto.getPropertyType());
        req.setAttribute("basePrice", dto.getPrice());
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        return "biddingForm";
    }

    @PostMapping("/biddingAmount")
    public String biddingFromSave(@ModelAttribute BiddingDto dto, HttpServletRequest req) {
        service.saveBidding(pid, logIn, dto);
        log.info("Saved bidding amount for property ID: {} and user ID: {}", pid, logIn);
        log.info(dto.toString());
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        req.setAttribute("infoList", service.validateGetProperty(logIn));
        return "viewProfile";
    }

    @GetMapping("/viewProfileToBidding")
    public String getBiddingTable(@RequestParam("id") int id, HttpServletRequest req) {
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        req.setAttribute("bidding", service.validateGetBiddingInfoById(id));
        log.info("Retrieved bidding info for property ID: {}", id);
        return "biddingTable";
    }

    @GetMapping("/sell")
    public String sellProperty(@RequestParam("id") int id, HttpServletRequest req) {
        service.saveSoldBought(id);
        req.setAttribute("inforef", service.validateGetRegisterInfo(logIn));
        req.setAttribute("message", "Congratulations your Property is Sold");
        req.setAttribute("infoList", service.validateGetProperty(logIn));
        log.info("Property with ID {} has been marked as sold", id);
        return "viewProfile";
    }

    @GetMapping("/sold")
    public String sold(@RequestParam("id") int id, HttpServletRequest req) {
        req.setAttribute("details", service.validateGetSellerDetailsById(id));
        req.setAttribute("inforef", service.validateGetRegisterInfo(id));
        log.info("Viewing details of property sold by seller with ID {}", id);
        return "soldProperty";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam("id") int id, HttpServletRequest req) {
        req.setAttribute("details", service.validateGetBuyerDetailsById(id));
        req.setAttribute("inforef", service.validateGetRegisterInfo(id));
        log.info("Viewing details of property to buy with ID {}", id);
        return "buyProperty";
    }
}
