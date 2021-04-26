package com.dc.spring.demo.deadlock;

class Entrance {
    public static void main(String[] args) {

        DeadLockThread deadLockThread1 = new DeadLockThread();
        DeadLockThread deadLockThread2 = new DeadLockThread();

        deadLockThread1.flag = true;
        deadLockThread2.flag = false;

        new Thread(deadLockThread1).start();
        new Thread(deadLockThread2).start();
    }

}

public class DeadLockThread implements Runnable {
    public static final Object objectA = new Object();
    public static final Object objectB = new Object();
    public boolean flag;

    @Override
    public void run() {
        if (flag) {
            synchronized (objectA) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("flag=true && lock objectA");
                synchronized (objectB) {
                    System.out.println("flag=true && lock objectB");
                }
            }
        } else {
            synchronized (objectB) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("flag=false && lock objectB");
                synchronized (objectA) {
                    System.out.println("flag=false && lock objectA");
                }
            }
        }
    }
}
