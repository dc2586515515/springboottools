package com.dc.topic.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description  
 * 任何发送到Topic Exchange的消息都会被转发到所有关心RouteKey中指定话题的Queue上。
 * 使用的是一种正则匹配规则。生产者会发送一个带路由键的消息。
 * Exchange会将消息转发到所有关注主题能与RouteKey          模糊匹配            的队列
 * *（星号）   * 可以替代一个单词。
 * （hash）  # 可以替换零个或多个单词
 * @Author DC
 * @Date 2020-05-22
 */
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    private static final String EXCHANGE_NAME = "local::mq04:exchange:e01";

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            String message = "topic交换机很有用";

            //声明一个TOPIC类型的交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            // 发送两条消息，路由键分别为 like.orange.color ,  lazy.boy.girl
            channel.basicPublish(EXCHANGE_NAME, "like.orange.color", null, message.getBytes("UTF-8"));
            // channel.basicPublish(EXCHANGE_NAME, "lazy.boy.girl", null, message.getBytes("UTF-8"));

            LOGGER.info("消息发送成功:{}", message);
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 总结：
     *
     * Topic类型的交换机 
     *
     *         任何发送到Topic Exchange的消息都会被转发到所有关心RouteKey中指定话题的Queue上。使用的是一种正则匹配规则。
     *             生产者会发送一个带路由键的消息。Exchange会将消息转发到所有关注主题能与RouteKey   模糊匹配   的队列。
     *
     * Fanout类型的交换机        
     *
     *         发送到exchange的所有消息会被转发到与exchange绑定的   所有   queue，不需要处理路由 
     *
     * Direct类型的交换机
     *
     *         需要处理路由键。该交换机收到消息后会把消息发送到接收  指定routing-key   的queue中。
     */
}
