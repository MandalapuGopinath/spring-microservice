package com.spring.microservice.config;

import java.text.SimpleDateFormat;
import java.util.*;

public class AccountsPayloadGenerator {

    public static List<Map<String, Object>> generatePayloadsForLastFourMonths(int recordsPerMonth, String accountName, String taxCode, String createdBy, String updatedBy) {
        List<Map<String, Object>> payloads = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        for (int i = 0; i < 4; i++) {
            calendar = Calendar.getInstance(); // reset calendar to current date each month iteration
            calendar.add(Calendar.MONTH, -i);
            Date monthDate = calendar.getTime();

            for (int j = 0; j < recordsPerMonth; j++) {
                Map<String, Object> payload = new HashMap<>();

                // Generate random account number
                Long randomAccountNumber = generateAccountNumber();
                payload.put("accountName", accountName);
                payload.put("taxCode", taxCode);
                payload.put("createdBy", createdBy);
                payload.put("updatedBy", updatedBy);

                // Adjust calendar to spread records within the month
                calendar.setTime(monthDate);
                calendar.set(Calendar.DAY_OF_MONTH, 1 + (j * 3) % calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // spread records within the month
                Date date = calendar.getTime();

                payload.put("createdDate", sdf.format(date));
                payload.put("updatedDate", sdf.format(date));

                payloads.add(payload);
            }
        }

        return payloads;
    }

    private static Long generateAccountNumber() {
        long lowerBound = 1000000000L;
        long upperBound = 9999999999L;
        Random random = new Random();
        return lowerBound + (long) (random.nextDouble() * (upperBound - lowerBound + 1));
    }

    public static void main(String[] args) {
        List<Map<String, Object>> payloads = generatePayloadsForLastFourMonths(10, "Test Account", "TX1234", "Admin", "Admin");

        for (Map<String, Object> payload : payloads) {
            System.out.println(payload);
        }
    }
}
