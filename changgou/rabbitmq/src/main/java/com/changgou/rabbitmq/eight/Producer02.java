package com.changgou.rabbitmq.eight;

import com.changgou.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
// 发送之后，去rabbitmq管理页面会看到normal——queue中有6个消息，dead-queue队列中有4个消息，测试成功
public class Producer02 {
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        //设置消息的 TTL 时间  10秒内正常消费者没有接收，就会转入死交换机
//        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();

        //该信息是用作演示队列个数限制
        for (int i = 0; i <10 ; i++) {
            String message="info"+i;
//            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",properties, message.getBytes());
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",null, message.getBytes());
            System.out.println("生产者发送消息:"+message);
        }

    }
}
