package com.changgou.springbootrabbitmq.controller;

import com.changgou.springbootrabbitmq.bconfig.BakConfirmConfig.Config;
import com.changgou.springbootrabbitmq.config.ConfirmConfig01;
import com.changgou.springbootrabbitmq.config.DelayedQueueConfig;
import com.changgou.springbootrabbitmq.config.TtlQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMsgController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * http://localhost:8088/ttl/sendMsg/你好
     *
     * @param message
     */
    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable("message") String message) {
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", new Date(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自 ttl 为 10S 的队列: " + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 30S 的队列: " + message);
    }

    /**
     * http://localhost:8088/ttl/sendExpirationMsg/你好1/20000
     * http://localhost:8088/ttl/sendExpirationMsg/你好2/6000
     *
     * @param message
     * @param ttlTime
     */
    @GetMapping("/sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable("message") String message, @PathVariable("ttlTime") String ttlTime) {
        log.info("当前时间：{},发送一条信息给expiration队列:{},时间间隔：{}", new Date(), message, ttlTime);
        rabbitTemplate.convertAndSend("X", "XC", message, megs -> {
            System.out.println("啊啊啊");
            megs.getMessageProperties().setExpiration(ttlTime);
            return megs;
        });
    }

    /**
     * http://localhost:8088/ttl/sendDelayMsg/come on baby1/20000
     * http://localhost:8088/ttl/sendDelayMsg/come on baby2/2000
     *
     * @param message
     * @param deayTime
     */
    @GetMapping("sendDelayMsg/{message}/{delayTime}")
    public void sendMsg(@PathVariable("message") String message, @PathVariable("delayTime") Integer deayTime) {
        log.info("当前时间：{},发送一条信息给delayed队列:{},时间间隔：{}", new Date(), message, deayTime);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME, DelayedQueueConfig.DELAYED_ROUTING_KEY, message, megs -> {
            megs.getMessageProperties().setDelay(deayTime);
//            megs.getMessageProperties().setMessageCount(); // Mandatory
            return megs;
        });
    }

    // http://localhost:8088/ttl/sendProduct/ppppp
    @GetMapping("/sendProduct/{message}")
    public void sendProduct(@PathVariable("message") String message) {
        log.info("当前时间：{}，发送一条信息给confirm队列：{}", new Date(), message);

        CorrelationData correlationData1 = new CorrelationData("1");
        CorrelationData correlationData2 = new CorrelationData("2");

        // 1。正常发送成功
        rabbitTemplate.convertAndSend(ConfirmConfig01.CONFIRM_EXCHANGE_NAME, ConfirmConfig01.CONFIRM_ROUTING_KEY, message+"key1", correlationData1);

        // 2。交换机不存在时，异常发送
//        rabbitTemplate.convertAndSend(ConfirmConfig01.CONFIRM_EXCHANGE_NAME+"123",ConfirmConfig01.CONFIRM_ROUTING_KEY,message, correlationData);

        // 3. routing-key异常
        rabbitTemplate.convertAndSend(ConfirmConfig01.CONFIRM_EXCHANGE_NAME, ConfirmConfig01.CONFIRM_ROUTING_KEY + "123", message+"key2", correlationData2);

    }

    // http://localhost:8088/ttl/sendBakProduct/ppppp
    @GetMapping("/sendBakProduct/{message}")
    public void sendBakProduct(@PathVariable("message") String message) {
        log.info("当前时间：{}，发送一条信息给confirm队列：{}", new Date(), message);

        CorrelationData correlationData1 = new CorrelationData("1");
        CorrelationData correlationData2 = new CorrelationData("2");
        // 1。正常发送成功
        rabbitTemplate.convertAndSend(Config.CONFIRM_EXCHANGE_NAME, Config.CONFIRM_ROUTING_KEY, message+"key1", correlationData1);
        // 2. routing-key异常
        rabbitTemplate.convertAndSend(Config.CONFIRM_EXCHANGE_NAME, Config.CONFIRM_ROUTING_KEY + "123", message+"key2", correlationData2);

    }
}

