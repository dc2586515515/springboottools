package com.dc.spring.circular.dependencies.constructorinjection;

import com.dc.spring.circular.dependencies.service.ServiceA;
import com.dc.spring.circular.dependencies.service.ServiceB;

/**
 * @Description  构造器方式注入 无法解决循环依赖
 * @Author DC
 * @Date 2021-03-09
 */
public class ClientConstructor {
    public static void main(String[] args) {
        // new ServiceA(new ServiceB(new ServiceA(new ServiceB())));  // 构造器注入会导致 无限循环

    }
}
