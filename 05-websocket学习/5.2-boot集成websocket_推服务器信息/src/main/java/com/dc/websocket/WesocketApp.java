package com.dc.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description
 * @Author DC
 * @Date 2020-05-25
 */
@SpringBootApplication
@EnableScheduling
public class WesocketApp {
    public static void main(String[] args) {
        SpringApplication.run(WesocketApp.class, args);
    }
}
