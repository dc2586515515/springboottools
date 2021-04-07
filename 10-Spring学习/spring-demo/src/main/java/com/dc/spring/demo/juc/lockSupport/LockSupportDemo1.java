package com.dc.spring.demo.juc.lockSupport;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
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
