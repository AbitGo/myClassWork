package com.company.ch3.AlgorithmDesgin;

import com.company.ch3.Stack.LinkStack.LinkStack;

public class p106_3_2 {
    public static void main(String[] args) throws Exception {
        StringBuffer str1 = new StringBuffer("abcdcba");
        StringBuffer str2 = new StringBuffer();

        LinkStack linkStack = new LinkStack();
        for(int i=0;i<str1.length();i++){
            linkStack.push(str1.charAt(i));
        }

        while (!linkStack.isEmpty()){
            str2.append(linkStack.pop());
        }

        System.out.println("str1: "+str1);
        System.out.println("str2: "+str2);
        if(str1.toString().equals(str2.toString())){
            System.out.println("是回文");
        }else {
            System.out.println("不是回文");
        }
    }
}
