package com.company.alo1;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class hw1 {
    public static void method_1(int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                System.out.print(" ");
            }
            System.out.println("*********");
        }
    }

    public static void method_2(int n){
        for(int i=0;i<n;i++){
            for(int k=0;k<i;k++){
                System.out.print(" ");
            }
            for(int j=0;j<n*2+1;j++){

                if(j==9-2*i){
                    System.out.print(" ");
                }else{
                    System.out.print("*");
                }

            }
            System.out.println();
        }
    }

    public static void method_3(int n){

        //序号
        int left = 0;
        int right = 0;
        int i =0;
        for(i=0;i<n;i++){
            left = n-i-1;
            right = 2*n-2-left;
            // System.out.print("i:"+left+" j: "+right);
            for(int j=0;j<2*n;j++){
                if(j==left || j ==right){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        i-=2;

        for(;i>=0;i--){
            left = n-i-1;
            right = 2*n-2-left;
            // System.out.print("i:"+left+" j: "+right);
            for(int j=0;j<2*n;j++){
                if(j==left || j ==right){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void method_4(int n){
        for(int i=1;i<=n;i++){
            for(int j=i;j>0;j--){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    public static void method_6(int[] array,int index,int len){
        if(index<len){
            int a[]=new int[index+1];
            a[0]=1;
            a[a.length-1]=1;
            for(int i=1;i<a.length-1;i++){
                a[i]=array[i-1]+array[i];
            }
            for(int i=0;i<8-2*index;i++){
                System.out.print(" ");
            }
            for(int i:a){
                System.out.print(i+"\t");
            }
            System.out.println();
            method_6(a,index+1,len);
        }

    }

    public static void method_5(int n){
        int a[]={1};
        for(int i=0;i<8;i++){
            System.out.print(" ");
        }
        System.out.println(1);
        method_6(a,1,n);
    }


    public static void main(String[] args) {
        //method_1(4);
        //method_2(5);
        //method_3(5);
        //method_4(7);
        method_5(5);
    }
}
