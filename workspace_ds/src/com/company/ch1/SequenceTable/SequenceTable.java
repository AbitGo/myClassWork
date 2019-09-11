package com.company.ch1.SequenceTable;

import com.company.ch1.interfaceFile.SequenceTableInterface;

public class SequenceTable implements SequenceTableInterface {
    final int maxLen = 100;
    private Object[] listElem;
    private int curlen;

    public SequenceTable() {
        curlen = 0;
        listElem = new Object[maxLen];
    }

    @Override
    public void clear() {
        this.curlen=0;
    }

    @Override
    public boolean isEmpty() {
        if (this.curlen == 0)
            return true;
        return false;
    }

    @Override
    public int getLenght() {
        return this.curlen;
    }

    @Override
    public Object get(int i) throws Exception {
        if (i < 0 || i > curlen - 1) {
            throw new Exception("第" + i + "元素不存在");
        }
        return listElem[i];
    }

    @Override
    public boolean insert(int index, Object x) throws Exception {
        if (index < 0 || index > curlen) {
            System.out.println("插入位置不合法");
            return false;
        }
        if (curlen >= maxLen) {
            System.out.println("数组已满");
            return false;
        }
        for (int j = curlen; j>index; j--) {
            listElem[j] = listElem[j-1];
        }
        listElem[index] = x;
        curlen++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        if(index<0 || index>curlen){
            System.out.println("删除位置不合法");
            return false;
        }
        if(curlen<=0){
            System.out.println("此表已空");
            return false;
        }
        for(int i=index;i<curlen-1;i++){
            listElem[i]=listElem[i+1];
        }
        curlen--;
        return false;
    }

    @Override
    public int indexOf(Object x) {
        int j = 0;
        while (j < curlen && !listElem[j].equals(x)) {
            j++;
        }
        if(j==curlen)
        {
            System.out.println("没有这个元素");
            return -1;
        }

        return j;
    }

    @Override
    public void display() {
        for (int i = 0; i < curlen; i++) {

            System.out.print(" " + listElem[i]);

        }
        System.out.println();
    }
}
