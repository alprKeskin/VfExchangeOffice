package io.alprkeskin.wallet.service.producer;

import io.alprkeskin.wallet.service.CurrencyClientService;
import io.github.alprkeskin.common.model.MutatedCurrency;
import io.alprkeskin.wallet.model.AssetTransaction;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MutateCurrencyValueProducer {
    // TODO: create a configuration class for the rabbitTemplate

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CurrencyClientService currencyClientService;

    // TODO: exchangeKey ve routingKey değerlerini doğru şekilde ayarla.
    @Value("#{'${alprkeskin.queues.to.currency}'.concat('-exchange')}")
    private String exchangeKey;
    @Value("#{'${alprkeskin.queues.to.currency}'.concat('-routing')}")
    private String routingKey;
    @Value("1")
    private int threshold;

    public void sendMessageToCurrency(int time, boolean isPump, AssetTransaction assetTransaction) {
        if (time % threshold != 0) return;

        MutatedCurrency mutatedCurrency = MutatedCurrency.builder()
                .currencyCode(assetTransaction.getAssetCode())
                .transactionAmountInUsdParity(currencyClientService.getCurrencyValue(assetTransaction.getAssetCode()))
                .isPump(isPump)
                .build();

        rabbitTemplate.convertAndSend(exchangeKey, routingKey, mutatedCurrency);
    }

}
