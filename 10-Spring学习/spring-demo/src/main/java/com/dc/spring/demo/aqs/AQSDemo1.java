package com.dc.spring.demo.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author DC
 * @Date 2021-04-08
 */
public class AQSDemo1 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        // 带入一个银行办理业务的案例来模拟我们的AQs如何进行线程的管理和通知唤醒机制
        // 3个线程模拟3个来银行网点，受理窗口办理业务的顾客

        // A顾客就是第一个顾客，此时受理窗口没有任何人，A可以直接去办理
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "------com in");
                // 暂停20分钟线程
                try { TimeUnit.MINUTES.sleep(20L);} catch (InterruptedException e) { e.printStackTrace(); }
            } finally {
                lock.unlock();
            }
        }, "A").start();

        //第2个顾客，第2个线程---->，由于受理业务的窗口只有一个(只能一个线程持有锁)，此时B只能等待,
        // 进入候客区
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "------com in");
            } finally {
                lock.unlock();
            }
        }, "B").start();

        //第3个顾客，第3个线程---->，由于受理业务的窗口只有一个(只能一个线程持有锁)，此时C只能等待,
        // 进入候客区
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "------com in");
            } finally {
                lock.unlock();
            }
        }, "C").start();

    }
}
