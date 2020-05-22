package com.dc.ybjoRabbitMQ.receiver;

import com.dc.ybjoRabbitMQ.entity.UserEntity;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description 监听队列01的消息，关于美女的消息，此队列都感兴趣
 * @Author DC
 * @Date 2020-05-22
 */
@Component
@RabbitListener(queues = {"${defineProps.rabbit.direct.queue.queue01}"})
public class BeautyListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeautyListener.class);

    @RabbitHandler
    public void receiver(@Payload UserEntity msg, @Headers Channel channel, Message message) throws IOException {
        LOGGER.info("接收到的消息:{}", msg);
        try {
            // 确认消息已经消费成功

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            LOGGER.info("确认处理消息:{}", msg);
        } catch (IOException e) {
            LOGGER.error("消费处理异常:{} - {}", msg, e);
            // 拒绝当前消息，并把消息返回原队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
