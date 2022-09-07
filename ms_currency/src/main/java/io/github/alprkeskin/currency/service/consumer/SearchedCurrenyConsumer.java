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
        // Parametre olarak verilen searchedCurrency'deki date'i (id) kullanarak ilgili tarihe ait olan currency rates'i çekiyor.
        Optional<CurrencyRates> currencyRatesOptional = repository.findById(searchedCurrency.getDate());
        // Eğer ilgili tarihe ait bir currency rates database'de yoksa işlem durduruluyor.
        // Bizim implementation'umuz doğruysa bunun olması mümkün değil
        if(currencyRatesOptional.isEmpty())
            return;
        // İlgili tarihe ait bir currency rate varsa bu currency rate'i optional'den kurtarıyoruz.
        CurrencyRates rates = currencyRatesOptional.get();
        // Arbitrage'ı uygula.
        rates.getRates().computeIfPresent(searchedCurrency.getCurrencyCode(), this::calculateRate);
        // Database'de güncellemeyi yap.
        repository.save(rates);
    }

    private double calculateRate(String key, double oldRate) {
        log.info("SearchedCurrencyConsumer::calculateRate(String key = {}, double oldRate = {})", key, oldRate);
        return oldRate * (1.0 + arbitrageRate);
    }
}
