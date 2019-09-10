package com.company;

public class Exp1_5_1 {
    public static void main(String[] args) {
        int sum = 0;
        int sum_temp=0;

        for(int i=1;i<=100;i++){
            sum_temp+=i;
            sum+=sum_temp;
        }
        System.out.println("sum:"+sum);
    }
}
