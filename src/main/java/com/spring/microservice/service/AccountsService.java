package com.spring.microservice.service;

import com.spring.microservice.config.AccountEnum;
import com.spring.microservice.config.AccountResponse;
import com.spring.microservice.dto.AccountsDto;
import com.spring.microservice.dto.CustomersDto;
import com.spring.microservice.dto.OrganizationDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AccountsService {

    void addAccount(AccountsDto accountsDto);

    void addAccounts(List<AccountsDto> accountsDtos);

    boolean updateAccounts(AccountsDto accountsDto);

    void addOrganization(OrganizationDto organizationDto);

    void addCustomers(CustomersDto customersDto);

    //public Map<String, Map<String, Map<String, Integer>>> fetchData();
//Map<String, Map<AccountEnum, Integer>> fetchData(String monthly, String weekly);
    //AccountResponse getAccountsData(boolean isMonthly, String weekly);
//public List<Map<String, Map<AccountEnum, Integer>>> fetchData(String monthly, String weekly);
    public List<Map<String, Object>> getAccountsData(String monthly, String weekly);
    public Map<String, Map<AccountEnum, Map<String, Object>>> generateReport(Date fromDate, Date toDate, String type);
}
