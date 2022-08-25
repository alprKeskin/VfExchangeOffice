package io.github.alprkeskin.search.repository;

import io.github.alprkeskin.search.model.Currency;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchRepositoryForCurrency extends ElasticsearchRepository<Currency, String> {
}
