package com.dc.spring.demo.reverselb;

public class Node {
    public int value;
    public Node next;

    public Node(int data) {
        this.value = data;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}


class TestReverse {
    public static void main(String[] args) {
        Node head = new Node(0);
        Node node1 = new Node(1);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        // 打印反转前的链表
        Node h = head;
        while (null != h) {
            System.out.print(h.getValue() + " ");
            h = h.getNext();
        }

        System.out.println("\n**************************");

        // 调用反转方法
        head = reverse(head);
        // 打印反转后的结果
        while (null != head) {
            System.out.print(head.getValue() + " ");
            head = head.getNext();
        }

    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node temp = head.next;
        Node newHead = reverse(temp);
        temp.next = head;
        head.next = null;
        return newHead;
    }
}


