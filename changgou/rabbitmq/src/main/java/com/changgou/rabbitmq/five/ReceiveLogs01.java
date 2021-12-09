package com.changgou.rabbitmq.five;

import com.changgou.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

public class ReceiveLogs01 {
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        /**
         * 生成一个临时的队列 队列的名称是随机的
         * 当消费者断开和该队列的连接时 队列自动删除  ，
         */
        String queueName = channel.queueDeclare().getQueue();

        //把该临时队列绑定我们的 exchange 其中 routingkey(也称之为 binding key)为空字符串
        channel.queueBind(queueName,EXCHANGE_NAME,"");

        System.out.println("等待接收消息,把接收到的消息打印在屏幕 ........... ");
        DeliverCallback deliverCallback = (consumeTag, delivery) -> {
            byte[] body = delivery.getBody();
            String s = new String(body, "UTF-8");
            System.out.println("控制台接收到的消息："+s);

        };
        CancelCallback cancelCallback = message -> {

        };
        channel.basicConsume(queueName, true, deliverCallback,cancelCallback);

    }
}
