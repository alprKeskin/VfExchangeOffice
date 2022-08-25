package io.github.alprkeskin.search.rest;

import io.github.alprkeskin.search.model.Currency;
import io.github.alprkeskin.search.service.ElasticsearchDatabaseService;
import io.github.alprkeskin.search.service.ValidCurrencies;
import io.github.alprkeskin.search.utils.Utils;
import org.elasticsearch.common.unit.Fuzziness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/search")
public class ServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private ElasticsearchDatabaseService elasticsearchDatabaseService;

    @GetMapping("/{currency_name}")
    public ResponseEntity<Currency> getCurrencyFuzzily(@PathVariable(value = "currency_name") String currencyName) throws IOException {
        String relatedId = Utils.getIdWithDateAndCurrencyName(currencyName);
        Currency relatedCurrency = elasticsearchDatabaseService.fuzzySearch(relatedId, Fuzziness.ZERO);
        if (relatedCurrency != null) {
            return ResponseEntity.ok(elasticsearchDatabaseService.increaseQueryNumberAndReturn(relatedCurrency));
        }
        if (ValidCurrencies.isValidCurrency(currencyName)) {
            return ResponseEntity.ok(elasticsearchDatabaseService.save(relatedId));
        }
        relatedCurrency = elasticsearchDatabaseService.fuzzySearch(relatedId, Fuzziness.ONE);
        return ResponseEntity.ok(elasticsearchDatabaseService.increaseQueryNumberAndReturn(relatedCurrency));
    }

    @DeleteMapping("/{date}/{currency_name}")
    public void deleteCurrency(@PathVariable(value = "date") String date,
                               @PathVariable(value = "currency_name") String currencyName) {
        String relatedId = Utils.getIdWithDateAndCurrencyName(currencyName, date);
        elasticsearchDatabaseService.delete(relatedId);
    }
}
