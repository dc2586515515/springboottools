package com.dc.jucandjvm.juc;

class AriCondition {
    private int number = 0;

    public synchronized void increment() throws Exception {
        String threadName = Thread.currentThread().getName();
        // 1 判断
        // if (number != 0) {
        while (number != 0) {
            this.wait();
        }

        // 2 干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //3 通知
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception {
        String threadName = Thread.currentThread().getName();
        // 1 判断
        // if (number == 0) {
        while (number == 0) {
            this.wait();
        }

        // 2 干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //3 通知
        this.notifyAll();
    }
}

/**
 * @Description
 * @Author DC
 * @Date 2021-04-14
 * 题目:现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零。
 * <p>
 * 1 高聚低合前提下，线程操作资源类
 * 2 判断/干活/通知
 * 3 防止多线程虚假唤醒
 */
public class ProdConsumerDemo4 {
    public static void main(String[] args) throws Exception {
        AriCondition ariCondition = new AriCondition();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    ariCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();


        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    ariCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    ariCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();


        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    ariCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}