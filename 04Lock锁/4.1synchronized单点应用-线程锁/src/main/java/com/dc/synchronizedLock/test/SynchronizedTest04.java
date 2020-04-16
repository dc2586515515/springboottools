package com.dc.synchronizedLock.test;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
public class SynchronizedTest04 {
    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + " ：Method 1 start");
        try {
            System.out.println(Thread.currentThread().getName() + " ：Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +" ：Method 1 end");
    }

    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() +" ：Method 2 start");
        try {
            System.out.println(Thread.currentThread().getName() + " ：Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +" ：Method 2 end");
    }


    public static void main(String[] args) {
        final SynchronizedTest04 test1 = new SynchronizedTest04();
        final SynchronizedTest04 test2 = new SynchronizedTest04();

        /**
         * 两个线程作用于不同的对象 的不同方法，获得的是不同的锁，所以互相并不影响
         */
        // new Thread(test1::method1).start();
        // new Thread(test2::method2).start();

        /**
         * 两个线程作用于不同的对象 的 同一个方法，获得的是不同的锁，所以互相并不影响
         */
        new Thread(test1::method1).start();
        new Thread(test2::method1).start();
    }

    /*
     * 总结：两个线程作用于不同的对象，获得的是不同的锁，所以互相并不影响
     */
}
