package com.company.ch3.LinkStack;

import com.company.ch2.LinkTable.Node;
import com.company.ch3.Stack.IStack;

public class LinkStack implements IStack {

    //栈顶元素
    private Node top;
    @Override
    public void clear() {
        this.top = null;
    }

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    @Override
    public int length() {
        int len = 0;
        Node  p = this.top;
        while(p!=null){
            ++len;
            p = p.next;
        }
        return len;
    }

    @Override
    public Object peek() {
        if(!isEmpty()){
            return top.data;
        }else {
            return null;
        }
    }

    @Override
    public boolean push(Object x) throws Exception {
        Node p = new Node(x);
        p.next = top;
        top = p;
        return true;
    }

    @Override
    public Object pop() {
        if(isEmpty()){
            System.out.println("栈已空");
            return null;
        }else{
            Node pop = top;
            top = top.next;
            return pop.data;
        }
    }

    public void display(){
        Node p = top;
        while (p != null) {
            System.out.println(p.data+" ");
            p = p.next;
        }
        System.out.println();
    }
}
