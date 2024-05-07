package com.xworkz.realestatemanagement.dto;

import com.xworkz.realestatemanagement.entity.PropertyEntity;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "Bidding_info")
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Component
@ToString
@NamedQueries({
        @NamedQuery(name = "getBiddingInfoById", query = "select dto from BiddingDto dto where dto.bidderId != :rid" +
                " and dto.propertyId.id in (select dto.id from PropertyEntity dto where dto.register.id=:pid and dto.statues='forSale') order by dto.amount desc"),
        @NamedQuery(name = "getBiddingById",query = "select dto from BiddingDto dto where dto.id=:id")

})

public class BiddingDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Property_Type")
    @NonNull
    private String propertyType;

    @Column(name = "Bidder_Name")
    @NonNull
    private String bidderName;

    @Column(name = "Bidder_Id")
    @NonNull
    private int  bidderId;

    @Column(name = "Amount")
    @NonNull
    private long amount;

    @Column(name = "Location")
    @NonNull
    private String location;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "pId")
    private PropertyEntity propertyId;

}
