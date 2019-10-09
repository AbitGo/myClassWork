package com.company.ch4.Matrix;

import com.company.ch4.TripleNode;

public class TriangularMatrix {
    /***
     * maxSize最大行数
     * tripleNodes矩阵
     * row行数
     * up如果为上三角即为true,反之为false
     * c为常数
     */
    final private int maxSize = 100;
    private TripleNode[] tripleNodes;
    private boolean up;
    private int row;
    private int c;

    public TriangularMatrix(int row,boolean up,int c) {

        //个数为
        int num = row * (row + 1) / 2+1;
        tripleNodes = new TripleNode[num];
        for (int i = 0; i < num; i++)
            tripleNodes[i] = new TripleNode();
        this.row = row;
        this.c = c;
        this.up = up;
    }

    //自动填充
    public void autoGenerate() {
        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (i >= j) {
                    //System.out.println("k:"+k);
                    this.tripleNodes[k++].setValue(i + j);
                }
            }
        }
        //设置常数c
        this.tripleNodes[tripleNodes.length-1].setValue(this.c);
    }

    public void disPlay() {
        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if(this.up==true){
                    if (i >= j) {
                        k = row * (row + 1) / 2;
                    } else {
                        k = (i + 1) * i / 2 + j;
                    }
                }else{
                    if (i >= j) {
                        k = (i + 1) * i / 2 + j;
                    } else {
                        k = row * (row + 1) / 2;
                    }
                }

                //System.out.println("i:"+i+" j: "+j+" k: "+k);
                System.out.print(this.tripleNodes[k].getValue() + "\t");
            }
            System.out.println();
        }
    }
}
