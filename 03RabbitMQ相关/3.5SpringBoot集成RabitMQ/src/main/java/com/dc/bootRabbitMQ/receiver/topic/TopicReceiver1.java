package com.dc.bootRabbitMQ.receiver.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author DC
 * @Date 2020-05-22
 */
@Component
@RabbitListener(queues = "topic.message")
public class TopicReceiver1 {
    private Logger logger = LoggerFactory.getLogger("TopicReceiver1");

    @RabbitHandler
    public void process(String msg) {
        System.out.println("接收者1号接收到信息:" + msg);
    }
}
