package com.company;

public class ramdomaa {
    final static int length = 100;

    public static void main(String[] args) {

        int a[] = new int[length];

        for(int i =0;i<length;i++){
            a[i]=(int) (Math.random() * 100);
        }
        int max = a[0];
        int maxIndex = 0;
        for (int i = 1; i < 100; i++) {
            if (a[i] > max) {
                max = a[i];
                maxIndex = i;
            }
        }
        System.out.println("max: " + max + " maxIndex " + maxIndex);
    }
}
