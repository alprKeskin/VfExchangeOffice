package io.alprkeskin.wallet.service;

import io.alprkeskin.wallet.model.Asset;
import io.alprkeskin.wallet.model.AssetTransaction;
import io.alprkeskin.wallet.model.CurrencyRates;
import io.alprkeskin.wallet.model.document.Assets;
import io.alprkeskin.wallet.repository.AssetRepository;
import io.alprkeskin.wallet.service.producer.MutateCurrencyValueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
public class AssetService {
    @Autowired
    private AssetRepository repository;

    @Autowired
    private MutateCurrencyValueProducer mutateCurrencyValueProducer;

    public Map<String, Asset> getUserAssets(String email) {
        return repository.findById(email).orElse(new Assets()).getAssets();
    }

    public Map<String, Asset> deposit(AssetTransaction transaction) {
        Optional<Assets> assetsOptional = repository.findById(transaction.getEmail());
        if (assetsOptional.isEmpty())
            return null;
        Assets assets =  assetsOptional.get();
        Asset asset = assets.getAssets().getOrDefault(transaction.getAssetCode(), new Asset());

        // TODO: get currencies for usd calculation
        double assetValue = getCurrencyValue(transaction.getAssetCode());

        asset.incrementValue(transaction.getAmount());

        // TODO: asset.incrementUsdValue(...);
        asset.incrementUsdValue(assetValue);

        assets.getAssets().put(transaction.getAssetCode(), asset);

        // arttır veya azalt
        // ms_currency ve ms_wallet birbiriyle konuşmalı
        // ms_wallet produce etmeli (talk)
        // ms_ currency consume etmeli (listen)
        // TODO: mutate related currency value with rabbitmq
        mutateCurrencyValueProducer.sendMessageToCurrency(1,
                true,
                transaction);

        return repository.save(assets).getAssets();
    }

    public Map<String, Asset> withdraw(AssetTransaction transaction) {
        Optional<Assets> assetsOptional = repository.findById(transaction.getEmail());
        if (assetsOptional.isEmpty())
            return null;
        Assets assets =  assetsOptional.get();
        Asset asset = assets.getAssets().getOrDefault(transaction.getAssetCode(), new Asset());

        // TODO: get currencies for usd calculation
        double assetValue = getCurrencyValue(transaction.getAssetCode());

        asset.decrementValue(transaction.getAmount());

        // TODO: asset.decrementUsdValue(...);
        asset.decrementUsdValue(assetValue);

        assets.getAssets().put(transaction.getAssetCode(), asset);

        // TODO: mutate related currency value with rabbitmq
        mutateCurrencyValueProducer.sendMessageToCurrency(1,
                false,
                transaction);

        return repository.save(assets).getAssets();
    }

    // ------------------------- mission methods -------------------------
    // http://localhost:8080/api/currency

    @Autowired
    private RestTemplate restTemplate;

    @Value("http://localhost:8080/api/currency")
    private String targetUri;

    private double getCurrencyValue(final String currencyCode) {
        CurrencyRates currencyRates = restTemplate.getForObject(targetUri, CurrencyRates.class);
        // assert currencyRates != null; // BE CAREFUL!!!
        return currencyRates.getRates().get(currencyCode);
    }


    // rabbitmq implementation
    // TODO: rabbitmq implementation

}





















