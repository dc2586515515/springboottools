package com.dc.spring.demo.jstack;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Description jstack使用测试
 * @Author DC
 * @Date 2021-03-31
 */
@SuppressWarnings("all")
public class JstackTest {
    private static Executor executor = Executors.newFixedThreadPool(5);
    private static final Object lock = new Object();

    public static void main(String[] args) {
        MyRunableImpl myRunable1 = new MyRunableImpl();
        MyRunableImpl myRunable2 = new MyRunableImpl();
        executor.execute(myRunable1);
        executor.execute(myRunable2);

    }

    static class MyRunableImpl implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                // 死循环
                calculate();
            }
        }

        /**
         * 始终只有一个线程能执行
         */
        private void calculate() {
            int i = 0;
            while (true) {
                i++;
            }
        }
    }

}
