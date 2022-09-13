package io.github.alprkeskin.currency.service.consumer;

import io.github.alprkeskin.common.model.SearchedCurrency;
import io.github.alprkeskin.currency.model.CurrencyRates;
import io.github.alprkeskin.currency.repositories.CurrencyRatesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
class SearchedCurrenyConsumer {
    @Autowired
    private CurrencyRatesRepository repository;

    @Value("${alprkeskin.arbitrage.rate:0.01}")
    private double arbitrageRate;

    @RabbitListener(queues = {"#{'${alprkeskin.queues.from.search}'.concat('-queue')}"})
    public void doArbitrage(SearchedCurrency searchedCurrency) {
        log.info("MutatedCurrencyValueConsumer::doArbitrage(SearchedCurrency searchedCurrency = {})", searchedCurrency);
        Optional<CurrencyRates> currencyRatesOptional = repository.findById(searchedCurrency.getDate());
        if(currencyRatesOptional.isEmpty()) return;
        CurrencyRates rates = currencyRatesOptional.get();
        rates.getRates().computeIfPresent(searchedCurrency.getCurrencyCode(), this::calculateRate);
        repository.save(rates);
    }

    private double calculateRate(String key, double oldRate) {
        log.info("SearchedCurrencyConsumer::calculateRate(String key = {}, double oldRate = {})", key, oldRate);
        return oldRate * (1.0 + arbitrageRate);
    }
}
