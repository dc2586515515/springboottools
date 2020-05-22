package com.dc.bootRabbitMQ.send;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Description
 * @Author DC
 * @Date 2020-05-22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMsgTest {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        String message = "Hello world";
        String receiver = "queueA";
        amqpTemplate.convertAndSend(receiver, message);
    }


    /**
     * 再次测试发送消息，这次循环发送消息。
     * 消息向队列queueA发送，监听queueA队列的两个接收者：接收者1号和接收者2号，
     * 会自动负载均衡。并且每个消息只会被接收一次
     */
    @Test
    public void sendManyTimes() {
        String message = "Hello world";
        String receiver = "queueA";
        for (int i = 0; i < 10; i++) {
            amqpTemplate.convertAndSend(receiver, message + i);
        }

    }


    @Test
    public void sendManyTimes2() {
        String message = "Hello world";
        String receiverA = "queueA";
        String receiverB = "queueB";
        for (int i = 0; i < 5; i++) {
            amqpTemplate.convertAndSend(receiverA, message + i);
            amqpTemplate.convertAndSend(receiverB, message + i);
        }
    }


    /**
     * 使用Topic Exchange广播
     */
    @Test
    public void test2() {
        String message = "hello world";
        System.out.println("发送者说 : " + message);
        String exchangeName = "exchange";
        String route_key = "topic.message"; //Receiver1/Receiver2 都可以接收到消息
        // String route_key = "topic.Test";  //只有Receiver2 可以接收到消息
        //数据message交给交换器exchange，交换器将其投递到队列中
        //监听这个队列的接收者将会收到消息
        this.amqpTemplate.convertAndSend(exchangeName, route_key, message);
    }


    /**
     * Fanout Exchange（订阅模式）
     */
    @Test
    public void fanoutMessage() {
        String message = "hello world";
        //路由键在Fanout广播形式中无效
        String route_key = "";
        System.out.println("发送者说 : " + message);
        String exchangeName = "fanoutExchange";
        //数据message交给交换器exchange，交换器将其投递到队列中
        //监听这个队列的接收者将会收到消息
        this.amqpTemplate.convertAndSend(exchangeName, route_key, message);

    }


}
