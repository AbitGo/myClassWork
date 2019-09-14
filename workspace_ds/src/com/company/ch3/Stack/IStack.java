package com.company.ch3.Stack;

public interface IStack {
    public void clear();
    public boolean isEmpty();
    public int length();
    public Object peek();
    public boolean push(Object x)throws Exception;
    public Object pop();
}
