package io.github.alprkeskin.currency.service.ext.call.open.exchange;

import io.github.alprkeskin.currency.model.CurrencyRates;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;

import static io.github.alprkeskin.currency.service.endpoints.Endpoint.HISTORICAL;
import static io.github.alprkeskin.currency.service.endpoints.Endpoint.LATEST;

@AllArgsConstructor
@Service
class OpenExchangeRatesService extends OpenExchangeRates {

    CurrencyRates getCurrencyRates(LocalDate date) {
        return getDataFromRemoteService(getUri(date), CurrencyRates.class);
    }

    URI getUri(LocalDate date) {
        String endpoint = date.isEqual(LocalDate.now()) ? LATEST.getOperationType() : HISTORICAL.getOperationType(date);

        return URI.create(URL + endpoint + "?" + "app_id=" + app_id);
    }

}
