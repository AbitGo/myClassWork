package com.company.ch3.AlgorithmDesgin;

import com.company.ch3.queue.LinkSqeue;

public class p106_3_5Test {
    public static void main(String[] args){
        p106_3_5 sqStack = new p106_3_5();
        System.out.println("----------插入操作：开始----------");
        sqStack.offer(1);
        sqStack.offer(2);
        sqStack.offer(3);
        System.out.println("----------插入操作：结束----------");
        sqStack.display();

        System.out.println("----------查看队列操作：开始----------");
        System.out.println(sqStack.peek());
        System.out.println("弹出队头元素："+sqStack.poll());

        sqStack.display();

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