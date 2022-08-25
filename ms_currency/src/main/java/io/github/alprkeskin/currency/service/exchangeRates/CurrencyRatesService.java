package io.github.alprkeskin.currency.service.exchangeRates;

import io.github.alprkeskin.currency.model.CurrencyRates;
import io.github.alprkeskin.currency.service.exchangeRates.abstractClasses.ExchangeRatesAbstractClass;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

import static io.github.alprkeskin.currency.service.endpoints.Endpoint.HISTORICAL;
import static io.github.alprkeskin.currency.service.endpoints.Endpoint.LATEST;

@AllArgsConstructor
@Service
class CurrencyRatesService extends ExchangeRatesAbstractClass {

    public CurrencyRates getCurrencyRates(LocalDate date, String symbols) {
        return getDataFromRemoteService(getUri(date, symbols), CurrencyRates.class);
    }

    public URI getUri(LocalDate date, String symbols) {
        String endpoint = date.isEqual(LocalDate.now()) ? LATEST.getOperationType() : HISTORICAL.getOperationType(date);
        String symbolsQueryParameter = "XXX".equals(symbols) ? "" : "&symbols=" + symbols;
        return URI.create(URL + endpoint + "?" + "app_id=" + app_id + symbolsQueryParameter);
    }

}
