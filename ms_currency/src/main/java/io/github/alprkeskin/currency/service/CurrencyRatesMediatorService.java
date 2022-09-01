package io.github.alprkeskin.currency.service;

import io.github.alprkeskin.currency.model.CurrencyRates;
import io.github.alprkeskin.currency.service.ext.call.open.exchange.OpenExchangeRatesMediatorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;


@Service
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRatesMediatorService {

    @Autowired
    private OpenExchangeRatesMediatorService exchangeRatesMediatorService;
    @Autowired
    private CurrencyPersistanceService currencyPersistanceService;

    public CurrencyRates getCurrencyRates(LocalDate date) {
        Optional<CurrencyRates> currencyRates = currencyPersistanceService.findCurrencyRatesById(date);
        if (currencyRates.isEmpty()) {
            CurrencyRates currencyRatesOfRelatedDate = exchangeRatesMediatorService.getCurrencyRates(date);
            currencyPersistanceService.saveCurrencyRates(currencyRatesOfRelatedDate, date);
            return currencyRatesOfRelatedDate;
        }
        return currencyRates.get();
    }

    public Map getCurrencies() {
        return exchangeRatesMediatorService.getCurrencies();
    }
}
