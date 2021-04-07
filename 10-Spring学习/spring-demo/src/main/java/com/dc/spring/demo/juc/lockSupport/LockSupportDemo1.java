package com.dc.spring.demo.juc.lockSupport;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    public static void main(String[] args) {
        new Thread(() -> {
            // 暂停几秒钟线程
            // try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) { e.printStackTrace(); } //  先wait后notify才OK，顺序不能反
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t" + "========com in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "========被唤醒");
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t" + "==========通知");
            }
        }, "B").start();

    }
}
