package com.company.ch3.AlgorithmDesgin;

import com.company.ch2.LinkTable.Node;
import com.company.ch3.IQueue;

public class p106_3_5 implements IQueue {
    private Node head;
    private Node rear;

    public p106_3_5(){
        this.head = new Node();
        this.head.next = null;
        this.rear = null;
    }

    @Override
    public void clear() {
        this.head.next = rear = null;
    }

    @Override
    public boolean isEmpty() {
        if (this.head.next == null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int length() {
        int count = 0;
        Node p = this.head.next;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    //取队内首元素
    @Override
    public Object peek() {
        if (this.isEmpty()) {
            System.out.println("此队列为空");
            return null;
        } else {
            return this.head.next.data;
        }
    }

    //入队
    @Override
    public void offer(Object x) {
        Node p = new Node(x);
        if (this.isEmpty()) {
            this.head.next = p;
            rear = p;
        } else {
            this.rear.next = p;
            this.rear = p;
        }
    }

    //出队
    @Override
    public Object poll() {
        Object p = new Object();
        if (this.isEmpty()) {
            System.out.println("此队列为空");
            return null;
        } else {
            p = this.head.next.data;
            //当剩下一个元素的时候
            if (this.head.next == rear) {
                this.head.next = rear = null;
            }else{
                this.head.next = this.head.next.next;
            }
            return p;
        }
    }

    public void display() {
        Node p = this.head.next;
        while (p != null) {
            System.out.print(" " + p.data);
            p = p.next;
        }
        System.out.println();
    }
}
