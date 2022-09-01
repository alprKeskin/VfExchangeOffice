package io.github.alprkeskin.search.utils;

import java.util.List;
import java.util.Set;

public class ValidCurrencies {
    private static Set<String> validCurrenciesList;

    public static boolean isValidCurrency(final String givenCurrency) {
        return validCurrenciesList.contains(givenCurrency);
    }

    public static void setValidCurrenciesList(final Set<String> listOfValidCurrencies) {
        validCurrenciesList = listOfValidCurrencies;
    }

}
