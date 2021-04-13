package com.dc.jucandjvm.juc;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket  // 资源类=实例变量+实例方法
{
    private int number = 30;
    // List list  = new ArryList();

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t 卖出了第：" + (number--) + "\t还剩下：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @Description 题目：三个售票员         卖出    30张票
 * 笔记： 如何编写企业级的多线程代码
 * 固定的编程编程套路 + 模板是什么？
 * 1. 在高内聚，低耦合的前提下，     线程 ——> 操作 ——>  资源类
 * 1.1 一言不合，先创建一个资源类
 * @Author DC
 * @Date 2021-04-12
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {  // 主线程，一切程序入口

        // 先创建一个资源类
        Ticket ticket = new Ticket();

        // lambda表达式代替 匿名内部类
        new Thread(() -> {  for (int i = 0; i < 40; i++) ticket.sale();  }, "A").start();
        new Thread(() -> {  for (int i = 0; i < 40; i++) ticket.sale();  }, "B").start();
        new Thread(() -> {  for (int i = 0; i < 40; i++) ticket.sale();  }, "C").start();


        // Thread(Runnable target, String name) 分配一个新的 Thread对象。
       /* new Thread(
                // new Interface接口，实现内部方法 ==== 就是匿名内部类
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 40; i++) {
                            ticket.sale();
                        }
                    }
                }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();*/

    }
}
