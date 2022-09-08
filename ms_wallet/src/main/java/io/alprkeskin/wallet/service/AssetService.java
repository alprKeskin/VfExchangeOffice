package io.alprkeskin.wallet.service;

import io.alprkeskin.wallet.model.Asset;
import io.alprkeskin.wallet.model.AssetTransaction;
import io.alprkeskin.wallet.model.CurrencyRates;
import io.alprkeskin.wallet.model.document.Assets;
import io.alprkeskin.wallet.repository.AssetRepository;
import io.alprkeskin.wallet.service.producer.MutateCurrencyValueProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class AssetService {
    @Autowired
    private AssetRepository repository;

    @Autowired
    private MutateCurrencyValueProducer mutateCurrencyValueProducer;

    public Map<String, Asset> getUserAssets(String email) {
        log.info("AssetService::getUserAssets(String email = {})", email);
        return repository.findById(email).orElse(Assets.getDefault(email)).getAssets();
    }

    public Map<String, Asset> deposit(AssetTransaction transaction) {
        log.info("AssetService::deposit(AssetTransaction transaction = {})", transaction);
        Assets assets = getAssets(transaction);
        Asset asset = assets.getAssets().getOrDefault(transaction.getAssetCode(), new Asset());
        asset.incrementAmount(transaction.getAmount());
        double assetValue = getCurrencyValue(transaction.getAssetCode());
        asset.incrementValue(transaction.getAmount() / assetValue);
        mutateCurrencyValueProducer.sendMessageToCurrency(1, true, transaction);

        assets.getAssets().put(transaction.getAssetCode(), asset);

        return repository.save(assets).getAssets();
    }

    public Map<String, Asset> withdraw(AssetTransaction transaction) {
        log.info("AssetService::withdraw(AssetTransaction transaction = {})", transaction);

        Assets assets = getAssets(transaction);
        Asset asset = assets.getAssets().getOrDefault(transaction.getAssetCode(), new Asset());
        asset.decrementAmount(transaction.getAmount());
        double assetValue = getCurrencyValue(transaction.getAssetCode());
        asset.decrementValue(transaction.getAmount() / assetValue);
        mutateCurrencyValueProducer.sendMessageToCurrency(1, false, transaction);


        return repository.save(assets).getAssets();
    }

    private Assets getAssets(AssetTransaction transaction) {
        return repository.findById(transaction.getEmail()).orElse(Assets.getDefault(transaction.getEmail()));
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





















