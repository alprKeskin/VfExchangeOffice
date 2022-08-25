package io.github.alprkeskin.currency.service.exchangeRates.abstractClasses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@NoArgsConstructor
@AllArgsConstructor
public abstract class ExchangeRatesAbstractClass {

    @Autowired
    protected RestTemplate restTemplate;

    @Value("${alprkeskin.consumed-urls.api}")
    protected String URL;

    @Value("${query-parameter.app_id}")
    protected String app_id;

    public<T> T getDataFromRemoteService(URI targetUri, Class<T> className) {
        return restTemplate.getForObject(targetUri, className);
    }

}
