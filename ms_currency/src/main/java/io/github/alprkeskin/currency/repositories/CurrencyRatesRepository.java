package io.github.alprkeskin.currency.repositories;

import io.github.alprkeskin.currency.model.CurrencyRates;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRatesRepository
        extends MongoRepository<CurrencyRates, String> {
}
