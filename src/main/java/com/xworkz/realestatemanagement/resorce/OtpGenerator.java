package com.xworkz.realestatemanagement.resorce;

import java.security.SecureRandom;

public class OtpGenerator {
    private static  final  String OTP_CHARACTERS="0123456789";
    private static final int OTP_LENGTH=6;

    public static String generateOTP(){
        SecureRandom secureRandom=new SecureRandom();
        StringBuilder otp=new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            int index=secureRandom.nextInt(OTP_CHARACTERS.length());
            otp.append(OTP_CHARACTERS.charAt(index));
        }
        return otp.toString();
    }

    public static void main(String[] args) {
        String otp=generateOTP();
        System.out.println("Generated OTP: "+otp);
    }
}
