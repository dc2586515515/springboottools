package com.dc.synchronizedLock.deadLock;

/**
 * * 死锁原因：
 * * 1.当前线程拥有其他线程需要的资源
 * * 2.当前线程等待其他线程已拥有的资源
 * * 3.都不放弃自己拥有的资源
 *
 * @Description 死锁演示01：锁顺序死锁
 * 线程A调用leftRight()方法，得到left锁
 * 同时线程B调用rightLeft()方法，得到right锁
 * 线程A和线程B都继续执行，此时线程A需要right锁才能继续往下执行。此时线程B需要left锁才能继续往下执行。
 * 但是：线程A的left锁并没有释放，线程B的right锁也没有释放。
 * 所以他们都只能等待，而这种等待是无期限的-->永久等待-->死锁
 * @Author DC
 * @Date 2020-04-16
 */
public class DeadLock01 implements Runnable {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        // 得到left锁
        synchronized (left) {
            System.out.println(Thread.currentThread().getName() + "：拿到left锁");
            try {
                Thread.sleep(1000);  // 使得CPU切换
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 得到right锁
            synchronized (right) {
                System.out.println(Thread.currentThread().getName() + "：拿到right锁");
            }
        }
    }

    public void rightLeft() {
        // 得到right锁
        synchronized (right) {
            System.out.println(Thread.currentThread().getName() + "：拿到right锁");
            // 得到left锁
            synchronized (left) {
                System.out.println(Thread.currentThread().getName() + "：拿到left锁");
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            leftRight();
            rightLeft();
        }
    }

    public static void main(String[] args) {
        DeadLock01 deadLock = new DeadLock01();
        new Thread(deadLock).start();
        new Thread(deadLock).start();
    }

    /**
     * 线程0拿到left锁-->线程0拿到right锁，释放left锁--->线程1拿到left锁，同时线程0拿到right锁
     * 此时，线程0想要left锁。但是left锁被线程1拿走，线程1想要right锁，但是right锁被线程0拿走了
     * 于是，就一直等待对方释放锁。。一直等待。。
     */
}
