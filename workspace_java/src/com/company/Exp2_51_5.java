package com.company;

public class Exp2_51_5 {

    public static void main(String[] args) {
        int a[]={20,10,50,40,30,70,60,80,90,100};
        for(int i=0;i<a.length-1;i++)
        {
            int minIndex = i;
            int min = a[i];

            for(int j = i+1;j<a.length-1;j++){
                if(min>a[j]){
                    min = a[j];
                    minIndex = j;
                }
            }
            a[minIndex]=a[i];
            a[i]=min;
        }
        for(int i:a){
            System.out.print(i+" ");
        }
    }
}
