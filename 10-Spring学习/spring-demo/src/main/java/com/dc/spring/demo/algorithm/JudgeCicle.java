package com.dc.spring.demo.algorithm;

public class JudgeCicle {

    public static void main(String[] args) {

        Point n1 = new Point(1);
        Point n2 = new Point(2);
        Point n3 = new Point(3);
        Point n4 = new Point(4);
        Point n5 = new Point(5);
        Point n6 = new Point(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        // n6.next = n3;  //构造一个带环的链表,去除此句表示不带环


        System.out.println(hasLoop(n1));

    }

    public static boolean hasLoop(Point n) {

        //定义两个指针tmp1,tmp2
        Point temp1 = n;
        Point temp2 = n.next;


        while (temp2 != null) {
            int d1 = temp1.val;
            int d2 = temp2.val;
            if (d1 == d2) return true; // 存在环
            temp1 = temp1.next;
            if (temp2.next != null) {
                temp2 = temp2.next.next;
                if (temp2 == null) return false; // 不存在环
            } else {
                return false;
            }

        }

        return true; // 说明只有一个元素，也可以认为是闭环

    }

}


class Point {
    public int val;
    public Point next;

    public Point(int data) {
        this.val = data;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Point getNext() {
        return next;
    }

    public void setNext(Point next) {
        this.next = next;
    }
}