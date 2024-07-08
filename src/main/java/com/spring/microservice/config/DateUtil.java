package com.spring.microservice.config;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {
    public static LocalDate getFirstDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate getLastDayOfMonth(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }
/*

    public static List<LocalDate> getLastThreeMonths() {
        LocalDate currentDate = LocalDate.now();
        List<LocalDate> months = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            months.add(currentDate.minusMonths(i).with(TemporalAdjusters.firstDayOfMonth()));
        }
        return months;
    }

    public static List<LocalDate> getWeeksOfMonth(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        List<LocalDate> weeks = new ArrayList<>();
        while (startDate.getMonthValue() == month) {
            weeks.add(startDate);
            startDate = startDate.plusWeeks(1);
        }
        return weeks;
    }*/
}

