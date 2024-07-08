package com.spring.microservice.controller;

import com.spring.microservice.config.AccountEnum;
import com.spring.microservice.constants.AccountsConstants;
import com.spring.microservice.dto.AccountsDto;
import com.spring.microservice.dto.ResponseDto;
import com.spring.microservice.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @PostMapping("/account/created")
    public ResponseEntity<AccountsDto> createAccount(@RequestBody AccountsDto accountsDto) {
        accountsService.addAccount(accountsDto);
        return new ResponseEntity<>(accountsDto, HttpStatus.CREATED);
    }

    @PostMapping("/accountList/created")
    public ResponseEntity<ResponseDto> createAccounts(@RequestBody List<AccountsDto> accountsDtos) {
        accountsService.addAccounts(accountsDtos);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @PutMapping("/account/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody AccountsDto accountsDto) {
        accountsService.updateAccounts(accountsDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }

    @GetMapping("/generate")
    public ResponseEntity<Map<String, Map<AccountEnum, Map<String, Object>>>> generateReport(
            @RequestParam Date fromDate,
            @RequestParam Date toDate,
            @RequestParam String type) {

        Map<String, Map<AccountEnum, Map<String, Object>>> reportData = accountsService.generateReport(fromDate, toDate, type);
        return ResponseEntity.ok(reportData);
    }
}
