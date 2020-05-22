package com.dc.bootRabbitMQ.receiver.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author DC
 * @Date 2020-05-22
 */
@Component
@RabbitListener(queues = "fanoutQueueB")
public class FanoutReceiver2 {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("接收者2号接收到信息:" + msg);
    }
}
