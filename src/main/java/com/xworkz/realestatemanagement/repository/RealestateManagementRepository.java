package com.xworkz.realestatemanagement.repository;

import com.xworkz.realestatemanagement.dto.BiddingDto;
import com.xworkz.realestatemanagement.dto.PropertyDto;
import com.xworkz.realestatemanagement.dto.RegisterDto;
import com.xworkz.realestatemanagement.dto.SoldBoughtDto;
import com.xworkz.realestatemanagement.entity.PropertyEntity;

import java.util.List;

public interface RealestateManagementRepository {
    public boolean saveRegisterInfo(RegisterDto dto);
    public void savePropertyDTO(PropertyEntity dto);
    public  void saveBiddingDto(BiddingDto dto);
    public  void saveSoldBought(SoldBoughtDto dto);
    public List<String> getEmail(String email);
    public List<String> getEmailForLogin(String email);
    public List<Long> getContactNumber(long contactNumber);
    public List<String> getPanCardNumber(String panCardNumber);
    public List<Long> getAadharNumber(long aadharNumber);
    public RegisterDto getRegisterInfo(int id);
    public List<PropertyEntity> getProperty(int id);
    public RegisterDto getInfoByEmail(String email);
    public boolean updateById(RegisterDto dto,int id);
    public boolean updateOTPByEmail(String otp,String email);
    public String getOtpByEmail(String email);
    public boolean updateAccountStatusByEmail(String accountStatus,String email);
    public boolean updateStatuesById(int id);
    public BiddingDto getBiddingById(int id);
    public boolean deleteById(int rid);
    public PropertyEntity getPropertyTypeById(int id);
    public List<BiddingDto> getBiddingInfoById(int id);
    public List<SoldBoughtDto> getSellerDetailsById(int id);
    public List<SoldBoughtDto> getBuyerDetailsById(int id);


}
