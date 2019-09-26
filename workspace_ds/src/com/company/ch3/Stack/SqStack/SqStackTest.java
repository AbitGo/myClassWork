package com.company.ch3.Stack.SqStack;

public class SqStackTest {
    public static void main(String[] args) throws Exception {
        SqStack sqStack = new SqStack(20);
        System.out.println("----------插入操作：开始----------");
        sqStack.push(1);
        sqStack.push(2);
        sqStack.push(3);
        System.out.println("----------插入操作：结束----------");

        System.out.println("----------查看栈顶操作：开始----------");
        System.out.println(sqStack.peek());
        System.out.println("弹出栈顶元素："+sqStack.pop());
        System.out.println(sqStack.peek());
        System.out.println("弹出栈顶元素："+sqStack.pop());
        System.out.println("----------查看栈顶操作：结束----------");

        System.out.println("----------查看栈长度操作：开始----------");
        System.out.println("长度为："+sqStack.length());
        System.out.println("----------查看栈长度操作：结束----------");

        System.out.println("----------清除栈操作：开始----------");
        sqStack.clear();
        System.out.println("----------清除栈操作：结束----------");
    }
}
