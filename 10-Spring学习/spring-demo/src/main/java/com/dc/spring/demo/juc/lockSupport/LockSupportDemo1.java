package com.dc.spring.demo.juc.lockSupport;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author DC
 * @Date 2021-04-07
 */
public class LockSupportDemo1 {

    /**
     * 1. wait和notify方法必须要在同步块或者方法里面且成对出现使用
     * 2. 先wait后notify才OK，顺序不能反
     */
    static Object objectLock = new Object();

    /**
     * 1. await和signal方法必须要配合 lock() ，unlock() 方法一起使用
     * 2. 先await后signal才OK，顺序不能反
     */
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        // synchornizedWaitNotify();
        // lockAwaitSignal();
        lockSupport();
    }

    /**
     * LockSupport：俗称 锁中断
     * 以前的两种方式：
     * 1.以前的等待唤醒通知机制必须synchronized里面有一个wait和notify
     * 2.lock里面有await和signal
     * 这上面这两个都必须要持有锁才能干，
     * LockSupport它的解决的痛点
     * 1。LockSupport不用持有锁块，不用加锁，程序性能好，
     * 2。先后顺序，不容易导致卡死
     */
    private static void lockSupport() {
        Thread a = new Thread(() -> {
            // 暂停几秒钟线程
            // try { TimeUnit.SECONDS.sleep(3L);} catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t" + "========com in" + System.currentTimeMillis());
            LockSupport.park(); // 被阻塞。。。。等待通知放行，他要通过需要许可证
            // LockSupport.park(); // park unpark一个对象只能调用一次，permit不可累加
            System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒" + System.currentTimeMillis());
        }, "A");
        a.start();

        Thread b = new Thread(() -> {
            LockSupport.unpark(a);
            // LockSupport.unpark(a);// park unpark一个对象只能调用一次，permit不可累加
            System.out.println(Thread.currentThread().getName() + "\t" + "通知了");
        }, "B");
        b.start();
    }

    /**
     * lock await signal 组合使用
     */
    private static void lockAwaitSignal() {
        new Thread(() -> {
            // 暂停几秒钟线程
            // try { TimeUnit.SECONDS.sleep(3L);} catch (InterruptedException e) { e.printStackTrace(); } // 先await后signal才OK，顺序不能反
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "========com in");
                condition.await();  // 阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "======被唤醒");
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "========通知");
                condition.signal(); // 唤醒线程
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }


    /**
     * synchornized  Wait  Notify 组合使用
     */
    private static void synchornizedWaitNotify() {
        new Thread(() -> {
            // 暂停几秒钟线程
            // try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) { e.printStackTrace(); } //  先wait后notify才OK，顺序不能反
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t" + "========com in");
                try {
                    objectLock.wait(); // 阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "========被唤醒");
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify(); // 唤醒
                System.out.println(Thread.currentThread().getName() + "\t" + "==========通知");
            }
        }, "B").start();
    }
}
