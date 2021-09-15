package com.changgou.rabbitmq.six;

import com.changgou.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class ReceiveLogs02 {
    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.DIRECT);
        /**
         * 生成一个临时的队列 队列的名称是随机的
         * 当消费者断开和该队列的连接时 队列自动删除
         */
        String queueName = "console";
        channel.queueDeclare(queueName, false,false,false,null);
        //把该临时队列绑定我们的 exchange 其中 routingkey(也称之为 binding key)为空字符串
        channel.queueBind(queueName,EXCHANGE_NAME,"info");
        channel.queueBind(queueName,EXCHANGE_NAME,"warning");

        System.out.println("等待接收消息 ........... ");
        DeliverCallback deliverCallback = (consumeTag, delivery) -> {
            byte[] body = delivery.getBody();
            String message = new String(body, "UTF-8");
            message = "接收绑定的键："+delivery.getEnvelope().getRoutingKey()+",消息："+message;
            System.out.println(message);

        };
        CancelCallback cancelCallback = message -> {

        };
        channel.basicConsume(queueName, true, deliverCallback, cancelCallback);

    }
}
