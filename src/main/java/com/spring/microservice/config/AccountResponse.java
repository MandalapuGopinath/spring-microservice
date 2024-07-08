package com.spring.microservice.config;

import lombok.Data;

import java.util.Map;

@Data
public class AccountResponse {

    private Map<String, Map<AccountEnum, Integer>> data;

}
