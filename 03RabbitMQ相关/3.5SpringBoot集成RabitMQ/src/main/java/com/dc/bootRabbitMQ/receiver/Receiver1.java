package com.dc.bootRabbitMQ.receiver;

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
@RabbitListener(queues = "queueA")
public class Receiver1 {

    @RabbitHandler
    public void receiver(String msg) {
        System.out.println("接收者1号获取到数据" + msg);
    }
}
