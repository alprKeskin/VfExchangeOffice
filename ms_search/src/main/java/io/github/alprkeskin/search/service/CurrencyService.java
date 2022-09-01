package io.github.alprkeskin.search.service;

import io.github.alprkeskin.search.model.Currency;
import io.github.alprkeskin.search.repository.ElasticsearchRepositoryForCurrency;
import io.github.alprkeskin.search.utils.Utils;
import org.elasticsearch.common.unit.Fuzziness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyFinderService finderService;
    @Autowired
    private ElasticsearchRepositoryForCurrency repository;

    public Currency getCurrency(String currencyName) {
        String relatedId = Utils.getIdWithDateAndCurrencyName(currencyName);

        return finderService.findCurrency(currencyName, relatedId, Fuzziness.ZERO)
                .orElse(finderService.findCurrency(currencyName,relatedId, Fuzziness.ONE).orElseThrow(this::throwNotFoundException));
    }
    public void delete(String id) {
        repository.deleteById(id);
    }

    private RuntimeException throwNotFoundException() {
        return new RuntimeException("Currency cannot found");
    }

}
