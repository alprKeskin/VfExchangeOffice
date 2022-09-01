package io.github.alprkeskin.search.service;

import io.github.alprkeskin.search.utils.ValidCurrencies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidCurrenciesTest {

    Set<String> validCurrencies;

    @BeforeEach
    void initializeFields() {
        validCurrencies = Stream.of("USD", "TRY", "AUD").collect(Collectors.toSet());
    }

    @Test
    void givenUSD_whenIsValidCurrencyCalled_thenReturnTrue() {
        ValidCurrencies.setValidCurrenciesList(validCurrencies);
        boolean actual = ValidCurrencies.isValidCurrency("USD");
        assertTrue(actual);
    }

    @Test
    void givenTRY_whenIsValidCurrencyCalled_thenReturnTrue() {
        ValidCurrencies.setValidCurrenciesList(validCurrencies);
        boolean actual = ValidCurrencies.isValidCurrency("TRY");
        assertTrue(actual);
    }

    @Test
    void givenAUD_whenIsValidCurrencyCalled_thenReturnTrue() {
        ValidCurrencies.setValidCurrenciesList(validCurrencies);
        boolean actual = ValidCurrencies.isValidCurrency("AUD");
        assertTrue(actual);
    }

    @Test
    void givenXXX_whenIsValidCurrencyCalled_thenReturnFalse() {
        ValidCurrencies.setValidCurrenciesList(validCurrencies);
        boolean actual = ValidCurrencies.isValidCurrency("XXX");
        assertFalse(actual);
    }

}