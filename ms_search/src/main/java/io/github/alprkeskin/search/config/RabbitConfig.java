package io.github.alprkeskin.search.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("#{'${alprkeskin.queues.to.currency}'.concat('-queue')}")
    private String searchQueue;
    @Value("#{'${alprkeskin.queues.to.currency}'.concat('-exchange')}")
    private String searchExchangeKey;
    @Value("#{'${alprkeskin.queues.to.currency}'.concat('-routing')}")
    private String searchRoutingKey;

    @Bean
    public Declarables createRabbitQueuesConnection() {
        return new Declarables(createQueue(searchQueue), createExchange(searchExchangeKey),
                createBindingRoute(searchRoutingKey, searchExchangeKey, searchQueue));
    }

    private Queue createQueue(String queue) {
        return new Queue(queue, true);
    }

    private DirectExchange createExchange(String exchange) {
        return new DirectExchange(exchange, true, false);
    }

    private Declarable createBindingRoute(String route, String exchange, String queue) {
        return BindingBuilder.bind(createQueue(queue)).to(createExchange(exchange)).with(route);
    }
}
