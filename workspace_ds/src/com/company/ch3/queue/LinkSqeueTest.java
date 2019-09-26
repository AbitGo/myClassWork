package com.company.ch3.queue;

public class LinkSqeueTest {
    public static void main(String[] args){
        LinkSqeue sqStack = new LinkSqeue();
        System.out.println("----------插入操作：开始----------");
        sqStack.offer(1);
        sqStack.offer(2);
        sqStack.offer(3);
        System.out.println("----------插入操作：结束----------");
        sqStack.display();

        System.out.println("----------查看队列操作：开始----------");
        System.out.println(sqStack.peek());
        System.out.println("弹出队头元素："+sqStack.poll());
        System.out.println(sqStack.peek());
        System.out.println("弹出队头元素："+sqStack.poll());
        System.out.println("----------查看队列操作：结束----------");

        System.out.println("----------查看队列长度操作：开始----------");
        System.out.println("长度为："+sqStack.length());
        System.out.println("----------查看队列长度操作：结束----------");

        System.out.println("----------清除队列操作：开始----------");
        sqStack.clear();
        System.out.println("----------清除队列操作：结束----------");
    }
}