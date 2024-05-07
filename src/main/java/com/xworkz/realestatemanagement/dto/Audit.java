package com.xworkz.realestatemanagement.dto;


import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "realEstate_meta_info")
@Component

public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Created_By")
    @NonNull
    private String createdBy;

    @Column(name = "Created_On")
    @NonNull
    private LocalDateTime createdOn;

    @Column(name = "Updated_By")
    @NonNull
    private String updatedBy;

    @Column(name = "Updated_On")
    @NonNull
    private LocalDateTime updatedOn;

    @NonNull
    @OneToOne(mappedBy = "audit")
    private RegisterDto dto;
}
