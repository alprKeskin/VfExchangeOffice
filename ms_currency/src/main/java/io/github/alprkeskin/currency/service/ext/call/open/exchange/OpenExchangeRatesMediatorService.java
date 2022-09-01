package io.github.alprkeskin.currency.service.ext.call.open.exchange;

import io.github.alprkeskin.currency.model.CurrencyRates;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@AllArgsConstructor
public class OpenExchangeRatesMediatorService {

    @Autowired
    private final OpenExchangeRatesService currencyRatesService;

    @Autowired
    private final OpenExchangeCurrenciesService openExchangeCurrenciesService;

    public CurrencyRates getCurrencyRates(LocalDate date) {
        return currencyRatesService.getCurrencyRates(date);
    }

    public Map getCurrencies() {
        return openExchangeCurrenciesService.getCurrencies();
    }

}
