package com.xworkz.realestatemanagement.resorce;


import com.xworkz.realestatemanagement.service.RealestateManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class RealestateManagementResorce {

    @Autowired
    private RealestateManagementService realestateManagementService;

    @GetMapping("/getEmail/{email}")
    public  String getEmail(@PathVariable String email){
        log.info(realestateManagementService.validateGetEmail(email));
        return realestateManagementService.validateGetEmail(email);
    }
    @GetMapping("/getContactNumber/{contactNumber}")
    public  String getContactNumber(@PathVariable long contactNumber){
        log.info(realestateManagementService.validateGetContactNumber(contactNumber));
        return realestateManagementService.validateGetContactNumber(contactNumber);
    }
    @GetMapping("/getPanCardNumber/{panCardNumber}")
    public  String getPanCardNumber(@PathVariable String panCardNumber){
        log.info(realestateManagementService.validateGetPanCardNumber(panCardNumber));
        return realestateManagementService.validateGetPanCardNumber(panCardNumber);
    }
    @GetMapping("/getAadharNumber/{aadharNumber}")
    public  String getAadharNumber(@PathVariable long aadharNumber){
        log.info(realestateManagementService.validateGetAadharNumber(aadharNumber));
        return realestateManagementService.validateGetAadharNumber(aadharNumber);
    }

    @GetMapping("/getEmailForLogin/{email}")
    public String getEmailForLogin(@PathVariable String email) {
        log.info(realestateManagementService.getEmailForLogin(email));
        return realestateManagementService.getEmailForLogin(email);
    }

}
