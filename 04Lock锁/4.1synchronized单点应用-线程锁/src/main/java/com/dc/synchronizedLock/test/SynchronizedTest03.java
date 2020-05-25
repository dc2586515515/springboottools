package com.dc.synchronizedLock.test;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
public class SynchronizedTest03 {
    public synchronized void method1() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    // public synchronized void method2() {
    public void method2() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }


    public static void main(String[] args) {
        final SynchronizedTest03 test = new SynchronizedTest03();

        new Thread(test::method1).start();

        new Thread(test::method2).start();
    }

    /**
     *  当线程1还在执行时，线程2也执行了，
     *  因为其他线程来访问 非 synchronized 修饰的方法时是可以访问的
     */
}
