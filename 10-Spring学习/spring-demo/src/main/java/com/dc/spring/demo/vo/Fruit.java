package com.dc.spring.demo.vo;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-30
 */
public class Fruit {
    public Fruit() {
        System.out.println("无参构造器 Run...........");
    }

    public Fruit(String type) {
        System.out.println("有参构造器 Run..........." + type);
    }
}
