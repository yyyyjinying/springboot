package com.changgou.springbootrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * 函数式接口
 * 1。创建实现类实现接口
 * 2。函数式表达式实现接口
 */
@Component
@Slf4j
public class MyCallback implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {

    // 注入
    @Autowired
    RabbitTemplate rabbitTemplate;

    // 所有注解执行完成之后才会执行的注解
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        /**
         * true：
         * 交换机无法将消息进行路由时，会将该消息返回给生产者
         * false：
         * 如果发现消息无法进行路由，则直接丢弃
         */
        rabbitTemplate.setMandatory(true);
        //设置回退消息交给谁处理
        rabbitTemplate.setReturnsCallback(this);
    }


    /**
     * 交换机确认回调方法
     * 1 发消息 交换机接收到 回调
     * 1。1 correlationDaata 保存回调消息的ID及相关信息
     * 1。2 交换机接收到消息 true
     * 1。3 cause null
     * 2. 发消息 交换机接收失败了 回调
     * 2.1 correlationDaata 保存回调消息的ID及相关信息
     * 2.2 交换机接收到消息 false
     * 2.3 cause null
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack) {
            log.info("交换机已经收到了ID为：{}", id);
        } else {
            log.info("交换机还未收到ID为：{}的消息，由于原因：{}", id, cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {

        Message message = returnedMessage.getMessage();
        String routingKey = returnedMessage.getRoutingKey();
        String exchange = returnedMessage.getExchange();
        String replyText = returnedMessage.getReplyText();
        log.info("消息:{}被服务器退回，退回原因:{}, 交换机是:{}, 路由 key:{}",
                new String(message.getBody()),replyText, exchange, routingKey);


    }
}
