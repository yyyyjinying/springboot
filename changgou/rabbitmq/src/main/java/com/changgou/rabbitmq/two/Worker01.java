package com.changgou.rabbitmq.two;

import com.changgou.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Worker01 {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery)->{
            String s = new String(delivery.getBody());
            System.out.println("接收到消息："+s);
        };
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag+"消费者取消消息回调接口");
        };

        System.out.println("C1————消息者启动等待消费……");

        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);

    }
}
