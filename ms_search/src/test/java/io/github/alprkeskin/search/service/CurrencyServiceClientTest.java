package io.github.alprkeskin.search.service;

import io.github.alprkeskin.search.service.extcall.CurrencyServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceClientTest {
    @InjectMocks
    private CurrencyServiceClient currencyServiceClient;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void initializeFields() {
        ReflectionTestUtils.setField(currencyServiceClient,"CURRENCY_NAMES_URI", "http://localhost:8082/open_exchange_rates_api/currency/currencies");
        when(restTemplate.getForObject(any(URI.class), eq(Map.class))).thenReturn(new HashMap<>());
    }

    @Test
    void whenSaveCurrencyNamesCalled_thenSaveCurrencyNames() {
        currencyServiceClient.saveCurrencyNames();

    }
}