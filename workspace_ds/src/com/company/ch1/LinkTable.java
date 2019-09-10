package com.company.ch1;

public class LinkTable implements SequenceTableInterface {

    //头节点
    public Node head;

    public LinkTable() {
        head = new Node();
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
            if (i > index || p == null) {
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
        while(p!=null && j<index-1){
            p = p.next;
            j++;
        }
        if(j>index-1 || p==null){
            System.out.println("插入位置不合法");
            return false;
        }
        else{
            Node s = new Node(x);
            s.next = p.next;
            p.next = s;
        }
        return false;
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
}
