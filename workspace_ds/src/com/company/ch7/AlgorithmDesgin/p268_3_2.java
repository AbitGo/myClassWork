package com.company.ch7.AlgorithmDesgin;

import java.util.ArrayList;

public class p268_3_2 {
    public static ArrayList sort(ArrayList list){
        int len = list.size();
        for(int i =0;i<len;i++){
            int min = i;
            int min_value=(int)list.get(min);
            int j;
            for(j=i+1;j<len;j++){
                if((int)list.get(min)>(int)list.get(j)){
                    min = j;
                    min_value=(int)list.get(min);
                }
            }
            list.remove(min);
            list.add(i,min_value);
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
