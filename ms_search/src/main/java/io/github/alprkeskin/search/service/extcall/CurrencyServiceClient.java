package io.github.alprkeskin.search.service.extcall;

import io.github.alprkeskin.search.utils.ValidCurrencies;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class CurrencyServiceClient {
    @Autowired
    private CurrencyFeignService currencyFeignService;

    @EventListener(ApplicationReadyEvent.class)
    public void saveCurrencyNames() {
        log.info("CurrencyServiceClient::saveCurrencyNames()");
        Set<String> currencies = currencyFeignService.getCurrencies().getBody().keySet();
        ValidCurrencies.setValidCurrenciesList(currencies);
    }

}
