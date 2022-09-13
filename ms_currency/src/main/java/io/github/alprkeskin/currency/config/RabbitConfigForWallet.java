package io.github.alprkeskin.currency.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RabbitConfigForWallet {
    private static final String QUEUE_POSTFIX = "-queue";
    private static final String EXCHANGE_POSTFIX = "-exchange";
    private static final String ROUTING_POSTFIX = "-routing";
    @Value("#{'${alprkeskin.queues.from.wallet}'}")
    private String searchPrefix;

    @Bean
    public Declarables createRabbitQueuesConnection() {
        return new Declarables(createQueue(searchPrefix), createExchange(searchPrefix),
                createBindingRoute(searchPrefix));
    }

    private Queue createQueue(String prefix) {
        return new Queue(prefix + QUEUE_POSTFIX, true);
    }

    private DirectExchange createExchange(String prefix) {
        return new DirectExchange(prefix + EXCHANGE_POSTFIX, true, false);
    }

    private Declarable createBindingRoute(String prefix) {
        return BindingBuilder.bind(createQueue(prefix)).to(createExchange(prefix)).with(prefix + ROUTING_POSTFIX);
    }
}
