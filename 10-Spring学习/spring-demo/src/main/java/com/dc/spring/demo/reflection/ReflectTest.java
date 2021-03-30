package com.dc.spring.demo.reflection;

import com.dc.spring.demo.vo.Fruit;

import java.lang.reflect.Constructor;

/**
 * @Description Java反射
 * @Author DC
 * @Date 2021-03-30
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        Class clazz = null;
        clazz = Class.forName("com.dc.spring.demo.vo.Fruit");
        Constructor constructor1 = clazz.getConstructor();
        Fruit fruit1 = (Fruit) constructor1.newInstance();

        Constructor constructor2 = clazz.getConstructor(String.class);
        Fruit fruit2 = (Fruit) constructor2.newInstance("Apple");

        //运行结果： 无参构造器 Run……….. 有参构造器 Run………..Apple

    }
}
