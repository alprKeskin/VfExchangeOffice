package io.github.alprkeskin.search.service.extcall;

import io.github.alprkeskin.search.utils.ValidCurrencies;
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
import java.util.Map;

@Service
public class CurrencyServiceClient {

    private final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openExchangeRatesCurrencyNamesUri}")
    private String CURRENCY_NAMES_URI;

    private Map<String, String> getCurrencyNames(URI targetUri) {
        return restTemplate.getForObject(targetUri, Map.class);
    }

    private ArrayList<String> getCurrencySymbols(URI targetUri) {
        return new ArrayList<>(getCurrencyNames(targetUri).keySet());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void saveCurrencyNames() {
        LOGGER.info("Currency names has been saved");
        ValidCurrencies.setValidCurrenciesList(getCurrencySymbols(URI.create(CURRENCY_NAMES_URI)));
    }

}
