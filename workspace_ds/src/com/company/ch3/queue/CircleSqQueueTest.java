package com.company.ch3.queue;

import com.company.ch3.Stack.SqStack.SqStack;

public class CircleSqQueueTest {
    public static void main(String[] args){
        CircleSqQueue sqStack = new CircleSqQueue(20);
        System.out.println("----------插入操作：开始----------");
        sqStack.offer(1);
        sqStack.offer(2);
        sqStack.offer(3);
        System.out.println("----------插入操作：结束----------");

        System.out.println("----------查看栈顶操作：开始----------");
        System.out.println(sqStack.peek());
        System.out.println("弹出栈顶元素："+sqStack.poll());
        System.out.println(sqStack.peek());
        System.out.println("弹出栈顶元素："+sqStack.poll());
        System.out.println("----------查看栈顶操作：结束----------");

        System.out.println("----------查看栈长度操作：开始----------");
        System.out.println("长度为："+sqStack.length());
        System.out.println("----------查看栈长度操作：结束----------");

        System.out.println("----------清除栈操作：开始----------");
        sqStack.clear();
        System.out.println("----------清除栈操作：结束----------");
    }
}
