package com.dc.spring.demo.juc.reEnterLock;


/**
 * 可重入锁:可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
 * 在一个synchronized修饰的方法或代码块的内部
 * 调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 */
public class ReEnterLockDemo1 {
    static Object objectLockA = new Object();

    /**
     * 1. 同步代码块
     */
    static void m1() {
        new Thread(() -> {
            synchronized (objectLockA) {
                System.out.println(Thread.currentThread().getName() + "\t" + "外层调用");
                synchronized (objectLockA) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "中层调用");
                    synchronized (objectLockA) {
                        System.out.println(Thread.currentThread().getName() + "\t" + "内层调用");
                    }
                }
            }
        }, "t1").start();
    }


    public static void main(String[] args) {
        m1();
    }
}
