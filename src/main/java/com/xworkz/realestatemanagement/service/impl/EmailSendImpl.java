package com.xworkz.realestatemanagement.service.impl;

import com.xworkz.realestatemanagement.configuration.MailConfiguration;
import com.xworkz.realestatemanagement.service.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailSendImpl implements EmailSend {

    @Autowired
    MailConfiguration configuration;

    @Override
    public boolean mailSend(String email, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("OTP for Login");
            message.setText("OTP for login: " + otp +"\n\nNote:- Otp will Expire in 2 Minutes");
            configuration.mailConfig().send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void mailSend(String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Registration Successful");
            message.setText("Welcome to Xworkz RealEstate, your account is successfully created.\n You can access our services by logging into your account.");
            configuration.mailConfig().send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean mailSend(String email,String propertyType,String soldTo,long amount) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Congratulation your Property is Sold");
            message.setText(" Property type: "+propertyType+"\n\n Buyer Name: "+soldTo+"\n\n Amount: "+amount+"\n\n Thank You");
            configuration.mailConfig().send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean mailSend(String email,String propertyType,String soldBy,long amount,String location){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Congratulation.!");
            message.setText(" Property type: "+propertyType+"\n\n Location: "+location+"\n\n Seller Name: "
                    +soldBy+"\n\n Amount: "+amount+"\n\n Thank You");
            configuration.mailConfig().send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
