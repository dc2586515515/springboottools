package com.dc.spring.circular.dependencies.service;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-09
 */
public class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }


    public B() {
        System.out.println("---B created success");

    }
}


