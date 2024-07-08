package com.spring.microservice.config;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {

    public static List<Date> getLastThreeMonths() {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 4; i++) {
            dates.add(calendar.getTime());
            calendar.add(Calendar.MONTH, -1);
        }

        return dates;
    }

    public static List<Date> getWeeklyDatesForMonth(String month) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        switch (month.toLowerCase()) {
            case "january":
                calendar.set(Calendar.MONTH, Calendar.JANUARY);
                break;
            case "february":
                calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
                break;
            // Add cases for other months as needed
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        while (calendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
            dates.add(calendar.getTime());
            calendar.add(Calendar.WEEK_OF_MONTH, 1);
        }

        return dates;
    }
}

