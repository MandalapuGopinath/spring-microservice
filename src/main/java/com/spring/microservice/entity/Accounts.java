package com.spring.microservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "account_name")
    private String accountName;
    private String taxCode;
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

}
