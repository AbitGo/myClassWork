package com.company.ch7;

public class ChangeSort {
    private static int[] a = {3, 2, 1, 4, 5, 6, 7, 9, 0, 1};
    private static int[] b = {3, 2, 1, 4, 5, 6, 7, 9, 0, 1};

    //冒泡排序
    public static void bubbleSort() {

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        Display.display(a);
    }

    //快排序开始
    public static void qSort(int low, int height) {
        if (low < height) {
            int index = partitionSort(low, height);
            partitionSort(low, index - 1);
            partitionSort(index + 1, height);
        }
    }

    public static int partitionSort(int i, int j) {
        //将第一个作为支点记录值
        int index_value = b[i];
        while (i < j) {
            while (b[j] >= index_value && i < j) {
                j--;
            }
            if (i < j) {
                b[i] = b[j];
                i++;
            }

            while (b[i] < index_value && i < j) {
                i++;
            }
            if (i < j) {
                b[j] = b[i];
                j--;
            }
        }

        b[i] = index_value;

        return i;
    }

    public static void quickSort() {
        qSort(0, a.length - 1);
        Display.display(b);
    }
    //快排序结束

    public static void main(String[] args) {
        //bubbleSort();
        quickSort();
    }
}
