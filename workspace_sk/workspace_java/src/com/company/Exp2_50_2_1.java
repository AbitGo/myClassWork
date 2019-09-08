package com.company;

//50!
public class Exp2_50_2_1 {

    public static int doWhile(int param) {
        int i = 0;
        int sum = 0;
        do {
            sum += i;
        } while (++i <= 50);
        return sum;
    }

    public static int whileStatue(int param) {
        int i = 0;
        int sum = 0;
        while (i++ < 50) {
            sum += i;
        }
        return sum;
    }


    public static int forStatue(int param) {
        int sum = 0;
        for (int i = 0; i <=50; i++) {
            sum += i;
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(doWhile(50));
        System.out.println(whileStatue(50));
        System.out.println(forStatue(50));

    }

}
