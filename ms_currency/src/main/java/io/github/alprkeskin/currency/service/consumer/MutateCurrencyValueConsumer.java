package io.github.alprkeskin.currency.service.consumer;

import io.github.alprkeskin.common.model.MutatedCurrency;
import io.github.alprkeskin.currency.model.CurrencyRates;
import io.github.alprkeskin.currency.repositories.CurrencyRatesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class MutateCurrencyValueConsumer {
    @Autowired
    private CurrencyRatesRepository repository;

    @Value("1.00")
    private double arbitrageRate;

    @RabbitListener(queues = {"#{'${alprkeskin.queues.from.wallet}'.concat('-queue')}"})
    public void doArbitrage(MutatedCurrency mutatedCurrency) {
        log.info("MutatedCurrencyValueConsumer::doArbitrage(MutatedCurrency mutatedCurrency = {})", mutatedCurrency);
        Optional<CurrencyRates> currencyRatesOptional = repository.findById(LocalDate.now().toString());
        if (currencyRatesOptional.isEmpty()) return;

        CurrencyRates currencyRates = currencyRatesOptional.get();

        currencyRates.getRates().computeIfPresent(mutatedCurrency.getCurrencyCode(), this::calculateRate);
        repository.save(currencyRates);
    }

    private double calculateRate(String key, double oldRate) {
        log.info("MutatedCurrencyValueConsumer::calculateRate(String key = {}, double oldRate = {})", key, oldRate);
        return oldRate * (1.0 + arbitrageRate);
    }
}
