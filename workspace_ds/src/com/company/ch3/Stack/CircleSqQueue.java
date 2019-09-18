package com.company.ch3.Stack;

import com.company.ch3.IQueue;

public class CircleSqQueue implements IQueue {
    private Object[] queueElem;
    private int front;
    private int rear;
    private int maxSize;

    public CircleSqQueue(int maxSize)
    {
        this.maxSize = maxSize;
        front = rear = 0;
        queueElem = new Object[maxSize];
    }

    public Object[] getQueueElem() {
        return queueElem;
    }

    public void setQueueElem(Object[] queueElem) {
        this.queueElem = queueElem;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void clear() {
        //将头与尾设置为0即可
        front = rear = 0;
    }

    @Override
    public boolean isEmpty() {
        if(front == rear){
            return true;
        }
        return false;
    }

    @Override
    public int length() {
        return (rear-front+queueElem.length)%queueElem.length;
    }

    @Override
    public Object peek() {
        if(isEmpty()){
            return null;
        }else
            return queueElem[this.getFront()];
    }

    @Override
    public void offer(Object x) {
        //入队

        //当队列已满的时候
        if((rear+1)%queueElem.length==front){
            System.out.println("the Sqack is full");
            return ;
        }else{
            queueElem[rear]=x;
        }

        //修改队尾指针
        rear = (rear+1)%queueElem.length;

    }

    @Override
    public Object poll() {
        //出队
        return null;
    }
}
