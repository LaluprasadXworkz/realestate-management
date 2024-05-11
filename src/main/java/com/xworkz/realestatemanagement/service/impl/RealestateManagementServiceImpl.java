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
import java.util.Collections;
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
            logger.info("Validating and saving Register data");
            return true;
        }else {
            logger.warn("Register is null, data not saved");
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
        if (logIn !=0 && dto.getPropertyType()!=null) {
            logger.info("Validating and Saving property data");
            RegisterDto  register= validateGetRegisterInfo(logIn);
            register.setRid(logIn);
            System.out.println(logIn);
            dto.setRegister(register);
            dto.setOwnerName(register.getFirstName()+" "+register.getLastName());
            validateSavePropertyDTO(dto);
        }else {
            logger.warn("Property type is null, data not saved");
        }

    }

    @Override
    public PropertyEntity validateGetPropertyTypeById(int id) {
        return repository.getPropertyTypeById(id);
    }

    @Override
    public void validateSaveBiddingDto(BiddingDto dto) {
        if (dto.getAmount()!=0){
            logger.info("ValidateSaveBiddingDto Data saved");
            repository.saveBiddingDto(dto);
        }else {
            logger.warn("validateSaveBiddingDto Not saved");
        }
    }
    @Autowired
    PropertyEntity entity;
    @Override
    public void saveBidding(int pid,int logIn, BiddingDto dto){
        if (pid != 0 && logIn !=0 && dto.getAmount()!=0) {
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
            logger.info("BiddingDto Data saved"+dto);
        }else {
            logger.warn("BiddingDto Data not saved");
        }
    }

    @Override
    public void validateSaveSoldBought(SoldBoughtDto dto) {
        if (!dto.getSoldBy().isEmpty()&&!dto.getSoldTo().isEmpty()){
            repository.saveSoldBought(dto);
        }else {
            logger.warn("validateSaveSoldBought Not working");
        }
    }

    @Autowired
    SoldBoughtDto soldBought;

    @Override
    public void saveSoldBought(int id){
        if (id !=0) {
            BiddingDto dto = getBiddingById(id);
            validateUpdateStatuesById(id);
            PropertyEntity propertyDto = validateGetPropertyTypeById(dto.getPropertyId().getId());
            RegisterDto soldTo = validateGetRegisterInfo(dto.getBidderId());
            RegisterDto soldFrom = validateGetRegisterInfo(propertyDto.getRegister().getRid());

            soldBought.setPropertyId(propertyDto.getId());
            soldBought.setPropertyType(propertyDto.getPropertyType());
            soldBought.setSoldTo(dto.getBidderName());
            soldBought.setBuyerId(dto.getBidderId());
            String soldBy = soldFrom.getFirstName() + " " + soldFrom.getLastName();
            soldBought.setSellerId(soldFrom.getRid());
            soldBought.setSoldBy(soldFrom.getFirstName() + " " + soldFrom.getLastName());
            soldBought.setLocation(dto.getLocation());
            soldBought.setPrice(dto.getAmount());
            validateSaveSoldBought(soldBought);

            emailSend.mailSend(soldTo.getEmail(), propertyDto.getPropertyType(), dto.getBidderName(), dto.getAmount());
            emailSend.mailSend(soldFrom.getEmail(), propertyDto.getPropertyType(), soldBy, dto.getAmount(), dto.getLocation());
            logger.info("saveSoldBought Data saved "+soldBought);
        }else {
            logger.warn("saveSoldBought Data not saved");
        }
    }
    public void saveImage(PropertyDto dto) throws IOException {
        if(dto.getMultipartFile()!=null && !dto.getMultipartFile().isEmpty()) {
            byte[] bytes = dto.getMultipartFile().getBytes();
            String filePath = "C:\\Users\\lalup\\OneDrive\\Desktop\\realEsate\\image" + dto.getMultipartFile().getOriginalFilename();
            File file = new File(filePath);
            Path path = Paths.get(file.getAbsolutePath());
            Files.write(path, bytes);
            dto.setPropertyImage(dto.getMultipartFile().getOriginalFilename().toString());
            logger.info("Image :" + dto.getMultipartFile().getOriginalFilename().toString());
        }else{
            String defaultImageName = "realEsate.jpg";
            dto.setPropertyImage(defaultImageName);
        }
//        else{
//            String defaultImageName = "realEsate.jpg";
//            String defaultImagePath="C:\\Users\\lalup\\OneDrive\\Desktop\\realEsate\\image"+defaultImageName;
//
//            byte[] defaultImageBytes=Files.readAllBytes(Paths.get(defaultImagePath));
//            String filePath="C:\\Users\\lalup\\OneDrive\\Desktop\\realEsate\\image"+defaultImageName;
//            File file=new File(filePath);
//            Path path= Paths.get(file.getAbsolutePath());
//            Files.write(path,defaultImageBytes);
//            dto.setPropertyImage(defaultImageName);
//            logger.warn("Image is File is empty");
//        }
    }

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
                logger.info("Email already exists: {}", email);
                return "Mail id Already exists";
            }
        }
        logger.info("Email does not exist in the repository: {}", email);
        return "";
    }

    @Override
    public String validateGetContactNumber(long contactNumber) {
        List<Long> number=repository.getContactNumber(contactNumber);
        for (Long num:number) {
            if (num==contactNumber) {
                logger.info("Contact number already exists: {}", contactNumber);
                return " ContactNumber Already exits";
            }
        }
        logger.info("Contact number does not exist in the repository: {}", contactNumber);
        return "";
    }

    @Override
    public String validateGetPanCardNumber(String panCardNumber) {
        List<String> pan=repository.getPanCardNumber(panCardNumber);
        for(String str:pan){
            if(str.equals(panCardNumber)){
                logger.info("PAN card number already exists: {}", panCardNumber);
                return " PanCard Number Already exits";
            }
        }
        logger.info("PAN card number does not exist in the repository: {}", panCardNumber);
        return "";
    }

    @Override
    public String validateGetAadharNumber(long aadharNumber) {
        List<Long> number=repository.getAadharNumber(aadharNumber);
        for(Long aadhar:number){
            if(aadhar==aadharNumber){
                logger.info("Aadhar number already exists: {}", aadharNumber);
                return " AadharNumber Already exits";
            }
        }
        logger.info("Aadhar number does not exist in the repository: {}", aadharNumber);
        return "";
    }

    @Override
    public RegisterDto validateGetRegisterInfo(int id) {
        if (id != 0) {
            logger.info("Fetching register info for ID: {}", id);
            RegisterDto registerDto = repository.getRegisterInfo(id);
            if (registerDto != null) {
                logger.info("Register info retrieved successfully for ID: {}", id);
                return registerDto;
            } else {
                logger.warn("No register info found for ID: {}", id);
                return null;
            }
        } else {
            logger.warn("Invalid ID provided for fetching register info");
            return null;
        }
    }

    @Override
    public RegisterDto validateGetInfoByEmail(String email) {
        if (email != null) {
            logger.info("Fetching info for email: {}", email);
            RegisterDto registerDto = repository.getInfoByEmail(email);
            if (registerDto != null) {
                logger.info("Info retrieved successfully for email: {}", email);
                return registerDto;
            } else {
                logger.warn("No info found for email: {}", email);
                return null;
            }
        } else {
            logger.warn("Invalid email provided for fetching info");
            return null;
        }
    }

    @Override
    public List<PropertyEntity> validateGetProperty(int id) {
        if (id != 0) {
            logger.info("Fetching properties for ID: {}", id);
            List<PropertyEntity> properties = repository.getProperty(id);
            if (properties != null && !properties.isEmpty()) {
                logger.info("Properties retrieved successfully for ID: {}", id);
                return properties;
            } else {
                logger.warn("No properties found for ID: {}", id);
                return Collections.emptyList();
            }
        } else {
            logger.warn("Invalid ID provided for fetching properties");
            return Collections.emptyList();
        }
    }

    @Override
    public boolean validateUpdateOTPByEmail(String otp, String email) {
        if (otp != null && email != null) {
            logger.info("Updating OTP for email: {}", email);
            boolean isUpdated = repository.updateOTPByEmail(otp, email);
            if (isUpdated) {
                logger.info("OTP updated successfully for email: {}", email);
            } else {
                logger.warn("Failed to update OTP for email: {}", email);
            }
            return isUpdated;
        } else {
            if (otp == null) {
                logger.warn("Invalid OTP provided for updating OTP for email: {}", email);
            }
            if (email == null) {
                logger.warn("Invalid email provided for updating OTP");
            }
            return false;
        }
    }

    @Override
    public boolean validateUpdateAccountStatusByEmail(String accountStatus, String email) {
        if (accountStatus != null && email != null) {
            logger.info("Updating account status '{}' for email: {}", accountStatus, email);
            boolean isUpdated = repository.updateAccountStatusByEmail(accountStatus, email);
            if (isUpdated) {
                logger.info("Account status updated successfully for email: {}", email);
            } else {
                logger.warn("Failed to update account status for email: {}", email);
            }
            return isUpdated;
        } else {
            if (accountStatus == null) {
                logger.warn("Invalid account status provided for updating account status for email: {}", email);
            }
            if (email == null) {
                logger.warn("Invalid email provided for updating account status");
            }
            return false;
        }
    }
    @Override
    public String validateGetOtpByEmail(String email) {
        if (email != null && !email.isEmpty()) {
            logger.info("Fetching OTP for email: {}", email);
            String otp = repository.getOtpByEmail(email);
            if (otp != null) {
                logger.info("OTP retrieved successfully for email: {}", email);
            } else {
                logger.warn("No OTP found for email: {}", email);
            }
            return otp;
        } else {
            logger.warn("Invalid email provided for retrieving OTP");
            return null;
        }
    }
    @Override
    public boolean validateUpdateById(RegisterDto dto, int id) {
        if (id != 0) {
            logger.info("Updating Register information for ID: {}", id);
            Audit audit = new Audit();
            audit.setUpdatedBy(dto.getFirstName() + " " + dto.getLastName());
            audit.setUpdatedOn(LocalDateTime.now());
            dto.setAudit(audit);
            boolean isUpdated = repository.updateById(dto, id);

            if (isUpdated) {
                logger.info("Register information updated successfully for ID: {}", id);
            } else {
                logger.warn("Failed to update Register information for ID: {}", id);
            }
            return isUpdated;
        } else {
            logger.warn("Invalid ID provided for updating Register information: {}", id);
            return false;
        }
    }
    @Override
    public boolean validateUpdateStatuesById(int id ) {
        if (id != 0) {
            logger.info("Updating status for ID: {}", id);
            boolean isUpdated = repository.updateStatuesById(id);
            if (isUpdated) {
                logger.info("Status updated successfully for ID: {}", id);
            } else {
                logger.warn("Failed to update status for ID: {}", id);
            }
            return isUpdated;
        } else {
            logger.warn("Invalid ID provided for updating status: {}", id);
            return false;
        }
    }
    @Override
    public BiddingDto getBiddingById(int id) {
        if (id != 0) {
            logger.info("Fetching Bidding with ID: {}", id);
            BiddingDto biddingDto = repository.getBiddingById(id);
            if (biddingDto != null) {
                logger.info("Bidding retrieved successfully for ID: {}", id);
            } else {
                logger.warn("Bidding not found for ID: {}", id);
            }
            return biddingDto;
        } else {
            logger.warn("Invalid ID provided for fetching Bidding: {}", id);
            return null;
        }
    }
    @Override
    public boolean validateDeleteById(int id) {
        if (id != 0) {
            logger.info("Attempting to delete entry with ID: {}", id);
            boolean isDeleted = repository.deleteById(id);
            if (isDeleted) {
                logger.info("Entry with ID {} deleted successfully", id);
            } else {
                logger.warn("Failed to delete entry with ID {}", id);
            }
            return isDeleted;
        } else {
            logger.warn("Invalid ID provided for delete operation: {}", id);
            return false;
        }
    }
    @Override
    public List<BiddingDto> validateGetBiddingInfoById(int id) {
        logger.info("Fetching Bidding Info for ID: {}", id);
        List<BiddingDto> biddingInfo = repository.getBiddingInfoById(id);
        if (biddingInfo != null && !biddingInfo.isEmpty()) {
            logger.info("Bidding Info retrieved successfully for ID: {}", id);
            return biddingInfo;
        } else {
            logger.warn("No Bidding Info found for ID: {}", id);
            return Collections.emptyList();
        }
    }
    @Override
    public List<SoldBoughtDto> validateGetSellerDetailsById(int id) {
        if (id != 0) {
            logger.info("Getting Seller Details for ID: {}", id);
            List<SoldBoughtDto> sellerDetails = repository.getSellerDetailsById(id);
            if (sellerDetails != null && !sellerDetails.isEmpty()) {
                logger.info("Seller Details retrieved successfully for ID: {}", id);
                return sellerDetails;
            } else {
                logger.warn("No Seller Details found for ID: {}", id);
            }
        } else {
            logger.warn("Invalid ID provided for fetching Seller Details: {}", id);
        }
        return Collections.emptyList();
    }
    @Override
    public List<SoldBoughtDto> validateGetBuyerDetailsById(int id) {
        if (id != 0) {
            logger.info("Getting Buyer Details Data for ID: {}", id);
            List<SoldBoughtDto> buyerDetails = repository.getBuyerDetailsById(id);
            if (buyerDetails != null && !buyerDetails.isEmpty()) {
                return buyerDetails;
            } else {
                logger.warn("Buyer Details not found for ID: {}", id);
            }
        } else {
            logger.warn("Invalid ID provided for fetching Buyer Details: {}", id);
        }
        return Collections.emptyList();
    }
}
