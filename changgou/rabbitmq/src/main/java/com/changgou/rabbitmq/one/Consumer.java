package com.changgou.rabbitmq.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    // 队列名称
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 工厂IP 连接Rabbitmq的队列
        factory.setHost("172.16.147.181");
        factory.setUsername("admin");
        factory.setPassword("admin");

        // 建立联机
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (var1, var2) -> {
            String message = new String(var2.getBody());
            System.out.println(message);
        };

        CancelCallback cancelCallback = var -> {
            System.out.println("消息消费被中断！");
        };

        // 消费者消费消息
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);



    }
}
