package com.dc.spring.demo.algorithm;

public class DeadLock implements Runnable {
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

                System.out.println("lockA && flag = true");
                synchronized (objectB) {
                    System.out.println("lockB && flag = true");
                }
            }
        } else {
            synchronized (objectB) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("lockB && flag = false");
                synchronized (objectA) {
                    System.out.println("lockA && flag = false");
                }
            }
        }
    }
}

class test {
    public static void main(String[] args) {
        DeadLock deadLock1 = new DeadLock();
        DeadLock deadLock2 = new DeadLock();
        deadLock1.flag = true;
        deadLock2.flag = false;

        new Thread(deadLock1).start();
        new Thread(deadLock2).start();
    }


}
