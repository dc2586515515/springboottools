package com.dc.spring.demo.lock;

/**
 * @Description 可重入锁
 * @Author DC
 * @Date 2021-03-29
 */
public class ReentrantLock {
    private boolean isLocked = false;
    Thread lockedBy = null;
    int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && lockedBy != thread) {
            wait();
        }
        isLocked = true;
        lockedBy = thread;
        lockedCount++;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                lockedBy = null;
            }
        }
    }
}
