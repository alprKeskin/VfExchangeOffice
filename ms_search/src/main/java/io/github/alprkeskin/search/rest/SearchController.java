package io.github.alprkeskin.search.rest;

import io.github.alprkeskin.search.model.Currency;
import io.github.alprkeskin.search.service.CurrencyService;
import io.github.alprkeskin.search.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/{currency_name}")
    @CrossOrigin
    public ResponseEntity<Currency> getCurrency(@PathVariable(value = "currency_name") String currencyName) throws IOException {
        log.info("SearchController::getCurrency()");
        return ok(currencyService.getCurrency(currencyName));
    }

    @DeleteMapping("/{date}/{currency_name}")
    @CrossOrigin
    public void deleteCurrency(@PathVariable(value = "date") String date,
                               @PathVariable(value = "currency_name") String currencyName) {
        log.info("SearchController::deleteCurrency(String date = {}, String currencyName = {} )", date, currencyName);
        String relatedId = Utils.getIdWithDateAndCurrencyName(currencyName, date);
        currencyService.delete(relatedId);
    }
}
