package io.github.alprkeskin.currency.utils;

import java.time.LocalDate;

public final class DateUtil {
    /**
     * It returns a LocalDate object that corresponds to the given string
     * If the date is a future date, it throws a runtime exception
     * **/
    public static LocalDate getLocalDate(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr.substring(0, 10));

        if (date.compareTo(LocalDate.now()) >= 0) {
            throw new RuntimeException("Requested date cannot be greater than or equal to today!");
        }
        return date;
    }
}
