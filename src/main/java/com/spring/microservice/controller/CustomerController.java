package com.spring.microservice.controller;

import com.spring.microservice.constants.AccountsConstants;
import com.spring.microservice.dto.AccountsDto;
import com.spring.microservice.dto.CustomersDto;
import com.spring.microservice.dto.OrganizationDto;
import com.spring.microservice.dto.ResponseDto;
import com.spring.microservice.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CustomerController {

    @Autowired
    private AccountsService accountsService;


    @PostMapping("customers/created")
    public ResponseEntity<CustomersDto> createCustomer(@RequestBody CustomersDto customersDto) {
        accountsService.addCustomers(customersDto);
        return new ResponseEntity<>(customersDto, HttpStatus.CREATED);
    }

}
