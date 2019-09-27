package com.company.ch3;

import com.company.ch3.queue.LinkSqeue;

public class Exp35 {
    final static int maxSize = 10;

    public static void main(String[] args) {

        int[] a = new int[maxSize];
        a[0] = 1;
        LinkSqeue linkSqeue = new LinkSqeue();
        for (int i = 2; i < maxSize; i++) {
            linkSqeue.offer(i);
        }
        System.out.println("数据为");
        linkSqeue.display();
        for(int i =0;i<maxSize;i++){
            int param1 = (int)linkSqeue.peek();
            while (true){
                if(isPrime(a[i],param1)){
                    System.out.println("a[i]:"+a[i]+" param1: "+param1);
                    //如果可以组成素数的话，直接进行poll以及如数组
                    a[i+1]=param1;
                    //出队
                    linkSqeue.poll();
                    break;
                }else
                {
                    linkSqeue.poll();
                    linkSqeue.offer(param1);
                }
            }
        }
        for(int i:a){
            System.out.println(" "+i);
        }


    }

    public static boolean isPrime(int param1, int param2) {
        int sum = param1 + param2;

        for(int i = 2;i<sum;i++){
            if(sum%i==0){
                return false;
            }
        }
        return true;
    }
}
