package io.github.alprkeskin.search.utils;

import java.time.LocalDate;

public class DateUtils {
    public static String getCurrentDateAsString(final LocalDate... date) {
        if (date.length != 0) return date.toString();
        return LocalDate.now().toString();
    }
}
