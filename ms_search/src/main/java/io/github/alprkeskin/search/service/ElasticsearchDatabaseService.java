package io.github.alprkeskin.search.service;

import io.github.alprkeskin.search.model.Currency;
import io.github.alprkeskin.search.repository.ElasticsearchRepositoryForCurrency;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@NoArgsConstructor
@AllArgsConstructor
public class ElasticsearchDatabaseService {

    @Autowired
    private ElasticsearchRepositoryForCurrency repository;

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private Currency find(String id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Update işlemini bu şekilde database üzerinden yaptım.
     * Önce database'den update edeceğim elemanı çekip düzenleyip tekrar save'leyerek de yapabilirdim.
     * Hangisi daha doğru???
     * **/
    private void increaseQueryNumber(String id) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("currency", id);
        updateRequest.doc("queryNumber", find(id).getQueryNumber() + 1);
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
    }

    public Currency increaseQueryNumberAndReturn(Currency currency) throws IOException {
        this.increaseQueryNumber(currency.getId());
        currency.setQueryNumber(currency.getQueryNumber() + 1);
        return currency;
    }

    public Currency save(String relatedId) {
        Currency newQueriedCurrency = new Currency(relatedId, 1);
        repository.save(newQueriedCurrency);
        return newQueriedCurrency;
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Currency fuzzySearch(final String queryValue, final Fuzziness fuzzyLevel) {
        // try to find the exact currency
        QueryBuilder queryBuilder = QueryBuilders
                .matchQuery("id", queryValue)
                .fuzziness(fuzzyLevel);

        Query searchQuery = new NativeSearchQueryBuilder().withFilter(queryBuilder).build();

        // execute search
        SearchHits<Currency> currencyHits = elasticsearchOperations.search(searchQuery, Currency.class, IndexCoordinates.of("currency"));

        // map searchHits to currency list
        List<Currency> currencyMatches = new ArrayList<Currency>();
        currencyHits.forEach(searchHit->{
            currencyMatches.add(searchHit.getContent());
        });

        if ((currencyMatches.size() == 0) && (fuzzyLevel == Fuzziness.ONE)) {
            throw new RuntimeException("Neither the given currency is a valid currency nor there is a similar currency in the database");
        }

        // if there could not found any currency
        if (currencyMatches.size() == 0) {
            return null;
        }
        return currencyMatches.get(0);
    }

}
