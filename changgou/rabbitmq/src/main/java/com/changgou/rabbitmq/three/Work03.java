package com.changgou.rabbitmq.three;

import com.changgou.rabbitmq.utils.RabbitMqUtils;
import com.changgou.rabbitmq.utils.SleepUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Work03 {

    private static final String TASK_QUEUE_NAME = "ack_name";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("C1 等待接收消息处理时间短");
            SleepUtils.sleep(1);
            System.out.println("接收消息：" + message);
            /**
             * 1.消息标记
             * 2。是否批量应答未应答消息
             */
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

        };


        // 采用手动应答
        boolean autoAck = false;
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallback, (consumerTag) -> {
            System.out.println(consumerTag + "消费者取消消费接口回调逻辑");
        });


    }

}
