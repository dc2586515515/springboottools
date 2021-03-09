package com.dc.spring.circular.dependencies.service;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-09
 */
public class A {
    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public A() {
        System.out.println("---A created success");
    }
}
