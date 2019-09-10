package com.company.ch1;

public class LinkTableTest {
    public static void main(String[] args) throws Exception {
        LinkTable linkTable = new LinkTable();
        System.out.println("----------插入操作：开始----------");
        linkTable.insert(0,0);
        linkTable.insert(1,1);
        linkTable.insert(2,2);
        linkTable.insert(3,3);
        linkTable.insert(4,4);
        linkTable.insert(2,5);
        linkTable.insert(10,5);
        linkTable.display();
        System.out.println("----------插入操作：结束----------");

        System.out.println("----------查询操作：开始----------");
        int index = linkTable.indexOf(1);;

        if(!(index==-1)){
            System.out.println("位置为："+index);
        }
        System.out.println("----------查询操作：结束----------");



        System.out.println("----------删除操作：开始----------");
        linkTable.remove(0);
        linkTable.display();
        System.out.println("----------删除操作：结束----------");

        System.out.println("----------清零操作：开始----------");
        linkTable.clear();
        linkTable.display();
        System.out.println("----------清零操作：结束----------");
    }
}
