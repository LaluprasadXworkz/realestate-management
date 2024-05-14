package com.xworkz.realestatemanagement.entity;

import com.xworkz.realestatemanagement.dto.RegisterDto;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "Property_info")
@ToString
@NamedQueries({
        @NamedQuery(name = "getProperty",query = "select dto from PropertyEntity dto where dto.register.id!=:id and dto.statues='forSale'"),
        @NamedQuery(name = "getPropertyTypeById",query = "select dto from PropertyEntity dto where dto.id=:id"),
        @NamedQuery(name = "updateStatuesById",query = "update PropertyEntity dto set dto.statues =:statues where dto.id=:id")
})

public class PropertyEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pId")
    private int id;
    @Column(name = "Property_Type")
    @NonNull
    private String propertyType;

    @Column(name = "Owner_Name")
    @NonNull
    private String ownerName;

    @Column(name = "Square_Feet")
    @NonNull
    private String squareFeet;

    @Column(name = "price")
    @NonNull
    private String price;

    @Column(name = "location")
    @NonNull
    private String location;

    @Column(name = "Pin_Code")
    @NonNull
    private String pinCode;

    @Column(name = "Statues")
    @NonNull
    private String statues;

    @Column(name = "Property_Image")
    @NonNull
    private String propertyImage;

    @ManyToOne
    @JoinColumn(name = "register_id", referencedColumnName = "id")
    private RegisterDto register;
}
