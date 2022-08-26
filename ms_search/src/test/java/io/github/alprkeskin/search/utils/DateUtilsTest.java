package io.github.alprkeskin.search.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void givenNull_whenGetDateAsStringCalled_thenReturnCurrentDate() {
        String expected = LocalDate.now().toString();
        String actual = DateUtils.getDateAsString();
        assertEquals(expected, actual);
    }

    @Test
    void givenRandomDate_whenGetDateAsStringCalled_thenReturnCurrentDate() {
        String expected = "2001-07-08";
        String actual = DateUtils.getDateAsString(LocalDate.parse(expected));
        assertEquals(expected, actual);
    }
}