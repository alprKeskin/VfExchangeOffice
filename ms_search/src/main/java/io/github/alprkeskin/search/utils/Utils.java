package io.github.alprkeskin.search.utils;

public class Utils {
    /**
     * If date is given, it produces the id using given currency name and date.
     * Otherwise, it produces the id using given currency name and the current date as default.
     * **/
    public static String getIdWithDateAndCurrencyName(final String currencyName, final String... date) {
        if (date.length != 0) {
            return date[0] + " (" + currencyName + ")";
        }
        return DateUtils.getDateAsString() + " " + "(" + currencyName + ")";
    }
}
