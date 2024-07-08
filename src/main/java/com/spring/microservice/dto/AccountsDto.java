package com.spring.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {

    private Long accountNumber;
    private String accountName;
    private Date accActiveDt;
    private String taxCode;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
}
