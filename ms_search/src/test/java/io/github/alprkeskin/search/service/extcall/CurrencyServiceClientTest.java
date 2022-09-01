package io.github.alprkeskin.search.service.extcall;

import io.github.alprkeskin.search.service.extcall.CurrencyServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
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
    private CurrencyFeignService feignService;

    @BeforeEach
    void initializeFields() {
       when(feignService.getCurrencies()).thenReturn(ResponseEntity.ok(new HashMap<>()));
    }

    @Test
    void whenSaveCurrencyNamesCalled_thenSaveCurrencyNames() {
        currencyServiceClient.saveCurrencyNames();
    }
}