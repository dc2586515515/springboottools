package com.dc.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.dc.spider.mapper")
@SpringBootApplication
public class Spider02 {
    public static void main(String[] args) {
        SpringApplication.run(Spider02.class);
    }
}