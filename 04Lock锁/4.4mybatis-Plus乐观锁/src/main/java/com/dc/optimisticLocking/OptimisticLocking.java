package com.dc.optimisticLocking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
@SpringBootApplication
@MapperScan(value = "com.dc.optimisticLocking.mapper")
public class OptimisticLocking {
    public static void main(String[] args) {
        SpringApplication.run(OptimisticLocking.class, args);
    }
}
