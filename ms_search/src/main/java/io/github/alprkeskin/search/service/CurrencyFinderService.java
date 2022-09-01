package io.github.alprkeskin.search.service;

import io.github.alprkeskin.search.model.Currency;
import io.github.alprkeskin.search.utils.ValidCurrencies;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CurrencyFinderService {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private CurrencyCounterService counterService;


    Optional<Currency> findCurrency(final String currencyName, final String id, final Fuzziness fuzzyLevel) {
        SearchHits<Currency> currencyHits = elasticsearchOperations.search(getSearchQuery(id, fuzzyLevel),
                Currency.class, IndexCoordinates.of("currency"));

        Optional<Currency> currencyOpt = currencyHits.stream().map(SearchHit::getContent).findFirst();

        return incrementAndGetCurrency(currencyOpt, currencyName, id, fuzzyLevel);
    }

    private Optional<Currency> incrementAndGetCurrency(Optional<Currency> currencyOpt, final String currencyName,
                                             final String id, final Fuzziness fuzzyLevel) {
        if (isValidCurrency(currencyOpt, currencyName, fuzzyLevel)) {
            return Optional.of(counterService.incrementSearchCount(id));
        }

        return Optional.empty();
    }

    private boolean isValidCurrency(Optional<Currency> currencyOpt, final String currencyName,
                                     final Fuzziness fuzzyLevel) {
        if (currencyOpt.isEmpty() && fuzzyLevel == Fuzziness.ONE) {
            throw new RuntimeException("Neither the given currency is a valid currency nor" +
                    " there is a similar currency in the database");
        }

        return currencyOpt.isPresent() || ValidCurrencies.isValidCurrency(currencyName);
    }

    private Query getSearchQuery(final String queryValue, final Fuzziness fuzzyLevel) {
        QueryBuilder queryBuilder = QueryBuilders
                .matchQuery("id", queryValue)
                .fuzziness(fuzzyLevel);

        return new NativeSearchQueryBuilder().withFilter(queryBuilder).build();
    }
}
