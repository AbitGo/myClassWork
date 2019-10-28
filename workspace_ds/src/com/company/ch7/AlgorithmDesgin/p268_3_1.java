package com.company.ch7.AlgorithmDesgin;

import com.company.ch2.LinkTable.LinkTable;

import java.util.ArrayList;

public class p268_3_1 {
    public static ArrayList sort(ArrayList list) {
        int len = list.size();
        for (int i = 1, j = 0; i < len; i++) {
            int temp_i = (int)list.get(i);
            for(j=0;j<i;j++){
                int temp_j = (int)list.get(j);
                if(temp_i<temp_j){
                    System.out.println("temp_i:"+temp_i+" temp_j: "+temp_j+" index: "+i+" j: "+j);
                    list.remove(i);

                    list.add(j,temp_i);
                    display(list);
                    break;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 1, 4, 5, 6, 7, 9, 0, 1};
        ArrayList arrayList = new ArrayList();
        arrayList.add(3);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(9);
        arrayList.add(0);
        arrayList.add(1);
        arrayList=sort(arrayList);
        display(arrayList);
    }
    public static void display(ArrayList list){
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        System.out.println();
    }
}
