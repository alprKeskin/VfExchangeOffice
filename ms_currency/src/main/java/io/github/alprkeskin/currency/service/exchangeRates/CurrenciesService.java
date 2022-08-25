package io.github.alprkeskin.currency.service.exchangeRates;

import io.github.alprkeskin.currency.service.exchangeRates.abstractClasses.ExchangeRatesAbstractClass;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

import static io.github.alprkeskin.currency.service.endpoints.Endpoint.CURRENCIES;

@Service
@AllArgsConstructor
class CurrenciesService extends ExchangeRatesAbstractClass {

    public Map getCurrencies() {
        return getDataFromRemoteService(getUri(), Map.class);
    }

    public URI getUri() {
        return URI.create(URL + CURRENCIES.getOperationType());
    }

}
