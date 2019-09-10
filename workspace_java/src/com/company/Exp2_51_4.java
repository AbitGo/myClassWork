package com.company;

public class Exp2_51_4 {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 500; i++) {
            if (i % 7 == 0) {
                sum += i;
            }

        }
        System.out.println("Sum:" + sum);
    }


}
