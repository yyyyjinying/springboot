package com.changgou.springbootrabbitmq.controller;

import com.changgou.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.Configuration;

@Slf4j
//@RestController
//@RequestMapping("/confirm")
public class ProducerController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable String message) {
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,ConfirmConfig.CONFIRM_ROOUTING_KEY,message);

//        CorrelationData correlationData = new CorrelationData("1");
//        CorrelationData correlationDataExpetion = new CorrelationData("2");
//
//
//        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,ConfirmConfig.CONFIRM_ROOUTING_KEY,message, correlationData);
//        // 队列异常
//        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,ConfirmConfig.CONFIRM_ROOUTING_KEY+"123",message, correlationDataExpetion);
        // 制造交换机异常
//        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,ConfirmConfig.CONFIRM_ROOUTING_KEY,message, correlationData);

        log.info("发送消息内容:{}",message);
    }
}
