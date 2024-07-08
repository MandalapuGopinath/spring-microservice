package com.spring.microservice.config;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class MonthlyDataResponse {

    private Map<String, Map<String, Map<String, Integer>>> monthlyData;

    public MonthlyDataResponse() {
        this.monthlyData = new HashMap<>();
    }

    public void addMonthData(String month, String businessType, int accountName) {
        // Initialize maps if not already present
        monthlyData.putIfAbsent(month, new HashMap<>());
        monthlyData.get(month).putIfAbsent(businessType, new HashMap<>());

        // Add accountName count
        monthlyData.get(month).get(businessType).put("accountName", accountName);
    }

    public static void main(String[] args) {
        MonthlyDataResponse response = new MonthlyDataResponse();

        // Example data population (replace with your actual data fetching logic)
        response.addMonthData("January", "SME", 10);
        response.addMonthData("January", "Government", 10);
        response.addMonthData("January", "Enterprise", 10);
        response.addMonthData("January", "GrandTotal", 30);

        response.addMonthData("February", "SME", 10);
        response.addMonthData("February", "Government", 10);
        response.addMonthData("February", "Enterprise", 10);
        response.addMonthData("February", "GrandTotal", 30);

        // Printing the response
        System.out.println(response.getMonthlyData());
    }
}
