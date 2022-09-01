package io.github.alprkeskin.search.service;

import io.github.alprkeskin.search.model.Currency;
import io.github.alprkeskin.search.repository.ElasticsearchRepositoryForCurrency;
import io.github.alprkeskin.search.service.producer.SearchedCurrencyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CurrencyCounterService {
    @Autowired
    private ElasticsearchRepositoryForCurrency repository;
    @Autowired
    private SearchedCurrencyProducer currencyProducer;

    Currency incrementSearchCount(Currency currency) {
        currency.incrementQueryNumber();
        currencyProducer.sendMessageToCurrency(currency.getQueryNumber(), currency);

        return repository.save(currency);
    }

    private Currency getCurrency(String id) {
        return repository.findById(id).orElse(Currency.getDefault(id));
    }
}
