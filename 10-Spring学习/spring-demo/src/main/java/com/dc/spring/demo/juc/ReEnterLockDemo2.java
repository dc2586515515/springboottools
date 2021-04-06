package com.dc.spring.demo.juc;

/**
 * @Description 可重入锁:可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
 * 在一个synchronized修饰的方法或代码块的内部
 * 调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 * @Author DC
 * @Date 2021-04-06
 */
public class ReEnterLockDemo2 {

    /**
     * synchronized同步方法，可重入锁
     */

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "\t" + "外层调用");
        m2();
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t" + "中层调用");
        m3();
    }

    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t" + "内层调用");
    }


    public static void main(String[] args) {
        new ReEnterLockDemo2().m1();
    }
}
