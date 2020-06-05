package com.dc.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author DC
 * @Date 2020-06-05
 */
@Component
public class Consumer {

    /**
     * 监听接收kafka topic消息
     * @param record
     */
    @KafkaListener(topics = "kafka-test-Topic")
    public void kafkaListen(ConsumerRecord<?, ?> record) {
        System.out.println("消费的字符串消息--监听：" + "topic=" + record.topic() + ", offset="
                + record.offset() + ", value=" + record.value());
    }
}
