package com.company.ch3.Stack;

public class SqStack implements IStack {
    private Object[] stackElem;

    //在非空栈中，top始终指向栈顶元素的下一个存储位置(我的理解就是座标
    //当栈空的时候，top的值为空
    private int top;
    private int maxSize;
    public SqStack(int Size){
        //创建新的栈，并且为空
        top = 0;
        maxSize = Size;
        stackElem = new Object[maxSize];
    }
    @Override
    public void clear() {
        //直接将
        this.top = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.top==0;
    }

    @Override

    public int length() {
        return this.top;
    }

    @Override
    public Object peek() {
        //获取栈顶元素
        if(!isEmpty()){
            return stackElem[top-1];
        }else{
            return null;
        }
    }

    @Override
    public boolean push(Object x) throws Exception {
        if(top>=this.maxSize){
            return false;
        }else
        {
            //将元素压如栈中
            stackElem[++top]=x;
            return true;
        }
    }

    @Override
    public Object pop() {
        //将栈顶的元素弹出
        if(!isEmpty()){
            return stackElem[top--];
        }else
            return null;
    }
}
