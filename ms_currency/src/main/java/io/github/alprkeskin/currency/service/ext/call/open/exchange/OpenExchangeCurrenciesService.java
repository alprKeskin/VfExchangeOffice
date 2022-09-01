package io.github.alprkeskin.currency.service.ext.call.open.exchange;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

import static io.github.alprkeskin.currency.service.endpoints.Endpoint.CURRENCIES;

@Service
@AllArgsConstructor
class OpenExchangeCurrenciesService extends OpenExchangeRates {

    Map getCurrencies() {
        return getDataFromRemoteService(getUri(), Map.class);
    }

    URI getUri() {
        return URI.create(URL + CURRENCIES.getOperationType());
    }

}
