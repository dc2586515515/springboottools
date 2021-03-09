package com.dc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-09
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AopAppcation {
    public static void main(String[] args) {
        SpringApplication.run(AopAppcation.class, args);
    }
}
