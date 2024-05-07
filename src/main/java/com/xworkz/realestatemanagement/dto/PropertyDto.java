package com.xworkz.realestatemanagement.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Component
@Data
public class PropertyDto {

    @Id
    private int id;

    private String propertyType;

    private String ownerName;

    private String squareFeet;

    private String price;

    private String location;

    private String pinCode;

    private String statues;

    private String propertyImage;

    private MultipartFile multipartFile;

    private RegisterDto register;

}
