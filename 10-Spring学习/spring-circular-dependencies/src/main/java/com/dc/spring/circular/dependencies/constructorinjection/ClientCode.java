package com.dc.spring.circular.dependencies.constructorinjection;

import com.dc.spring.circular.dependencies.service.A;
import com.dc.spring.circular.dependencies.service.B;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-09
 */
public class ClientCode {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        a.setB(b);
        b.setA(a);
    }
}


