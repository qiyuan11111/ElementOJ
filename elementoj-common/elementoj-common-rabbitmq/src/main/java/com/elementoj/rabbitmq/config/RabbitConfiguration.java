package com.elementoj.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean("eleExchange")
    public Exchange exchange() {
        return ExchangeBuilder.directExchange("amq.direct").build();
    }

    @Bean("eleQueue")
    public Queue queue() {
        return QueueBuilder
                .nonDurable("eleQueue")
                .build();
    }

    @Bean("eleBinding")
    public Binding binding(@Qualifier("eleQueue") Queue queue,
                           @Qualifier("eleExchange") Exchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("eleQueue")
                .noargs();
    }
}
