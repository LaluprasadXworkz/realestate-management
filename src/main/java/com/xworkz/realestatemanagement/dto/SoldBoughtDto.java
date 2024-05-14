package com.xworkz.realestatemanagement.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "SoldBought_Info")
@Setter
@ToString
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Component
@NamedQueries({
        @NamedQuery(name = "getSellerDetailsById",query = "select dto from SoldBoughtDto dto where dto.sellerId=:sellerId"),
        @NamedQuery(name = "getBuyerDetailsById",query = "select dto from SoldBoughtDto dto where dto.buyerId=:buyerId")
})
public class SoldBoughtDto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "property_Id")
    @NonNull
    private int  propertyId;

    @Column(name = "Property_Type")
    @NonNull
    private String propertyType;

    @Column(name = "Sold_To")
    @NonNull
    private String soldTo;

    @Column(name = "Seller_Id")
    @NonNull
    private int sellerId;

    @Column(name = "Sold_By")
    @NonNull
    private String soldBy;

    @Column(name = "Buyer_Id")
    @NonNull
    private int buyerId;

    @Column(name = "Location")
    @NonNull
    private String location;

    @Column(name = "Price")
    @NonNull
    private long price;
}
