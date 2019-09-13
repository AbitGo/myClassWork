package com.company.ch1.DuLinkTable;

public class DuLinkListTest {
    public static void main(String[] args) throws Exception {
        DuLinkList duLinkList = new DuLinkList();
        System.out.println("----------判断空操作：开始----------");
        if(duLinkList.isEmpty()==true){
            System.out.println("the link is empty");
        }else{
            System.out.println("the link is not empty");
        }
        System.out.println("----------判断空操作：结束----------");


        System.out.println("----------插入操作：开始----------");
        duLinkList.insert(0,0);
        duLinkList.display();
        duLinkList.insert(1,1);
        duLinkList.insert(2,2);
        //插入错误
        duLinkList.insert(5,4);
        duLinkList.display();
        System.out.println("----------插入操作：结束----------");


        System.out.println("----------删除操作：开始----------");

        duLinkList.remove(1);
        duLinkList.display();
        duLinkList.remove(100);
        duLinkList.display();
        System.out.println("----------删除操作：结束----------");

        System.out.println("----------查找操作：开始----------");

        Object result = duLinkList.get(1);
        if(result!=null){
            System.out.println("data:"+result);
        }

        System.out.println("----------查找操作：结束----------");

        System.out.println("----------索引操作：开始----------");
        int xxx= duLinkList.indexOf(2);
        if(xxx!=-1){
            System.out.println("indexOf:"+xxx);
        }

        System.out.println("----------索引操作：结束----------");

        System.out.println("----------清空操作：开始----------");
        duLinkList.clear();
        duLinkList.display();
        System.out.println("----------清空操作：结束----------");

    }
}
