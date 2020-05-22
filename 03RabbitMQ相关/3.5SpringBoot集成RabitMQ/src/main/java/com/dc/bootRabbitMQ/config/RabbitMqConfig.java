package com.dc.bootRabbitMQ.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author DC
 * @Date 2020-05-22
 */
@Configuration
public class RabbitMqConfig {
    public static final String QUEUE_NAME_A = "queueA";
    public static final String QUEUE_NAME_B = "queueB";

    // 我们来创建俩个队列，队列A和队列B

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_NAME_A);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_NAME_B);
    }
}
