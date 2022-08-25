package io.github.alprkeskin.currency.rest;

import io.github.alprkeskin.currency.model.CurrencyRates;
import io.github.alprkeskin.currency.service.OpenExchangeRatesMediatorService;
import io.github.alprkeskin.currency.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/currency")
public class ServiceController {

    @Autowired
    private OpenExchangeRatesMediatorService openExchangeRatesMediatorService;

    @GetMapping(value = {"", "/{requestedDate}"})
    public ResponseEntity<CurrencyRates> getCurrencyRates(
            @PathVariable(value = "requestedDate", required = false) String requestedDate,
            @RequestParam(value = "symbols", required = false, defaultValue = "XXX") String symbols) {

        LocalDate desiredDate = requestedDate == null ? LocalDate.now() : DateUtil.getLocalDate(requestedDate);
        return ResponseEntity.ok(openExchangeRatesMediatorService.getCurrencyRates(desiredDate, symbols));
    }

    @GetMapping(value = "/currencies")
    public ResponseEntity<Map> getCurrencies() {
        return ResponseEntity.ok(openExchangeRatesMediatorService.getCurrencies());
    }
}
