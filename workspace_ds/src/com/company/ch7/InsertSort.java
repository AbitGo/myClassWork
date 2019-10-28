package com.company.ch7;

public class InsertSort {
    public static void insertSort() {
        int[] a = {3, 2, 1, 4, 5, 6, 7, 9, 0, 1};
        for (int i = 1, j = 0; i < a.length; i++) {
            //这是待插入值
            int temp = a[i];
            //递减的效果比递增来的好
            for (j = i - 1; j >= 0; j--) {
                //找到需要向后移动的位置
                a[j + 1] = a[j];
                if (temp > a[j]) {
                    break;
                }
            }
            a[j + 1] = temp;
        }
        Display.display(a);
    }
}
