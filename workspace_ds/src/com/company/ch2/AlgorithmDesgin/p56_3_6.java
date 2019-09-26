package com.company.ch2.AlgorithmDesgin;

import com.company.ch2.LinkTable.LinkTable;

public class p56_3_6 {
    public static void main(String[] args) throws Exception {
        LinkTable linkTable = new LinkTable();
        int[] a = {1, 2, 3, 4, 5, 5, 6, 7, 8, 9};
        for (int i = 0; i < a.length; i++) {
            linkTable.insert(i, a[i]);
        }
        linkTable.display();
        System.out.println("counter："+linkTable.p56_3_6_method(5));
        linkTable.display();
    }
}
