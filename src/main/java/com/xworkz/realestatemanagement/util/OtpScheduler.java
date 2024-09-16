package com.xworkz.realestatemanagement.util;

import com.xworkz.realestatemanagement.service.RealestateManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OtpScheduler {

    @Autowired
    RealestateManagementService service;

//    @Scheduled(fixedRate = 3 * 60 * 1000)
//    public void scheduleOtpUpdate() {
//        service.updateOtp();
//        log.info("Invoking Scheduled");
//    }
}
