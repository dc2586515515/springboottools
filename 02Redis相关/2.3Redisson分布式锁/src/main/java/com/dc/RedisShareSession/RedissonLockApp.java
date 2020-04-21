package com.dc.RedisShareSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
@SpringBootApplication
@EnableSwagger2
public class RedissonLockApp {
    public static void main(String[] args) {
        SpringApplication.run(RedissonLockApp.class, args);
    }
}
