package com.company.ch2.SequenceTable;

public class SequenceTableTest {
    public static void main(String[] args) throws Exception {
        SequenceTable sequenceTable = new SequenceTable();
        System.out.println("----------插入操作：开始----------");
        sequenceTable.insert(0,0);
        sequenceTable.insert(1,1);
        sequenceTable.insert(2,2);
        sequenceTable.insert(3,3);
        sequenceTable.insert(4,4);
        sequenceTable.insert(2,5);
        sequenceTable.insert(10,5);
        sequenceTable.display();
        System.out.println("----------插入操作：结束----------");

        System.out.println("----------查询操作：开始----------");
        int index = sequenceTable.indexOf(1);;

        if(!(index==-1)){
            System.out.println("位置为："+index);
        }
        System.out.println("----------查询操作：结束----------");

        System.out.println("----------删除操作：开始----------");
        sequenceTable.remove(0);
        sequenceTable.display();
        System.out.println("----------删除操作：结束----------");

        System.out.println("----------清零操作：开始----------");
        sequenceTable.clear();
        sequenceTable.display();
        System.out.println("----------清零操作：结束----------");


    }
}
