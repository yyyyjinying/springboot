package com.changgou.springbootrabbitmq.controller;

import com.changgou.springbootrabbitmq.config.TtlQueueConfig;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable("message") String message){
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", new Date(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自 ttl 为 10S 的队列: "+message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 40S 的队列: "+message);

    }

    /**
     *  http://localhost:8080/ttl/sendExpirationMsg/你好 1/2000
     *  http://localhost:8080/ttl/sendExpirationMsg/你好 2/200
     * @param message
     * @param ttlTime
     */
    @GetMapping("/sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable("message") String message, @PathVariable("ttlTime") String ttlTime){
        log.info("当前时间：{},发送一条信息给expiration队列:{},时间间隔：{}", new Date(), message, ttlTime);
        rabbitTemplate.convertAndSend(TtlQueueConfig.X_EXCHANGE,TtlQueueConfig.QUEUE_C,message, megs->{
            megs.getMessageProperties().setExpiration(ttlTime);
            return megs;
        });
    }
}
