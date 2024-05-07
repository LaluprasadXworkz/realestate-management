package com.xworkz.realestatemanagement.service;

public interface EmailSend {
    public boolean mailSend(String email,String otp);
    public void mailSend(String mail);
    public boolean mailSend(String email,String propertyType,String soldTo,long amount);
    public boolean mailSend(String email,String propertyType,String soldBy,long amount,String location);
}
