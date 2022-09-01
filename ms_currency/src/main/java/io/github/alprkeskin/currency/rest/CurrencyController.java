package io.github.alprkeskin.currency.rest;

import io.github.alprkeskin.currency.model.CurrencyRates;
import io.github.alprkeskin.currency.service.CurrencyRatesMediatorService;
import io.github.alprkeskin.currency.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyRatesMediatorService currencyRatesMediatorService;

    @GetMapping(value = {"", "/{requestedDate}"})
    public ResponseEntity<CurrencyRates> getCurrencyRates(
            @PathVariable(value = "requestedDate", required = false) String requestedDate) {
        LocalDate desiredDate = requestedDate == null ? LocalDate.now() : DateUtil.getLocalDate(requestedDate);
        return ResponseEntity.ok(currencyRatesMediatorService.getCurrencyRates(desiredDate));
    }

    @GetMapping(value = "/currencies")
    public ResponseEntity<Map> getCurrencies() {
        return ResponseEntity.ok(currencyRatesMediatorService.getCurrencies());
    }
}
