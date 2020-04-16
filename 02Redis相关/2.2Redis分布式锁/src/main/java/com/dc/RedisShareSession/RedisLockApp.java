package com.dc.RedisShareSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
@SpringBootApplication
@EnableScheduling
public class RedisLockApp {
    public static void main(String[] args) {
        SpringApplication.run(RedisLockApp.class, args);
    }
}
