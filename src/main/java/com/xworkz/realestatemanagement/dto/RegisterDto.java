package com.xworkz.realestatemanagement.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "register_info")
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Component
@NamedQueries({
        @NamedQuery(name = "getEmail", query = "select dto.email from RegisterDto dto "),
        @NamedQuery(name = "getContactNumber", query = "select dto.contactNumber from RegisterDto dto"),
        @NamedQuery(name = "getPanCardNumber", query = "select dto.panCardNumber from RegisterDto dto"),
        @NamedQuery(name = "getAadharNumber", query = "select dto.aadharNumber from RegisterDto dto "),
        @NamedQuery(name = "updateAudit", query = "UPDATE Audit a set a.updatedBy=:updatedBy,a.updatedOn=:updatedOn " +
                "where a.id in (select re.id from RegisterDto re where re.id=:i)"),
        @NamedQuery(name = "updateRegisterAndAuditById", query = "UPDATE RegisterDto r " +
                "SET r.firstName = :firstName, r.lastName = :lastName, r.email = :email, " +
                "r.alternativeEmail = :alternativeEmail, r.contactNumber = :contactNumber, " +
                "r.alternativeContact = :alternativeContact, r.currentAddress = :currentAddress, " +
                "r.permanentAddress = :permanentAddress, r.panCardNumber = :panCardNumber, " +
                "r.aadharNumber = :aadharNumber WHERE r.rid = :rid"),
        @NamedQuery(name = "getInfoByEmail", query = "select dto from RegisterDto dto where dto.email=:email"),
        @NamedQuery(name = "updateOTPByEmail", query = "update RegisterDto dto set dto.otp=:otp where dto.email=:email " +
                "and dto.accountStatus='Active'"),
        @NamedQuery(name = "updateAccountStatusByEmail", query = "update RegisterDto dto set dto.accountStatus=:accountStatus " +
                "where dto.email=:email"),
        @NamedQuery(name = "getOtpByEmail", query = "select dto.otp from RegisterDto dto where dto.email=:email" +
                " and dto.accountStatus='Active'"),
})
public class RegisterDto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;

    @Column(name = "First_Name")
    @NonNull
    private String firstName;

    @Column(name = "Last_Name")
    @NonNull
    private String lastName;

    @Column(name = "Email")
    @NonNull
    private String email;

    @Column(name = "Alternative_Email")
    @NonNull
    private String alternativeEmail;

    @Column(name = "Contact_Number")
    @NonNull
    private long contactNumber;

    @Column(name = "Alternative_Contact")
    @NonNull
    private long alternativeContact;

    @Column(name = "Current_Address")
    @NonNull
    private String currentAddress;

    @Column(name = "Permanent_Address")
    @NonNull
    private String permanentAddress;

    @Column(name = "Pan_Card_Number")
    @NonNull
    private String panCardNumber;

    @Column(name = "Aadhar_Number")
    @NonNull
    private long aadharNumber;

    @Column(name = "Otp")
    @NonNull
    private String otp;

    @Column(name = "Account_Status")
    @NonNull
    private String accountStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "audit_id", referencedColumnName = "id")
    private Audit audit;
}
