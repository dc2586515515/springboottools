package com.dc.synchronizedLock.test;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
public class SynchronizedTest02 {
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

    public synchronized void method2() {
        // public  void method2() {
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
        final SynchronizedTest02 test = new SynchronizedTest02();

        new Thread(test::method1).start();

        new Thread(test::method2).start();
    }

    /**
     * 分析：可以看出其他线程来访问 synchronized修饰的 其他方法 时需要等待线程1先把锁释放
     * 一个对象只有一把锁，一个线程获取了该对象的锁之后，其他线程无法获取该对象的锁，
     * 就不能访问该对象的其他synchronized实例方法，但是可以访问非synchronized修饰的方法
     */
}
