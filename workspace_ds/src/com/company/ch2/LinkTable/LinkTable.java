package com.company.ch2.LinkTable;

import com.company.ch2.interfaceFile.SequenceTableInterface;

public class LinkTable implements SequenceTableInterface {

    //头节点
    public Node head;

    //使用无参构造方法创建链表
    public LinkTable() {
        head = new Node();
    }

    //构建一个长度为n的链表，
    //并且使用order判别是否为头插发还是尾插法
    public LinkTable(int len, boolean order) {
        this.head = new Node();

        if (order) {
            create1(len);
        } else {
            create2(len);
        }
    }

    @Override
    public void clear() {
        this.head.next = null;
    }

    @Override
    public boolean isEmpty() {
        if (this.head.next == null)
            return false;
        else
            return true;
    }

    @Override
    public int getLenght() {
        Node p = head.next;
        int lenght = 0;
        if (p == null) {
            return 0;
        } else {
            while (p != null) {
                lenght++;
                p = p.next;
            }
        }
        return lenght;
    }

    @Override
    public Object get(int index) throws Exception {
        //通过index获取节点
        Node p = head.next;
        int i = 0;
        if (p == null) {
            System.out.println("该链表是空的");
            return null;
        } else {
            while (p != null && i++ < index) {
                p = p.next;
            }
            //超过链表的长度
            if (i < index || p == null) {
                return null;
            }

            return p.data;
        }

    }

    //插入操作
    @Override
    public boolean insert(int index, Object x) throws Exception {
        Node p = head;
        int j = -1;
        while (p != null && j < index - 1) {
            p = p.next;
            j++;
        }
        if (j > index - 1 || p == null) {
            System.out.println("插入位置不合法");
            return false;
        } else {
            Node s = new Node(x);
            s.next = p.next;
            p.next = s;
        }
        return false;
    }

    public void insert_increase(Object x) throws Exception {
        Node p = head.next;
        if (p == null) {
            this.insert(0, x);
            return;
        } else {
            while (p != null) {
                if ((double) p.data > (double) x) {
                    Node s = new Node(x);

                }
            }
        }
    }

    @Override
    public boolean remove(int index) {
        //这里为什么不是head.next呢？
        //我发现可能删除第一个节点
        Node p = head;
        //所以这里也是-1
        int i = -1;
        if (p.next == null) {
            System.out.println("该链表是空的");
            return false;
        } else {
            while (p != null && i < index - 1) {
                i++;
                p = p.next;
            }
            //超过链表的长度/下一个节点是空
            if (i > index - 1 || p.next == null) {
                return false;
            }
            //进行单链表删除
            p.next = p.next.next;
            return true;
        }
    }

    @Override
    public int indexOf(Object x) {
        Node p = head.next;
        int j = 0;
        while (p != null && !p.data.equals(x)) {
            p = p.next;
            j++;
        }
        if (p != null)
            return j;
        else
            return -1;
    }

    @Override
    public void display() {
        Node p = head.next;
        while (p != null) {
            System.out.print(" " + p.data);
            p = p.next;
        }
        System.out.println();
    }

    //p56_3_3需要使用的函数
    public void p56_3_3_method(int x) throws Exception {
        int len = this.getLenght();
        int i = 0;
        for (; i < len; i++) {
            int param = this.indexOf(i);
            if (x < param) {
                break;
            }
        }
        System.out.println(i);
        this.insert(i, x);
    }

    //p56_3_3需要使用的函数
    public void p56_3_4_method() throws Exception {
        int len = this.getLenght();
        for(int i=0;i<len;i++){
            Object param = this.get(i);
            this.remove(i);
            this.insert(0,param);
        }

    }

    //使用尾插法建立单链表
    //我发现书上的是(n+1)*n/2
    public void create1(int len) {
        int i = 0;
        //这个节点类似于一个中转站
        Node p = this.head;

        //建立一个长度大于等于0的链表
        while (i < len && i >= 0) {
            Node temp = new Node();
            temp.data = i;
            p.next = temp;
            p = p.next;
            i++;
        }
    }

    //使用头插发建立单链表
    public void create2(int len) {
        int i = 0;
        Node p = this.head;
        while (i < len && i >= 0) {
            Node temp = new Node();
            temp.data = i;
            temp.next = p.next;
            p.next = temp;
            i++;
        }
    }
}
