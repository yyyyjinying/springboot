package com.changgou.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfirmConfig01 {
    public static final String CONFIRM_EXCHANGE_NAME = "conf.exchange";
    public static final String CONFIRM_QUEUE_NAME = "conf.queue";
    public static final String CONFIRM_ROUTING_KEY = "conf.routingkey";

    @Bean("confExchange")
    public DirectExchange confExchange(){
        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
    }

    @Bean("confQueue")
    public Queue confQueue(){
        return new Queue(CONFIRM_QUEUE_NAME);
    }

    @Bean
    public Binding confQueueExchange(@Qualifier("confQueue") Queue confQueue, @Qualifier("confExchange") DirectExchange confExchange){
        return BindingBuilder.bind(confQueue).to(confExchange).with(CONFIRM_ROUTING_KEY);
    }
}
