package com.dc.spring.demo.lazyandhungay;

/**
 * @Description 懒汉式
 * @Author DC
 * @Date 2021-04-13
 */
public class LazySingleton {
    //默认不会实例化，什么时候用就什么时候new
    private static LazySingleton instance = null;

    public LazySingleton() {

    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            //什么时候用就什么时候new
            instance = new LazySingleton();
        }
        return instance;
    }
}
