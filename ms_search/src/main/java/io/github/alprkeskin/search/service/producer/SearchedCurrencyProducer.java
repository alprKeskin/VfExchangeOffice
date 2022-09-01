package io.github.alprkeskin.search.service.producer;

import io.github.alprkeskin.common.model.SearchedCurrency;
import io.github.alprkeskin.search.model.Currency;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchedCurrencyProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("#{'${alprkeskin.queues.to.currency}'.concat('-exchange')}")
    private String exchangeKey;
    @Value("#{'${alprkeskin.queues.to.currency}'.concat('-routing')}")
    private String routingKey;
    @Value("${alprkeskin.arbitrage.threshold:10}")
    private int threshold;

   public void sendMessageToCurrency(int time, Currency currency) {
       if(time % threshold != 0)
            return;

       SearchedCurrency searchedCurrency = SearchedCurrency.builder()
               .currencyCode(getCode(currency)).date(getDate(currency)).build();
       rabbitTemplate.convertAndSend(exchangeKey, routingKey, searchedCurrency);
   }

    private String getDate(Currency currency) {
        return currency.getId().split(" ")[0];
    }

    private String getCode(Currency currency) {
        return currency.getId().split(" ")[1]
                .replace("(","")
                .replace(")","");
    }
}
