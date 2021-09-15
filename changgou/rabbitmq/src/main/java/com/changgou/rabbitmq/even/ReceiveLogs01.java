package com.changgou.rabbitmq.even;

import com.changgou.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.File;

public class ReceiveLogs01 {
    @Autowired
    ResourceLoader resourceLoader;

    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        /**
         * 生成一个临时的队列 队列的名称是随机的
         * 当消费者断开和该队列的连接时 队列自动删除
         */
//        String queueName = channel.queueDeclare().getQueue();
        String queueName = "Q1";
        channel.queueDeclare(queueName, false,false,false,null);
        //把该临时队列绑定我们的 exchange 其中 routingkey(也称之为 binding key)为空字符串
        channel.queueBind(queueName,EXCHANGE_NAME,"*.orange.*");

        System.out.println("等待接收消息 ........... ");
        DeliverCallback deliverCallback = (consumeTag, delivery) -> {
            byte[] body = delivery.getBody();
            String message = new String(body, "UTF-8");
            message = "接收绑定的键："+delivery.getEnvelope().getRoutingKey()+",消息："+message;
            System.out.println(message);


        };
        CancelCallback cancelCallback = message -> {

        };
        channel.basicConsume(queueName, true, deliverCallback,cancelCallback);

    }
}
