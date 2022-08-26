package io.github.alprkeskin.search.service;

import io.github.alprkeskin.search.model.Currency;
import io.github.alprkeskin.search.repository.ElasticsearchRepositoryForCurrency;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ElasticsearchDatabaseServiceTest {

    private ElasticsearchDatabaseService elasticsearchDatabaseService;
    private ElasticsearchRepositoryForCurrency elasticsearchRepositoryForCurrency;
    private RestHighLevelClient restHighLevelClient;
    private ElasticsearchOperations elasticsearchOperations;
    private Currency defaultCurrency;

    @BeforeEach
    void initializeFields() {
        elasticsearchRepositoryForCurrency = Mockito.mock(ElasticsearchRepositoryForCurrency.class);
        restHighLevelClient = Mockito.mock(RestHighLevelClient.class);
        elasticsearchOperations = Mockito.mock(ElasticsearchOperations.class);
        elasticsearchDatabaseService = new ElasticsearchDatabaseService(elasticsearchRepositoryForCurrency, restHighLevelClient, elasticsearchOperations);
        defaultCurrency = new Currency("1", 10);
    }

    @Test
    void increaseQueryNumberAndReturn() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void fuzzySearch() {
    }
}