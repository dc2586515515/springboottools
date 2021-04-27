package com.dc.spring.demo.algorithm;

public class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
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

        Node h = head;
        while (null != h) {
            System.out.print(h.value + " ");
            h = h.getNext();
        }

        System.out.println("**************");

        head = reverse(head);
        while (null != head) {
            System.out.print(head.value + " ");
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
