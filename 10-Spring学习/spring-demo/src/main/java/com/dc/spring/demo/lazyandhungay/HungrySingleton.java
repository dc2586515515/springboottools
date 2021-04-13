package com.dc.spring.demo.lazyandhungay;

/**
 * @Description 饿汉式
 * @Author DC
 * @Date 2021-04-13
 */
public class HungrySingleton {
    //一开始类加载的时候就实例化，创建单实例对象
    private static HungrySingleton instance = new HungrySingleton();

    public HungrySingleton() {

    }

    public static synchronized HungrySingleton getInstance() {
        return instance;
    }
}
