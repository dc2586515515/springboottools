package com.dc.spring.circular.dependencies.service;

import org.springframework.stereotype.Component;

/**
 * @Description 构造器注入
 * @Author DC
 * @Date 2021-03-09
 */
@Component
public class ServiceB {
    private ServiceA serviceA;

    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
