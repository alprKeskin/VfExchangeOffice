package io.github.alprkeskin.search.service;

import java.util.List;

public class ValidCurrencies {
    private static List<String> validCurrenciesList;

    public static boolean isValidCurrency(final String givenCurrency) {
        return validCurrenciesList.contains(givenCurrency);
    }

    public static void setValidCurrenciesList(final List<String> listOfValidCurrencies) {
        validCurrenciesList = listOfValidCurrencies;
    }

}
