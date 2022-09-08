package io.alprkeskin.wallet.service;

import io.alprkeskin.wallet.model.CurrencyRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyClientService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("http://localhost:8080/api/currency")
    private String targetUri;

    public double getCurrencyValue(final String currencyCode) {
        CurrencyRates currencyRates = restTemplate.getForObject(targetUri, CurrencyRates.class);
        // assert currencyRates != null; // BE CAREFUL!!!
        return currencyRates.getRates().get(currencyCode);
    }
}
