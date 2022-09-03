package io.github.alprkeskin.currency.rest;

import io.github.alprkeskin.currency.model.CurrencyRates;
import io.github.alprkeskin.currency.service.CurrencyRatesMediatorService;
import io.github.alprkeskin.currency.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/currency")
@Slf4j
public class CurrencyController {
    @Autowired
    private CurrencyRatesMediatorService currencyRatesMediatorService;

    @GetMapping(value = {"", "/{requestedDate}"})
    @CrossOrigin
    public ResponseEntity<CurrencyRates> getCurrencyRates(
            @PathVariable(value = "requestedDate", required = false) String requestedDate) {
        log.info("CurrencyController::getCurrencyRates(String requestedDate = {} )", requestedDate);
        LocalDate desiredDate = requestedDate == null ? LocalDate.now() : DateUtil.getLocalDate(requestedDate);
        return ResponseEntity.ok(currencyRatesMediatorService.getCurrencyRates(desiredDate));
    }

    @GetMapping(value = "/currencies")
    @CrossOrigin
    public ResponseEntity<Map> getCurrencies() {
        log.info("CurrencyController::getCurrencies()");
        return ResponseEntity.ok(currencyRatesMediatorService.getCurrencies());
    }
}
