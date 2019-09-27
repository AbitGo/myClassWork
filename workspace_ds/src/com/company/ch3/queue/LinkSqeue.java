package com.company.ch3.queue;

import com.company.ch2.LinkTable.Node;
import com.company.ch3.IQueue;

public class LinkSqeue implements IQueue {
    private Node front;
    private Node rear;

    public LinkSqeue() {
        this.front = this.rear = null;
    }

    @Override
    public void clear() {
        this.front = this.rear = null;
    }

    @Override
    public boolean isEmpty() {
        if (this.front == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int length() {
        Node p = this.front;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }
        return len;
    }

    //取队内首元素
    @Override
    public Object peek() {
        if (this.isEmpty() == true) {
            System.out.println("队列为空");
            return null;
        } else {
            return this.front.data;
        }
    }

    //入队
    @Override
    public void offer(Object x) {
        Node p = new Node(x);
        if(this.isEmpty()==true){
            this.front = this.rear =p;
        }else{
            this.rear.next = p;
            this.rear = p;
        }
    }

    //出队
    @Override
    public Object poll() {
        if(this.isEmpty()==true){
            System.out.println("队列为空");
            return null;
        }else{
            Object p = this.front.data;
            this.front = this.front.next;
            if(p==rear){
                this.rear = null;
                this.front = null;
            }
            return p;
        }
    }

    public void display(){
        Node p = this.front;
        while (p!=null){
            System.out.print(" "+p.data);
            p = p.next;
        }
        System.out.println();
    }
}
