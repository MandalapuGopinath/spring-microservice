package com.spring.microservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "organization")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    private Long organizationId;
    @Column(name = "cas_status_id")
    private Short casStatusId;
    @Column(name = "party_nbr")
    private Integer partyNbr;
    @Column(name = "organization_name")
    private String organizationName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date",updatable = false)
    private Date createdDate;
    @Column(name = "created_by")
    private String createdBy;
    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
        updatedDate = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "contact_no")
    private String contactNo;
}
