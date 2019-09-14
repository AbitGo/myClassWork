package com.company.ch2.DuLinkTable;

public class DuNode {
    public Object data;
    public DuNode next;
    public DuNode prior;

    public DuNode(){
        this.data = null;
        this.next = null;
        this.prior = null;
    }

    public DuNode(Object data){
        this.data = data;
        this.prior  = null;
        this.next = null;
    }

    public DuNode(Object data,DuNode next,DuNode prior){
        this.data = data;
        this.next = next;
        this.prior = prior;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DuNode getNext() {
        return next;
    }

    public void setNext(DuNode next) {
        this.next = next;
    }

    public DuNode getPrior() {
        return prior;
    }

    public void setPrior(DuNode prior) {
        this.prior = prior;
    }
}
