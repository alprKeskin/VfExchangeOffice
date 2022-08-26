package io.github.alprkeskin.search.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OpenExchangeRatesCurrencyServiceCallerTest {

    private OpenExchangeRatesCurrencyServiceCaller openExchangeRatesCurrencyServiceCaller;

    @BeforeEach
    void initializeFields() {
        openExchangeRatesCurrencyServiceCaller = new OpenExchangeRatesCurrencyServiceCaller(new RestTemplate(), "http://localhost:8082/open_exchange_rates_api/currency/currencies");
    }

    @Test
    void whenSaveCurrencyNamesCalled_thenSaveCurrencyNames() {
        openExchangeRatesCurrencyServiceCaller.saveCurrencyNames();
        // some code here
    }
}