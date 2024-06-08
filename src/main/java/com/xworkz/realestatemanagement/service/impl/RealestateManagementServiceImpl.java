package com.xworkz.realestatemanagement.service.impl;


import com.xworkz.realestatemanagement.dto.*;
import com.xworkz.realestatemanagement.entity.PropertyEntity;
import com.xworkz.realestatemanagement.repository.RealestateManagementRepository;
import com.xworkz.realestatemanagement.service.EmailSend;
import com.xworkz.realestatemanagement.service.RealestateManagementService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class RealestateManagementServiceImpl implements RealestateManagementService {

    @Autowired
    RealestateManagementRepository repository;
    @Autowired
    Audit audit;
    @Autowired
    EmailSend emailSend;

    @Override
    public boolean validateSaveRegisterInfo(RegisterDto dto) {
        if (dto.getFirstName() != null && dto.getLastName() != null) {
            audit.setId(dto.getRid());
            audit.setCreatedBy(dto.getFirstName() + " " + dto.getLastName());
            audit.setCreatedOn(LocalDateTime.now());
            dto.setAudit(audit);
            dto.setAccountStatus("Active");
            repository.saveRegisterInfo(dto);
            emailSend.mailSend(dto.getEmail());
            log.info("Validating and saving Register data");
            return true;
        } else {
            log.warn("Register is null, data not saved");
            return false;
        }
    }

    @Override
    public void validateSavePropertyDTO(PropertyDto dto) throws IOException {
        if (dto.getPropertyType() != null) {
            log.info("Validating and saving property data");
            dto.setStatues("forSale");
            saveImage(dto);
            PropertyEntity entity = new PropertyEntity();
            BeanUtils.copyProperties(dto, entity);
            repository.savePropertyDTO(entity);
        } else {
            log.warn("Property type is null, data not saved");
        }
    }

    public void savePropertyDto(int logIn, PropertyDto dto) throws IOException {
        if (logIn != 0 && dto.getPropertyType() != null) {
            log.info("Validating and Saving property data");
            RegisterDto register = validateGetRegisterInfo(logIn);
            register.setRid(logIn);
            System.out.println(logIn);
            dto.setRegister(register);
            dto.setOwnerName(register.getFirstName() + " " + register.getLastName());
            validateSavePropertyDTO(dto);
        } else {
            log.warn("Property type is null, data not saved");
        }

    }

    @Override
    public PropertyEntity validateGetPropertyTypeById(int id) {
        return repository.getPropertyTypeById(id);
    }

    @Override
    public void validateSaveBiddingDto(BiddingDto dto) {
        if (dto.getAmount() != 0) {
            log.info("ValidateSaveBiddingDto Data saved");
            repository.saveBiddingDto(dto);
        } else {
            log.warn("validateSaveBiddingDto Not saved");
        }
    }

    @Autowired
    PropertyEntity entity;

    @Override
    public void saveBidding(int pid, int logIn, BiddingDto dto) {
        if (pid != 0 && logIn != 0 && dto.getAmount() != 0) {
            PropertyEntity propertyDto = validateGetPropertyTypeById(pid);
            System.out.println(propertyDto);
            RegisterDto register = validateGetRegisterInfo(logIn);
            dto.setBidderName(register.getFirstName() + " " + register.getLastName());
            dto.setBidderId(register.getRid());
            dto.setPropertyType(propertyDto.getPropertyType());
            dto.setLocation(propertyDto.getLocation());
            entity.setId(pid);
            dto.setPropertyId(entity);
            validateSaveBiddingDto(dto);
            log.info("BiddingDto Data saved" + dto);
        } else {
            log.warn("BiddingDto Data not saved");
        }
    }

    @Override
    public void validateSaveSoldBought(SoldBoughtDto dto) {
        if (!dto.getSoldBy().isEmpty() && !dto.getSoldTo().isEmpty()) {
            repository.saveSoldBought(dto);
        } else {
            log.warn("validateSaveSoldBought Not working");
        }
    }

    @Autowired
    SoldBoughtDto soldBought;

    @Override
    public void saveSoldBought(int id) {
        if (id != 0) {
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
            log.info("saveSoldBought Data saved " + soldBought);
        } else {
            log.warn("saveSoldBought Data not saved");
        }
    }

    @Value("${image.directory}")
    private String imageDirectory;

    public void saveImage(PropertyDto dto) throws IOException {

        if (dto.getMultipartFile() != null && !dto.getMultipartFile().isEmpty()) {
            byte[] bytes = dto.getMultipartFile().getBytes();
            String filePath = imageDirectory + File.separator + dto.getMultipartFile().getOriginalFilename();
            File directory = new File(imageDirectory);
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            dto.setPropertyImage(dto.getMultipartFile().getOriginalFilename().toString());
            log.info("Image saved: " + dto.getMultipartFile().getOriginalFilename().toString());
        } else {
            String defaultImageName = "realEsate.jpg";
            dto.setPropertyImage(defaultImageName);
            log.info("Default image set: " + defaultImageName);
        }
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

    String emailid;
    @Override
    public boolean validateToGetEmail(String email) {
        List<String> extMail = repository.getEmail(email);
        for (String str : extMail) {
            if (str.equals(email)) {
                this.emailid=email;
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateOtp() {
        if(emailid!=null){
            validateUpdateOTPByEmail("000000", emailid);
            log.info("Default OTP set to this email: " + emailid);
        }else {
            log.info("Default OTP not set to this email: " + emailid);
        }
    }

    @Override
    public String validateGetEmail(String email) {
        List<String> extMail = repository.getEmail(email);
        for (String str : extMail) {
            if (str.equals(email)) {
                log.info("Email already exists: {}", email);
                return "Mail id Already exists";
            }
        }
        log.info("Email does not exist in the repository: {}", email);
        return "";
    }

    @Override
    public String validateGetContactNumber(long contactNumber) {
        List<Long> number=repository.getContactNumber(contactNumber);
        for (Long num:number) {
            if (num==contactNumber) {
                log.info("Contact number already exists: {}", contactNumber);
                return " ContactNumber Already exits";
            }
        }
        log.info("Contact number does not exist in the repository: {}", contactNumber);
        return "";
    }

    @Override
    public String validateGetPanCardNumber(String panCardNumber) {
        List<String> pan=repository.getPanCardNumber(panCardNumber);
        for(String str:pan){
            if(str.equals(panCardNumber)){
                log.info("PAN card number already exists: {}", panCardNumber);
                return " PanCard Number Already exits";
            }
        }
        log.info("PAN card number does not exist in the repository: {}", panCardNumber);
        return "";
    }

    @Override
    public String validateGetAadharNumber(long aadharNumber) {
        List<Long> number=repository.getAadharNumber(aadharNumber);
        for(Long aadhar:number){
            if(aadhar==aadharNumber){
                log.info("Aadhar number already exists: {}", aadharNumber);
                return " AadharNumber Already exits";
            }
        }
        log.info("Aadhar number does not exist in the repository: {}", aadharNumber);
        return "";
    }

    @Override
    public RegisterDto validateGetRegisterInfo(int id) {
        if (id != 0) {
            log.info("Fetching register info for ID: {}", id);
            RegisterDto registerDto = repository.getRegisterInfo(id);
            if (registerDto != null) {
                log.info("Register info retrieved successfully for ID: {}", id);
                return registerDto;
            } else {
                log.warn("No register info found for ID: {}", id);
                return null;
            }
        } else {
            log.warn("Invalid ID provided for fetching register info");
            return null;
        }
    }

    @Override
    public RegisterDto validateGetInfoByEmail(String email) {
        if (email != null) {
            log.info("Fetching info for email: {}", email);
            RegisterDto registerDto = repository.getInfoByEmail(email);
            if (registerDto != null) {
                log.info("Info retrieved successfully for email: {}", email);
                return registerDto;
            } else {
                log.warn("No info found for email: {}", email);
                return null;
            }
        } else {
            log.warn("Invalid email provided for fetching info");
            return null;
        }
    }

    @Override
    public List<PropertyEntity> validateGetProperty(int id) {
        if (id != 0) {
            log.info("Fetching properties for ID: {}", id);
            List<PropertyEntity> properties = repository.getProperty(id);
            if (properties != null && !properties.isEmpty()) {
                log.info("Properties retrieved successfully for ID: {}", id);
                return properties;
            } else {
                log.warn("No properties found for ID: {}", id);
                return Collections.emptyList();
            }
        } else {
            log.warn("Invalid ID provided for fetching properties");
            return Collections.emptyList();
        }
    }

    @Override
    public boolean validateUpdateOTPByEmail(String otp, String email) {
        if (otp != null && email != null) {
            log.info("Updating OTP for email: {}", email);
            boolean isUpdated = repository.updateOTPByEmail(otp, email);
            if (isUpdated) {
                log.info("OTP updated successfully for email: {}", email);
            } else {
                log.warn("Failed to update OTP for email: {}", email);
            }
            return isUpdated;
        } else {
            if (otp == null) {
                log.warn("Invalid OTP provided for updating OTP for email: {}", email);
            }
            if (email == null) {
                log.warn("Invalid email provided for updating OTP");
            }
            return false;
        }
    }

    @Override
    public boolean validateUpdateAccountStatusByEmail(String accountStatus, String email) {
        if (accountStatus != null && email != null) {
            log.info("Updating account status '{}' for email: {}", accountStatus, email);
            boolean isUpdated = repository.updateAccountStatusByEmail(accountStatus, email);
            if (isUpdated) {
                log.info("Account status updated successfully for email: {}", email);
            } else {
                log.warn("Failed to update account status for email: {}", email);
            }
            return isUpdated;
        } else {
            if (accountStatus == null) {
                log.warn("Invalid account status provided for updating account status for email: {}", email);
            }
            if (email == null) {
                log.warn("Invalid email provided for updating account status");
            }
            return false;
        }
    }
    @Override
    public String validateGetOtpByEmail(String email) {
        if (email != null && !email.isEmpty()) {
            log.info("Fetching OTP for email: {}", email);
            String otp = repository.getOtpByEmail(email);
            if (otp != null) {
                log.info("OTP retrieved successfully for email: {}", email);
            } else {
                log.warn("No OTP found for email: {}", email);
            }
            return otp;
        } else {
            log.warn("Invalid email provided for retrieving OTP");
            return null;
        }
    }
    @Override
    public boolean validateUpdateById(RegisterDto dto, int id) {
        if (id != 0) {
            log.info("Updating Register information for ID: {}", id);
            Audit audit = new Audit();
            audit.setUpdatedBy(dto.getFirstName() + " " + dto.getLastName());
            audit.setUpdatedOn(LocalDateTime.now());
            dto.setAudit(audit);
            boolean isUpdated = repository.updateById(dto, id);

            if (isUpdated) {
                log.info("Register information updated successfully for ID: {}", id);
            } else {
                log.warn("Failed to update Register information for ID: {}", id);
            }
            return isUpdated;
        } else {
            log.warn("Invalid ID provided for updating Register information: {}", id);
            return false;
        }
    }
    @Override
    public boolean validateUpdateStatuesById(int id ) {
        if (id != 0) {
            log.info("Updating status for ID: {}", id);
            boolean isUpdated = repository.updateStatuesById(id);
            if (isUpdated) {
                log.info("Status updated successfully for ID: {}", id);
            } else {
                log.warn("Failed to update status for ID: {}", id);
            }
            return isUpdated;
        } else {
            log.warn("Invalid ID provided for updating status: {}", id);
            return false;
        }
    }
    @Override
    public BiddingDto getBiddingById(int id) {
        if (id != 0) {
            log.info("Fetching Bidding with ID: {}", id);
            BiddingDto biddingDto = repository.getBiddingById(id);
            if (biddingDto != null) {
                log.info("Bidding retrieved successfully for ID: {}", id);
            } else {
                log.warn("Bidding not found for ID: {}", id);
            }
            return biddingDto;
        } else {
            log.warn("Invalid ID provided for fetching Bidding: {}", id);
            return null;
        }
    }
    @Override
    public boolean validateDeleteById(int id) {
        if (id != 0) {
            log.info("Attempting to delete entry with ID: {}", id);
            boolean isDeleted = repository.deleteById(id);
            if (isDeleted) {
                log.info("Entry with ID {} deleted successfully", id);
            } else {
                log.warn("Failed to delete entry with ID {}", id);
            }
            return isDeleted;
        } else {
            log.warn("Invalid ID provided for delete operation: {}", id);
            return false;
        }
    }
    @Override
    public List<BiddingDto> validateGetBiddingInfoById(int id) {
        log.info("Fetching Bidding Info for ID: {}", id);
        List<BiddingDto> biddingInfo = repository.getBiddingInfoById(id);
        if (biddingInfo != null && !biddingInfo.isEmpty()) {
            log.info("Bidding Info retrieved successfully for ID: {}", id);
            return biddingInfo;
        } else {
            log.warn("No Bidding Info found for ID: {}", id);
            return Collections.emptyList();
        }
    }
    @Override
    public List<SoldBoughtDto> validateGetSellerDetailsById(int id) {
        if (id != 0) {
            log.info("Getting Seller Details for ID: {}", id);
            List<SoldBoughtDto> sellerDetails = repository.getSellerDetailsById(id);
            if (sellerDetails != null && !sellerDetails.isEmpty()) {
                log.info("Seller Details retrieved successfully for ID: {}", id);
                return sellerDetails;
            } else {
                log.warn("No Seller Details found for ID: {}", id);
            }
        } else {
            log.warn("Invalid ID provided for fetching Seller Details: {}", id);
        }
        return Collections.emptyList();
    }
    @Override
    public List<SoldBoughtDto> validateGetBuyerDetailsById(int id) {
        if (id != 0) {
            log.info("Getting Buyer Details Data for ID: {}", id);
            List<SoldBoughtDto> buyerDetails = repository.getBuyerDetailsById(id);
            if (buyerDetails != null && !buyerDetails.isEmpty()) {
                return buyerDetails;
            } else {
                log.warn("Buyer Details not found for ID: {}", id);
            }
        } else {
            log.warn("Invalid ID provided for fetching Buyer Details: {}", id);
        }
        return Collections.emptyList();
    }
}
