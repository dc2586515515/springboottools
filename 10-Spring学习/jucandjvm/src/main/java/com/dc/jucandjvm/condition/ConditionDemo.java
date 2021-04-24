package com.dc.jucandjvm.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author DC
 * @Date 2021-04-16
 * <p>
 * 多线程之间按顺序调用 ，实现A -> B -> C
 * 三个线程启动，要求顺序如下：
 * <p>
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 来10轮
 */
public class ConditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
    }
}


class ShareData {
    private int number = 1; // A:1 B:2 C:3

    private Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //1 判断
            while (number != 1) {
                // wait...
                c1.await();
            }

            //2 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t打印" + i);
            }

            //3 通知 多线程一定要先修改标识字段
            number = 2;
            // 如何通知第二个？
            c2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
