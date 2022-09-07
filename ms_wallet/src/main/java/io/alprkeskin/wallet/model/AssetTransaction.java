package io.alprkeskin.wallet.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Builder
public class AssetTransaction {
    // email of the user that carries out transaction (e.g. deposit / withdraw 100 TRY)
    private String email;
    // asset code (currency code) that is being transacted (e.g. TRY)
    private String assetCode;
    // amount of asset to be transacted (e.g. 100 TRY)
    private int amount;

    public double getValueInUsdParity() {
        return getCurrencyValue(assetCode);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Value("http://localhost:8080/api/currency")
    private String targetUri;

    private double getCurrencyValue(final String currencyCode) {
        CurrencyRates currencyRates = restTemplate.getForObject(targetUri, CurrencyRates.class);
        // assert currencyRates != null; // BE CAREFUL!!!
        return currencyRates.getRates().get(currencyCode);
    }
}
