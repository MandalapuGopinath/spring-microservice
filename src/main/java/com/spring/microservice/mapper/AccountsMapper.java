package com.spring.microservice.mapper;

import com.spring.microservice.dto.AccountsDto;
import com.spring.microservice.entity.Accounts;

public class AccountsMapper {

    public AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountName(accounts.getAccountName());
        accountsDto.setTaxCode(accounts.getTaxCode());
        accountsDto.setCreatedBy(accountsDto.getCreatedBy());
        accountsDto.setCreatedDate(accounts.getCreatedDate());
        accountsDto.setUpdatedBy(accountsDto.getUpdatedBy());
        accountsDto.setUpdatedDate(accounts.getUpdatedDate());
        return accountsDto;
    }

    public Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountName(accountsDto.getAccountName());
        accounts.setCreatedDate(accountsDto.getCreatedDate());
        accounts.setCreatedBy(accountsDto.getCreatedBy());
        accounts.setUpdatedBy(accountsDto.getUpdatedBy());
        accounts.setUpdatedDate(accountsDto.getUpdatedDate());
        accounts.setTaxCode(accountsDto.getTaxCode());
        return accounts;
    }
}
