package com.xworkz.realestatemanagement.resorce;


import com.xworkz.realestatemanagement.service.RealestateManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RealestateManagementResorce {

    @Autowired
    private RealestateManagementService realestateManagementService;

    @GetMapping("/getEmail/{email}")
    public  String getEmail(@PathVariable String email){
        System.out.println(realestateManagementService.validateGetEmail(email));
        return realestateManagementService.validateGetEmail(email);
    }
    @GetMapping("/getContactNumber/{contactNumber}")
    public  String getContactNumber(@PathVariable long contactNumber){
        System.out.println(realestateManagementService.validateGetContactNumber(contactNumber));
        return realestateManagementService.validateGetContactNumber(contactNumber);
    }
    @GetMapping("/getPanCardNumber/{panCardNumber}")
    public  String getPanCardNumber(@PathVariable String panCardNumber){
        System.out.println(realestateManagementService.validateGetPanCardNumber(panCardNumber));
        return realestateManagementService.validateGetPanCardNumber(panCardNumber);
    }
    @GetMapping("/getAadharNumber/{aadharNumber}")
    public  String getAadharNumber(@PathVariable long aadharNumber){
        System.out.println(realestateManagementService.validateGetAadharNumber(aadharNumber));
        return realestateManagementService.validateGetAadharNumber(aadharNumber);
    }

    @GetMapping("/getEmailForLogin/{email}")
    public String getEmailForLogin(@PathVariable String email) {
        return realestateManagementService.getEmailForLogin(email);
    }

}
