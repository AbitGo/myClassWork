package com.company.ch3.AlgorithmDesgin;

import com.company.ch3.Stack.LinkStack.LinkStack;

public class p106_3_1 {
    public static void main(String[] agrs) throws Exception {
        int[] a={1,2,3,4,5,6,7,8,9};
        int count=0;
        LinkStack linkStack = new LinkStack();
        for(int i:a){
            linkStack.push(i);
        }

        while (!linkStack.isEmpty()){
            a[count++]=(int)linkStack.pop();
        }

        for(int i:a){
            System.out.print(" "+i);
        }
    }

}
