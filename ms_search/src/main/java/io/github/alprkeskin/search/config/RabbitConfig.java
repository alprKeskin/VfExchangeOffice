package io.github.alprkeskin.search.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private static final String QUEUE_POSTFIX = "-queue";
    private static final String EXCHANGE_POSTFIX = "-exchange";
    private static final String ROUTING_POSTFIX = "-routing";
    @Value("#{'${alprkeskin.queues.to.currency}'}")
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
