package com.wachala.rentaltests.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static DateTimeFormatter dayMonthYearFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static DateTimeFormatter yearMonthDayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, yearMonthDayFormatter);
    }
}
