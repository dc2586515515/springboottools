package com.dc.spring.demo.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 可重入锁:可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
 * * 在一个synchronized修饰的方法或代码块的内部
 * * 调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 * @Author DC
 * @Date 2021-04-06
 */
public class ReEnterLockDemo3 {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                System.out.println("=======外层");
                // lock() 与 unlock()数量必须一致，否则，其他线程会一直阻塞
                lock.lock();
                // lock.lock();
                try {
                    System.out.println("=======内层");
                    lock.lock();
                } finally {
                    lock.unlock();
                }
            } finally {
                //实现加锁次数和释放次数不一样
                //由于加锁次数和释放次数不一样，第二个线程始终无法获取到锁，导致一直在等待。
                lock.unlock();
                // lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "------调用开始");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
