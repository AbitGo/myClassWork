package com.company.ch3;

public interface IQueue {
    public void clear();
    public boolean isEmpty();
    public int length();
    public Object peek();
    public void offer(Object x);
    public Object poll();
}
