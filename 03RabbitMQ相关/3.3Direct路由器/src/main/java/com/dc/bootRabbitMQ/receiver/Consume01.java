package com.dc.topic.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Description
 * @Author DC
 * @Date 2020-05-22
 */
public class Consume01 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consume01.class);
    private static final String EXCHANGE_NAME = "local::mq03:exchange:e01";
    private static final String QUEUE_NAME_01 = "local::mq03:queue:q01";

    /**
     * 路由键:美女
     */
    private static final String ROUTING_KEY_BEAUTY = "routekey_beauty";

    public static void main(String[] args) {
        try {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            //声明一个Direct类型的交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            //申明两队列
            channel.queueDeclare(QUEUE_NAME_01, true, false, false, null);
            //开始绑定
            //队列一对交换机说，关于美女的消息给我
            channel.queueBind(QUEUE_NAME_01, EXCHANGE_NAME, ROUTING_KEY_BEAUTY);

            //模拟接收消息
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    final String message = new String(body, "UTF-8");
                    LOGGER.info("队列一收到消息:{}", message);
                }
            };

            //队列一确认收到消息
            channel.basicConsume(QUEUE_NAME_01, true, consumer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
