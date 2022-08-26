package io.github.alprkeskin.search.utils;

import java.time.LocalDate;

public class DateUtils {
    public static String getDateAsString(final LocalDate... date) {
        if (date.length != 0) return date[0].toString();
        return LocalDate.now().toString();
    }
}
