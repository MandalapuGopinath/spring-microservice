package com.spring.microservice.mapper;

import com.spring.microservice.dto.CustomersDto;
import com.spring.microservice.entity.Customers;

public class CustomerMapper {

    public CustomersDto mapToCustomerDto(Customers customers, CustomersDto customersDto) {
        customersDto.setCustomerType(customers.getCustomerType());
        customersDto.setPartyNbr(customers.getPartyNbr());
        customersDto.setCreatedBy(customersDto.getCreatedBy());
        customersDto.setCreatedDate(customersDto.getCreatedDate());
        customersDto.setUpdatedBy(customersDto.getUpdatedBy());
        customersDto.setUpdatedDate(customersDto.getUpdatedDate());
        return customersDto;
    }

    public Customers mapToCustomer(CustomersDto customersDto, Customers customers) {
        customers.setCustomerType(customersDto.getCustomerType());
        customers.setPartyNbr(customersDto.getPartyNbr());
        customers.setCreatedBy(customersDto.getCreatedBy());
        customers.setCreatedDate(customersDto.getCreatedDate());
        customers.setUpdatedDate(customersDto.getUpdatedDate());
        customers.setUpdatedBy(customersDto.getUpdatedBy());
        return customers;
    }
}
