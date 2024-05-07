package com.xworkz.realestatemanagement.service.impl;


import com.xworkz.realestatemanagement.dto.*;
import com.xworkz.realestatemanagement.entity.PropertyEntity;
import com.xworkz.realestatemanagement.repository.RealestateManagementRepository;
import com.xworkz.realestatemanagement.service.EmailSend;
import com.xworkz.realestatemanagement.service.RealestateManagementService;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Component
@NoArgsConstructor
public class RealestateManagementServiceImpl implements RealestateManagementService {

    private static final Logger logger = LoggerFactory.getLogger(RealestateManagementServiceImpl.class);

    @Autowired
    RealestateManagementRepository repository;
    @Autowired
    Audit audit;
    @Autowired
    EmailSend emailSend;



    @Override
    public boolean validateSaveRegisterInfo(RegisterDto dto) {
        if (dto.getFirstName()!=null&&dto.getLastName()!=null){
            audit.setId(dto.getRid());
            audit.setCreatedBy(dto.getFirstName()+" "+dto.getLastName());
            audit.setCreatedOn(LocalDateTime.now());
            dto.setAudit(audit);
            dto.setAccountStatus("Active");
            repository.saveRegisterInfo(dto);
            emailSend.mailSend(dto.getEmail());
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void validateSavePropertyDTO(PropertyDto dto) throws IOException {
        if (dto.getPropertyType()!=null){
            logger.info("Validating and saving property data");
            dto.setStatues("forSale");
            saveImage(dto);
            PropertyEntity entity=new PropertyEntity();
            BeanUtils.copyProperties(dto,entity);
            repository.savePropertyDTO(entity);
        }else {
            logger.warn("Property type is null, data not saved");
        }
    }

    public void savePropertyDto(int logIn,PropertyDto dto) throws IOException {
     RegisterDto  register= validateGetRegisterInfo(logIn);
       register.setRid(logIn);
        System.out.println(logIn);
        dto.setRegister(register);
       dto.setOwnerName(register.getFirstName()+" "+register.getLastName());
       validateSavePropertyDTO(dto);
    }

    @Override
    public PropertyEntity validateGetPropertyTypeById(int id) {
        return repository.getPropertyTypeById(id);
    }

    @Override
    public void validateSaveBiddingDto(BiddingDto dto) {
        if (dto.getAmount()!=0){
            System.out.println("validateSaveBiddingDto Data saved");
            repository.saveBiddingDto(dto);
        }else {
            System.out.println("validateSaveBiddingDto Not saved");
        }
    }
    @Autowired
    PropertyEntity entity;
    @Override
    public void saveBidding(int pid,int logIn, BiddingDto dto){
        PropertyEntity propertyDto=validateGetPropertyTypeById(pid);
        System.out.println(propertyDto);
        RegisterDto register =validateGetRegisterInfo(logIn);
        dto.setBidderName(register.getFirstName() + " " + register.getLastName());
        dto.setBidderId(register.getRid());
        dto.setPropertyType(propertyDto.getPropertyType());
        dto.setLocation(propertyDto.getLocation());
        entity.setId(pid);
        dto.setPropertyId(entity);
        validateSaveBiddingDto(dto);
    }

    @Override
    public void validateSaveSoldBought(SoldBoughtDto dto) {
        if (!dto.getSoldBy().isEmpty()&&!dto.getSoldTo().isEmpty()){
            repository.saveSoldBought(dto);
        }else {
            System.out.println("validateSaveSoldBought Not working");
        }
    }

    @Autowired
    SoldBoughtDto soldBought;

    @Override
    public void saveSoldBought(int id){
        BiddingDto dto =getBiddingById(id);
        validateUpdateStatuesById(id);
        PropertyEntity propertyDto=validateGetPropertyTypeById(dto.getPropertyId().getId());
        RegisterDto soldTo=validateGetRegisterInfo(dto.getBidderId());
        RegisterDto soldFrom=validateGetRegisterInfo(propertyDto.getRegister().getRid());

        soldBought.setPropertyId(propertyDto.getId());
        soldBought.setPropertyType(propertyDto.getPropertyType());
        soldBought.setSoldTo(dto.getBidderName());
        soldBought.setBuyerId(dto.getBidderId());
        String soldBy=soldFrom.getFirstName()+" "+soldFrom.getLastName();
        soldBought.setSellerId(soldFrom.getRid());
        soldBought.setSoldBy(soldFrom.getFirstName()+" "+soldFrom.getLastName());
        soldBought.setLocation(dto.getLocation());
        soldBought.setPrice(dto.getAmount());
        validateSaveSoldBought(soldBought);

        emailSend.mailSend(soldTo.getEmail(),propertyDto.getPropertyType(),dto.getBidderName(),dto.getAmount());
        emailSend.mailSend(soldFrom.getEmail(),propertyDto.getPropertyType(),soldBy,dto.getAmount(),dto.getLocation());

    }
    public void saveImage(PropertyDto dto) throws IOException {
        if(dto.getMultipartFile()!=null && !dto.getMultipartFile().isEmpty()){
            byte[] bytes=dto.getMultipartFile().getBytes();
            String filePath="C:\\Users\\lalup\\OneDrive\\Desktop\\realEsate\\image"+dto.getMultipartFile().getOriginalFilename();
            File file=new File(filePath);
            Path path= Paths.get(file.getAbsolutePath());
            Files.write(path,bytes);
            dto.setPropertyImage(dto.getMultipartFile().getOriginalFilename().toString());
        }else{
            String defaultImageName = "realEsate.jpg";
            String defaultImagePath="C:\\Users\\lalup\\OneDrive\\Desktop\\realEsate\\image"+defaultImageName;

            byte[] defaultImageBytes=Files.readAllBytes(Paths.get(defaultImagePath));
            String filePath="C:\\Users\\lalup\\OneDrive\\Desktop\\realEsate\\image"+defaultImageName;
            File file=new File(filePath);
            Path path= Paths.get(file.getAbsolutePath());
            Files.write(path,defaultImageBytes);
            dto.setPropertyImage(defaultImageName);
        }

    }

//    realEsate

    @Override
    public String getEmailForLogin(String email) {
        List<String> mailCheck = repository.getEmailForLogin(email);

        for (String str : mailCheck) {
            if (str.equals(email)) {
                return "Click on Generate OTP";
            }
        }
        return "Register the mail Id Before login";
    }

    @Override
    public boolean validateToGetEmail(String email) {
        List<String> extMail = repository.getEmail(email);
        for (String str : extMail) {
            if (str.equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String validateGetEmail(String email) {
        List<String> extMail = repository.getEmail(email);
        for (String str : extMail) {
            if (str.equals(email)) {
                return "Mail id Already exists";
            }
        }
        return "";
    }

    @Override
    public String validateGetContactNumber(long contactNumber) {
        List<Long> number=repository.getContactNumber(contactNumber);
        for (Long num:number) {
            if (num==contactNumber) {
                return " ContactNumber Already exits";
            }
        }
            return "";
    }

    @Override
    public String validateGetPanCardNumber(String panCardNumber) {
        List<String> pan=repository.getPanCardNumber(panCardNumber);
        for(String str:pan){
            if(str.equals(panCardNumber)){
                return " PanCard Number Already exits";
            }
        }
        return "";
    }

    @Override
    public String validateGetAadharNumber(long aadharNumber) {
        List<Long> number=repository.getAadharNumber(aadharNumber);
        for(Long aadhar:number){
            if(aadhar==aadharNumber){
                return " AadharNumber Already exits";
            }
        }
            return "";
    }

    @Override
    public RegisterDto validateGetRegisterInfo(int id) {
        return repository.getRegisterInfo(id);
    }

    @Override
    public RegisterDto validateGetInfoByEmail(String email) {
        return repository.getInfoByEmail(email);
    }

    @Override
    public List<PropertyEntity> validateGetProperty(int id) {
        return repository.getProperty(id);

    }

    @Override
    public boolean validateUpdateOTPByEmail(String otp, String email) {
        return repository.updateOTPByEmail(otp,email);
    }

    @Override
    public boolean validateUpdateAccountStatusByEmail(String accountStatus, String email) {
        return repository.updateAccountStatusByEmail(accountStatus,email);
    }

    @Override
    public String validateGetOtpByEmail(String email) {
        return repository.getOtpByEmail(email);
    }

    @Override
    public boolean validateUpdateById(RegisterDto dto, int id) {
        audit.setUpdatedBy(dto.getFirstName() + " " + dto.getLastName());
        audit.setUpdatedOn(LocalDateTime.now());
        dto.setAudit(audit);
        return repository.updateById(dto,id);
    }

    @Override
    public boolean validateUpdateStatuesById(int id ) {
        return repository.updateStatuesById(id);
    }

    @Override
    public BiddingDto getBiddingById(int id) {
        return repository.getBiddingById(id);
    }

    @Override
    public boolean validateDeleteById(int id) {
        if(id!=0) {
            System.out.println("controller delete service");
           return repository.deleteById(id);
        }else {
            System.out.println("controller not delete service");
            return false;
        }
    }



    @Override
    public List<BiddingDto> validateGetBiddingInfoById(int id) {
        System.out.println("validateGetBiddingInfoById  service"+repository.getBiddingInfoById(id).toString());
        return repository.getBiddingInfoById(id);
    }

    @Override
    public List<SoldBoughtDto> validateGetSellerDetailsById(int id) {
        return repository.getSellerDetailsById(id);
    }

    @Override
    public List<SoldBoughtDto> validateGetBuyerDetailsById(int id) {
        return repository.getBuyerDetailsById(id);
    }
}
