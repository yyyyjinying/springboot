package com.changgou.pay.controller;

import com.changgou.pay.config.rabbitmq.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RequestMapping("ttl")
@RestController
public class SendMsgController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable("message") String message) {
        log.info("当前时间：{},发送一条信息给两个TTL队列:{}", new Date().toString(), message);

        rabbitTemplate.convertAndSend("X", "XA", "消息来自ttl为10S的队列: " + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自ttl为40S的队列: " + message);

    }

    /**
     * http://localhost:8080/ttl/sendDelayMsg/come on baby1/20000
     * http://localhost:8080/ttl/sendDelayMsg/come on baby2/2000
     * @param message
     * @param deayTime
     */
    @GetMapping("sendDelayMsg/{message}/{delayTime}")
    public void sendMsg(@PathVariable("message") String message, @PathVariable("delayTime") Integer deayTime) {
        log.info("当前时间：{},发送一条信息给delayed队列:{},时间间隔：{}", new Date(), message, deayTime);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME, DelayedQueueConfig.DELAYED_ROUTING_KEY, message, megs -> {
            megs.getMessageProperties().setDelay(deayTime);
            return megs;
        });

    }

}
