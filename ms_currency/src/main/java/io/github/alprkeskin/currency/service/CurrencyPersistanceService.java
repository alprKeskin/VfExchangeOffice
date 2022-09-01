package io.github.alprkeskin.currency.service;

import io.github.alprkeskin.currency.model.CurrencyRates;
import io.github.alprkeskin.currency.repositories.CurrencyRatesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
class CurrencyPersistanceService {
    @Autowired
    private CurrencyRatesRepository currencyRatesRepository;

    public Optional<CurrencyRates> findCurrencyRatesById(LocalDate localDate) {
        return currencyRatesRepository.findById(localDate.toString());
    }

    public void saveCurrencyRates(CurrencyRates currencyRatesObject, LocalDate localDate) {
        currencyRatesObject.setId(localDate.toString());
        currencyRatesRepository.save(currencyRatesObject);
    }
}
