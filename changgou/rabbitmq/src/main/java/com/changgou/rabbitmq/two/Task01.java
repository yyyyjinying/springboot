package com.changgou.rabbitmq.two;

import com.changgou.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Task01 {
    private static final String QUEUE_NAME = "hello1";

    public static void main(String[] args) throws IOException, TimeoutException {
        try (Channel channel = RabbitMqUtils.getChannel();) {
            /**
             * 队列名
             * 是否持久化
             * true共享 exclusive
             * 是否自动删除
             * 其他参数
             *
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 从控制台当中接受信息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String message = scanner.next();

                /**'
                 * 默认交换机
                 * routing——key
                 * 其他参数  ：可以设置生产者发送消息为持久化消息（要求保存到磁盘上）保存在内存中 MessageProperties.PERSISTET_TEXT_PLAIN
                 * 消息体
                 */
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("发送消息完成:" + message);
            }
        }


    }
}
