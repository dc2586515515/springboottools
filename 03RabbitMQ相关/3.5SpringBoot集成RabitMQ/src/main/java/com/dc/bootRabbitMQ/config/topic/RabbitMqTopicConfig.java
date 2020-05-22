package com.dc.bootRabbitMQ.config.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author DC
 * @Date 2020-05-22
 */
@Configuration
public class RabbitMqTopicConfig {
    /**
     * message队列名
     */
    final String message = "topic.message";

    /**
     * messages队列名
     */
    final String messages = "topic.messages";


    /**
     * 初始化一个叫topic.message的队列
     */
    @Bean
    public Queue queueMessage() {
        return new Queue(message);
    }


    /**
     * 初始化一个叫topic.messages的队列
     */
    @Bean
    public Queue queueMessages() {
        return new Queue(messages);
    }

    /**
     * 创建一个叫exchange的路由器
     * 路由器负责分发消息到指定的队列
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    /**
     * 约定大于配置
     * 这个方法的参数queueMessage就是本类中queueMessage()方法的返回值
     * 这个方法的参数exchange就是本类中exchange()方法的返回值
     * 如果发送者投递的路由键是：topic.message，那么就把数据发送到queueMessage队列中
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 约定大于配置
     * 这个方法的参数queueMessage就是本类中queueMessage()方法的返回值
     * 这个方法的参数exchange就是本类中exchange()方法的返回值
     * 如果发送者投递的路由键是：topic.#，即路由键使用topic开头的
     * 那么就把数据发送到queueMessages队列中
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        //这里的#表示零个或多个词。
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}