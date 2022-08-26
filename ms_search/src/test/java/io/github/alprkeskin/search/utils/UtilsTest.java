package io.github.alprkeskin.search.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void givenUSDAndNullDate_whenGetIdWithDateAndCurrencyNameCalled_thenReturnCurrentDateAndUSD() {
        String expected = LocalDate.now().toString() + " " + "(USD)";
        String actual = Utils.getIdWithDateAndCurrencyName("USD");
        assertEquals(expected, actual);
    }

    @Test
    void givenTRYAndNullDate_whenGetIdWithDateAndCurrencyNameCalled_thenReturnCurrentDateAndUSD() {
        String expected = LocalDate.now().toString() + " " + "(TRY)";
        String actual = Utils.getIdWithDateAndCurrencyName("TRY");
        assertEquals(expected, actual);
    }

    @Test
    void givenTRYAndRandomDate_whenGetIdWithDateAndCurrencyNameCalled_thenReturnGivenDateAndUSD() {
        String expected = "2012-07-08 (TRY)";
        String actual = Utils.getIdWithDateAndCurrencyName("TRY", "2012-07-08");
        assertEquals(expected, actual);
    }

}