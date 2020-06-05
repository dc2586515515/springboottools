package com.dc.kafka.controller;

import com.dc.kafka.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author DC
 * @Date 2020-06-05
 */
@Controller
public class KafkaController {
    @Autowired
    private Producer producer;

    @RequestMapping("/testSendMsg")
    @ResponseBody
    public String testSenMsg(String mes) {
        producer.send(mes);
        return "success";
    }

}
