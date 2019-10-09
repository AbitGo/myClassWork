package com.company.ch4.Matrix;

import com.company.ch4.TripleNode;

//特殊矩阵的压缩存储-对称矩阵的操作
public class DiagonalMatrix {
    /***
     * maxSize最大行数
     * tripleNodes矩阵
     * row行数
     */
    final private int maxSize = 100;
    private TripleNode[] tripleNodes;
    private int row;

    public DiagonalMatrix(int row) {
        //数组的个数为3n-2
        //最后一个存储0
        int num = row * 3 - 2;
        tripleNodes = new TripleNode[num];
        for (int i = 0; i < num; i++)
            tripleNodes[i] = new TripleNode();
        this.row = row;
    }

    //自动填充
    public void autoGenerate() {
        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (i == 0) {
                    //第一行两个
                    if (j == 0) {
                        this.tripleNodes[k++].setValue(i);
                    } else if (j == 1) {
                        this.tripleNodes[k++].setValue(i + 1);
                    }
                } else if (i == row - 1) {
                    //最后一行两个
                    if (j == row - 2) {
                        this.tripleNodes[k++].setValue(i);
                    } else if (j == row - 1) {
                        this.tripleNodes[k++].setValue(i + 1);
                    }
                } else {
                    if (i == j - 1 || i == j + 1 || j == i)
                        this.tripleNodes[k++].setValue(j);
                }
            }
        }
//        //debug过程
//        for(int x=0;x<row*3-2;x++){
//            System.out.println("x:"+this.tripleNodes[x].getValue());
//        }
    }

    public void disPlay() {
        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (i == 0) {
                    //第一行
                    if (j <=1) {//j==1 || j==0
                        System.out.print(this.tripleNodes[j].getValue() + "\t");
                    } else {
                        System.out.print(0 + "\t");
                    }
                } else if (i == row - 1) {
                    //第二行
                    if (j == row - 2 ) {
                        //倒数第二个
                        System.out.print(this.tripleNodes[3* this.row-4].getValue() + "\t");
                    } else if(j == row - 1){
                        //倒数第一个
                        System.out.print(this.tripleNodes[3*this.row-3].getValue() + "\t");
                    }
                    else {
                        System.out.print(0 + "\t");
                    }
                } else {
                    if (i == j) {
                        k = 3 * i;
                        System.out.print(this.tripleNodes[k].getValue() + "\t");
                    } else if (i == j - 1) {
                        k = 3 * i + 1;
                        System.out.print(this.tripleNodes[k].getValue() + "\t");
                    } else if (i == j + 1) {
                        k = 3 * i - 1;
                        System.out.print(this.tripleNodes[k].getValue() + "\t");
                    }else{
                        System.out.print(0 + "\t");
                    }

                }
            }
            System.out.println();
        }
    }
}
