package com.company.ch2.DuLinkTable;

import com.company.ch2.interfaceFile.SequenceTableInterface;

public class DuLinkList implements SequenceTableInterface {
    DuNode head;

    public DuLinkList() {
        head = new DuNode();
        head.prior = head;
        head.next = head;
    }

    @Override
    public void clear() {
        this.head.next = head;
        this.head.prior = head;
    }

    @Override
    public boolean isEmpty() {
        if (head.next == head) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getLenght() {
        DuNode duNode = head.next;
        int len = 0;
        while (duNode != null) {
            len++;
            duNode = duNode.next;
        }
        return len;
    }

    @Override
    public Object get(int index) throws Exception {
        DuNode duNode = head;
        int i = -1;
        //达到需要插入的前缀
        while (!duNode.next.equals(head) && i < index-1) {
            duNode = duNode.next;
            i++;
        }

        if (i < index-1) {
            System.out.println("查找位置不合法");
            return null;
        } else {
            return duNode.data;
        }
    }

    @Override
    //这个函数有问题，就是不能在空表之前插入
    public boolean insert(int index, Object x) throws Exception {
        DuNode duNode = head;
        int i = -1;
        //当这个是只有头节点的时候只需要直接插入即可
        //为了挽救上面提到的问题
        if (duNode.next.equals(duNode)) {
            DuNode temp = new DuNode(x);
            temp.next = duNode.next;
            temp.prior = duNode;
            duNode.next = temp;
            duNode.prior = duNode;
            return true;
        }
        //达到需要插入的前缀
        while (!duNode.next.equals(head) && i < index-1) {
            duNode = duNode.next;
            i++;
        }

        if (i < index-1) {
            System.out.println("插入位置不合法");
            return false;
        } else {
            DuNode temp = new DuNode(x);

            //代码分为四部，分别是
            //duNode=temp=p三个节点之间的前驱动后驱代换
            temp.next = duNode.next;
            temp.prior = duNode;
            duNode.next.prior = temp;

            duNode.next = temp;
            return true;
        }
    }


    @Override
    public boolean remove(int index) {
        DuNode duNode = head;
        int i = -1;
        //达到需要插入的前缀
        while (!duNode.next.equals(head) && i<index-1) {
            duNode = duNode.next;
            i++;
        }
        if (i < index-1) {
            System.out.println("删除位置不合法");
            return false;
        } else {
            duNode.next.prior = duNode;
            duNode.next = duNode.next.next;
            return true;
        }
    }

    @Override
    public int indexOf(Object x) {
        DuNode duNode = head;
        int i = 0;
        //达到需要插入的前缀
        while (!duNode.next.equals(head)) {
            duNode = duNode.next;
            if(duNode.data.equals(x))
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public void display() {
        DuNode duNode = head.next;
        while (!duNode.equals(head)) {

            System.out.print(duNode.data + " ");
            duNode = duNode.next;
        }
        System.out.println();
    }
}
