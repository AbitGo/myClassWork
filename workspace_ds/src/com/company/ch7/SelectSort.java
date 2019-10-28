package com.company.ch7;

public class SelectSort {
    public static void main(String[] args) {
        int[] a = {3, 2, 1, 4, 5, 6, 7, 9, 0, 1};

        for (int i = 0; i < a.length -1; i++) {
            int min = i;
            int j;
            for (j = i + 1; j < a.length ; j++) {
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
            Display.display(a);
        }
        Display.display(a);
    }
}
