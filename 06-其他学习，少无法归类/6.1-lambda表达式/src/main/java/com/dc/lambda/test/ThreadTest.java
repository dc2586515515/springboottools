package com.dc.lambda.test;

import org.checkerframework.checker.units.qual.A;

/**
 * @Description lambda 表达式创建线程
 * @Author DC
 * @Date 2020-05-26
 */
public class ThreadTest {
    /**
     * 我们以往都是通过创建 Thread 对象，然后通过匿名内部类重写 run() 方法，
     * 一提到匿名内部类我们就应该想到可以使用 lambda 表达式来简化线程的创建过程。
     */

    // 原本方式创建
   /* public static void main(String[] args) {
        Thread thread = new Thread() {
            public void run() {
                System.out.println("我是一个匿名内部类线程执行方法");
            }
        };
        //开启线程
        thread.start();
    }*/


    // lambda 表达式来简化线程的创建过程
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("我是一个lambda表达式线程执行方法");
        });
        thread.start();
    }
}
