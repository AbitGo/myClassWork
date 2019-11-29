package com.company.ch5;

public class HuffmanTree {
    public int[][] huffmanfiCoding(int[] w){
        //字符个数
        int n = w.length;
        //结点数
        int m = 2*n -1;
        HuffmanNode[] HN = new HuffmanNode[m];
        int i;
        for(i = 0;i<n;i++){
            HN[i]=new HuffmanNode(w[i]);
        }
        for(i = n;i<m;i++){
            HuffmanNode min1 = selctMin(HN,i-1);
        }

    }
}
