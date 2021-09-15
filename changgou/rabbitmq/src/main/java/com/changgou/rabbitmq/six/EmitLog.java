package com.changgou.rabbitmq.six;

import com.changgou.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class EmitLog {
    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        // 创建多个bindngKey
        HashMap<String, String> bindingKeyMap = new HashMap<>();
        bindingKeyMap.put("info","普通info信息");
        bindingKeyMap.put("warning","警告warning信息");
        bindingKeyMap.put("error","错误error信息");
        // debug没有消费接收 所以丢弃
        bindingKeyMap.put("debug","调试debug信息");

        for (Map.Entry<String, String> entry : bindingKeyMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            /**
             * basicPublish
             * 交换机
             * 队列
             * 持久化
             * null
             * 消息
             */
            channel.basicPublish(EXCHANGE_NAME,key,false, null,value.getBytes("UTF-8"));
            System.out.println("生产者发出消息："+value);
        }

    }
}
