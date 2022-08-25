package io.github.alprkeskin.currency.service.exchangeRates;

import io.github.alprkeskin.currency.model.CurrencyRates;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@AllArgsConstructor
public class ExchangeRatesMediatorService {

    @Autowired
    private final CurrencyRatesService currencyRatesService;

    @Autowired
    private final CurrenciesService currenciesService;

    public CurrencyRates getCurrencyRates(LocalDate date, String symbols) {
        return currencyRatesService.getCurrencyRates(date, symbols);
    }

    public Map getCurrencies() {
        return currenciesService.getCurrencies();
    }

}
