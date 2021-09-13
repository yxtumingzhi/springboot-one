package com.hope.one.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tumingzhi
 */
@Configuration
public class FanoutRabbitConfig {

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange333");
    }

    @Bean
    Queue fanout_exchange_queue_a() {
        return new Queue("fanout_exchange_queue_a");
    }

    @Bean
    Queue fanout_exchange_queue_b() {
        return new Queue("fanout_exchange_queue_b");
    }

    @Bean
    Queue fanout_exchange_queue_c() {
        return new Queue("fanout_exchange_queue_c");
    }

    @Bean
    Binding binding_a() {
        return BindingBuilder.bind(fanout_exchange_queue_a()).to(fanoutExchange());
    }

    @Bean
    Binding binding_b() {
        return BindingBuilder.bind(fanout_exchange_queue_b()).to(fanoutExchange());
    }

    @Bean
    Binding binding_c() {
        return BindingBuilder.bind(fanout_exchange_queue_c()).to(fanoutExchange());
    }


}
