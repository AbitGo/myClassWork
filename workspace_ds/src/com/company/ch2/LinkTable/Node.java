package com.company.ch2.LinkTable;

public class Node {
    //数据域
    public Object data;
    //指针域
    public Node next;
    public Node(){
        this.data = null;
        this.next = null;
    }

    public Node(Object data){
        this.data = data;
        this.next = null;
    }
    public Node(Object data,Node next){
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }


}
