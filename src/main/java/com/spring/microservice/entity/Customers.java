package com.spring.microservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "customer_type")
    private String customerType;
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
    @Column(name = "party_nbr")
    private Integer partyNbr;
private
}

