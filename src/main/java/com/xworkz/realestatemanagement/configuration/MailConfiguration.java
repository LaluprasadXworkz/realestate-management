package com.xworkz.realestatemanagement.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    public JavaMailSenderImpl mailConfig(){

        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("laluprasad245527@gmail.com");
        mailSender.setPassword("lbxh eqgw jgba taix");
        Properties props=mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
//        props.put("mail.debug","true");

        return mailSender;

    }
}
