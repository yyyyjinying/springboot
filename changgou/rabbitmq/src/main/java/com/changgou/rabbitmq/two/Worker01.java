package com.changgou.rabbitmq.two;

import com.changgou.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 开启多任务
 */
public class Worker01 {
    private static final String QUEUE_NAME = "hello1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery)->{
            String s = new String(delivery.getBody());
            System.out.println("接收到消息："+s);
        };
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag+"消费者取消消息回调接口");
        };

        System.out.println("C2————消息者启动等待消费……");

        /**
         * 消费哪个队列
         * 是否自动应答:已接受到消息为准 消息应答
         * 消费成功
         * 消费失败
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);


    }
}
