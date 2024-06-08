package com.xworkz.realestatemanagement.service;

import com.xworkz.realestatemanagement.dto.BiddingDto;
import com.xworkz.realestatemanagement.dto.PropertyDto;
import com.xworkz.realestatemanagement.dto.RegisterDto;
import com.xworkz.realestatemanagement.dto.SoldBoughtDto;
import com.xworkz.realestatemanagement.entity.PropertyEntity;

import java.io.IOException;
import java.util.List;

public interface RealestateManagementService {
    public boolean validateSaveRegisterInfo(RegisterDto dto);
    public void validateSavePropertyDTO(PropertyDto dto) throws IOException;
    public void savePropertyDto(int logIn,PropertyDto dto) throws IOException;
    public void validateSaveBiddingDto(BiddingDto dto);
    public void saveBidding(int id,int logIn, BiddingDto dto);
    public void validateSaveSoldBought(SoldBoughtDto dto);
    public void saveSoldBought(int id);
    public String validateGetEmail(String email);
    public String getEmailForLogin(String email);
    public boolean validateToGetEmail(String email);
    public String validateGetContactNumber(long contactNumber);
    public String validateGetPanCardNumber(String panCardNumber);
    public String validateGetAadharNumber(long aadharNumber);
    public RegisterDto validateGetRegisterInfo(int id);
    public RegisterDto validateGetInfoByEmail(String email);
    public List<PropertyEntity> validateGetProperty(int id);
    public boolean validateUpdateOTPByEmail(String otp,String email);
    public boolean validateUpdateAccountStatusByEmail(String accountStatus,String email);
    public String validateGetOtpByEmail(String email);
    public boolean validateUpdateById(RegisterDto dto, int id);
    public boolean validateUpdateStatuesById(int id);
    public BiddingDto getBiddingById(int id);
    public boolean validateDeleteById(int id);
    public PropertyEntity validateGetPropertyTypeById(int id);
    public List<BiddingDto> validateGetBiddingInfoById(int id);
    public List<SoldBoughtDto> validateGetSellerDetailsById(int id);
    public List<SoldBoughtDto> validateGetBuyerDetailsById(int id);
    public void updateOtp();

}
