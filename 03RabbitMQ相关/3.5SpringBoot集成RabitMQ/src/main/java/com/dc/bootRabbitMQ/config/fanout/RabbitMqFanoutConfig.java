package com.dc.bootRabbitMQ.config.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author DC
 * @Date 2020-05-22
 */
@Configuration
public class RabbitMqFanoutConfig {
    /**
     * 初始化一个叫queueA的队列
     */
    @Bean
    public Queue queueA() {
        return new Queue("fanoutQueueA");
    }


    /**
     * 初始化一个叫queueB的队列
     */
    @Bean
    public Queue queueB() {
        return new Queue("fanoutQueueB");
    }

    /**
     * 创建一个叫fanoutExchange的路由器
     * 路由器负责分发消息到指定的队列
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }


    /**
     * 直接把队列绑定到交换机身上，不用路由键
     * 也就是说，交换机把消息直接投递到绑定它的队列中
     * 不需要路由键
     */
    @Bean
    Binding bindingFanoutExchangeMsg(Queue queueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutExchangeMsgs(Queue queueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }
}
