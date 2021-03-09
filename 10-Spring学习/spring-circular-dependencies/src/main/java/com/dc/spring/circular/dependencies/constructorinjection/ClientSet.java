package com.dc.spring.circular.dependencies.constructorinjection;

import com.dc.spring.circular.dependencies.service.ServiceAA;
import com.dc.spring.circular.dependencies.service.ServiceBB;

/**
 * @Description setter方式 依赖注入 的循环依赖可以解决
 * @Author DC
 * @Date 2021-03-09
 */
public class ClientSet {
    public static void main(String[] args) {
        // 创建ServiceAA实例
        ServiceAA serviceAA = new ServiceAA();
        // 创建ServiceBB实例
        ServiceBB serviceBB = new ServiceBB();
        // 将serviceAA注入到serviceBB里面
        serviceBB.setServiceAA(serviceAA);
        // 将serviceBB注入到serviceAA
        serviceAA.setServiceBB(serviceBB);
    }
}
