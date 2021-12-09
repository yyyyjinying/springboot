package com.changgou.rabbitmq.three;

import com.changgou.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Task02 {
    private static final String TASK_QUEUE_NAME = "ack_name";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();

        boolean Durable = false; // 队列是否持久化
        channel.queueDeclare(TASK_QUEUE_NAME, Durable, false, false, null);
        // 从控制台中发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.nextLine();
            channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("消息发送完成：" + message);
        }
    }


}
