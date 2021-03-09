package com.dc.spring.circular.dependencies.service;

import org.springframework.stereotype.Component;

/**
 * @Description 构造器注入
 * @Author DC
 * @Date 2021-03-09
 */
@Component
public class ServiceA {
    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
