package com.changgou.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

public class MsgTtlQueueConfig {
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    public static final String QUEUE_C = "QC";

    // 声明死信交换机
    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    // 声明死信队列
    @Bean("queueC")
    public Queue queueC(){
        HashMap<String, Object> args = new HashMap<>(2);
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", "YD");
        return QueueBuilder.durable(QUEUE_C).withArguments(args).build();
    }

    // 队列与交换机进行绑定
    @Bean
    public Binding queuecBindingC(@Qualifier("queueC") Queue queueC, @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }

}
