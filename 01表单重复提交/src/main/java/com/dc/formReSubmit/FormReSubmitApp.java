package com.dc.formReSubmit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
@MapperScan(value="com.dc.formReSubmit.mapper")
@SpringBootApplication
public class FormReSubmitApp {
    public static void main(String[] args) {
        SpringApplication.run(FormReSubmitApp.class, args);
    }
}
