package com.dc.spring.circular.dependencies.service;

import org.springframework.stereotype.Component;

/**
 * @Description setter注入
 * @Author DC
 * @Date 2021-03-09
 */
@Component
public class ServiceBB {
    private ServiceAA serviceAA;

    public void setServiceAA(ServiceAA serviceAA) {
        this.serviceAA = serviceAA;
        System.out.println("BB里面设置了AA");
    }
}
