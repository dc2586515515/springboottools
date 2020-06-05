package com.dc.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author DC
 * @Date 2020-06-05
 */
@Component
public class Producer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送
     *
     * @param msg
     */
    public void send(String msg) {
        kafkaTemplate.send("kafka-test-Topic", msg);
    }
}
