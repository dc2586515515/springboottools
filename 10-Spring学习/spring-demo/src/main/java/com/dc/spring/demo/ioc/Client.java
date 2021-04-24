package com.dc.spring.demo.ioc;


interface Fruit {
    public void eat();
}

class Apple implements Fruit {

    @Override
    public void eat() {
        System.out.println("吃苹果");
    }
}

class Orange implements Fruit {

    @Override
    public void eat() {
        System.out.println("吃橘子");
    }
}

/**
 * 工厂模式 + 反射
 */
class Factory {
    public static Fruit getInstance(String name) {
        Fruit f = null;
        try {
            Class<?> fruit = Class.forName(name);
            f = (Fruit) fruit.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}

/**
 * @Description SpringIOC 原理 : 工厂模式 + 反射
 * @Author DC
 * @Date 2021-04-24
 */
public class Client {
    public static void main(String[] args) {
        Fruit f = Factory.getInstance("com.dc.spring.demo.ioc.Apple");
        if (f != null) {
            f.eat();
        }
    }
}
