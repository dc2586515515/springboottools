package com.dc.spring.demo.lock;

/**
 * @Description 不可重入锁
 * @Author DC
 * @Date 2021-03-29
 */
public class UnReentrantLock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unLock() {
        isLocked = false;
        notify();
    }
}
