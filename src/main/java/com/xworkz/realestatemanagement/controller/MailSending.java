package com.xworkz.realestatemanagement.controller;


import com.xworkz.realestatemanagement.configuration.MailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

public class MailSending {
    public static void main(String[] args) {
        String userName="Lalu";
        String email="laluprasad245527@gmail.com";
        MailSending mailSending=new MailSending();
        mailSending.mailSend(email,userName);
    }

    @Autowired
    private MailConfiguration configuartion;
    public boolean mailSend(String email,String otp) {
        try {
            SimpleMailMessage message =new SimpleMailMessage();
            message.setFrom("laluprasad245527@gmail.com");
            message.setTo(email);
            message.setSubject("OTP for login");
            message.setText("Your otp for admin login is"+otp);
            configuartion.mailConfig().send(message);
            System.out.println("mail send successfully");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
