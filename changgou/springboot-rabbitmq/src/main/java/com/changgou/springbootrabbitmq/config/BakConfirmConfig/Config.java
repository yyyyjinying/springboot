package com.changgou.springbootrabbitmq.bconfig.BakConfirmConfig;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    public static final String CONFIRM_EXCHANGE_NAME = "bconf.exchange";
    public static final String CONFIRM_QUEUE_NAME = "bconf.queue";
    public static final String CONFIRM_ROUTING_KEY = "bconf.routingkey";

    public static final String BAK_CONFIRM_EXCHANGE_NAME="bak.exchange";
    public static final String BAK_CONFIRM_QUEUE_NAME="bak.queue";
    public static final String WARING_CONFIRM_QUEUE_NAME="warning.queue";

    @Bean("bconfExchange")
    public DirectExchange bconfExchange(){
//        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
        Exchange alterExchange = ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true)
                .withArgument("alternate-exchange", BAK_CONFIRM_EXCHANGE_NAME).build();
        return (DirectExchange) alterExchange;
    }

    @Bean("bconfQueue")
    public Queue bconfQueue(){
        return new Queue(CONFIRM_QUEUE_NAME);
    }

    @Bean
    public Binding bconfQueueExchange(@Qualifier("bconfQueue") Queue bconfQueue, @Qualifier("bconfExchange") DirectExchange bconfExchange){
        return BindingBuilder.bind(bconfQueue).to(bconfExchange).with(CONFIRM_ROUTING_KEY);
    }

    // 扇形交换机
    @Bean("bakExchange")
    public FanoutExchange bakExchange(){
        return new FanoutExchange(BAK_CONFIRM_EXCHANGE_NAME);
    }

    // 备份队列
    @Bean("bakQueue")
    public Queue bakQueue(){
        return QueueBuilder.durable(BAK_CONFIRM_QUEUE_NAME).build();
    }

    @Bean("waringQueue")
    public Queue waringQueue(){
        return QueueBuilder.durable(WARING_CONFIRM_QUEUE_NAME).build();
    }

    @Bean
    public Binding BakQueueExchange(@Qualifier("bakQueue") Queue bakQueue, @Qualifier("bakExchange") FanoutExchange bakExchange){
        return BindingBuilder.bind(bakQueue).to(bakExchange);
    }
    @Bean
    public Binding WarningQueueExchange(@Qualifier("waringQueue") Queue waringQueue, @Qualifier("bakExchange") FanoutExchange bakExchange){
        return BindingBuilder.bind(waringQueue).to(bakExchange);
    }
}
