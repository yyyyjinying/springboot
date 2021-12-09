package com.changgou.rabbitmq.one;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class Producer {
    // 队列名称
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 工厂IP 连接Rabbitmq的队列
        factory.setHost("172.16.147.184"); // http://172.16.147.184/
        factory.setUsername("mm");
        factory.setPassword("123456");

        // 建立联机
        Connection connection = factory.newConnection("app:audit component:event-producer");
        // 创建信道
        Channel channel = connection.createChannel();


        /**
         * 配置优先级队列
         */
//       HashMap<String, Object> argments = new HashMap<>();
//        argments.put("x-max-priority", 10);


        // 创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "hello world099";

//        for (int i = 0; i < 11 ; i++) {
//            String message = "message" + i;
//
//            if (i==5) {
//                AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().priority(5).build();
//                channel.basicPublish("",QUEUE_NAME, properties ,message.getBytes());
//            }else{
//                // 发送消息
//                channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
//            }
//        }
//
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕！");



    }
}
