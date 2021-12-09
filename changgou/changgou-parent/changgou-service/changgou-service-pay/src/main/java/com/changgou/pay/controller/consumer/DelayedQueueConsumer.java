package com.changgou.pay.controller.consumer;

import com.changgou.pay.config.rabbitmq.DelayedQueueConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class DelayedQueueConsumer {
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayedQueue(Message message, Channel channel) throws Exception{
        String msg = new String(message.getBody());
        log.info("当前时间：{},收到Delayed队列信息{}", new Date().toString(), msg);
    }
}
