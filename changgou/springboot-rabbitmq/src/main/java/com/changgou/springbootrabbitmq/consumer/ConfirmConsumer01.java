package com.changgou.springbootrabbitmq.consumer;

import com.changgou.springbootrabbitmq.config.ConfirmConfig01;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConfirmConsumer01 {
    @RabbitListener(queues = ConfirmConfig01.CONFIRM_QUEUE_NAME)
    public void receiveMsg(Message message){
        String msg=new String(message.getBody());
        log.info("接受到队列 confirm.queue 消息:{}",msg);

    }
}
