package com.dc.spring.circular.dependencies.service;

import org.springframework.stereotype.Component;

/**
 * @Description setter注入
 * @Author DC
 * @Date 2021-03-09
 */
@Component
public class ServiceAA {
    private ServiceBB serviceBB;

    public void setServiceBB(ServiceBB serviceBB) {
        this.serviceBB = serviceBB;
        System.out.println("AA里面设置了BB");
    }
}
