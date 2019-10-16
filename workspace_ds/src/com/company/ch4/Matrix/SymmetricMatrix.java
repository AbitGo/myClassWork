package com.company.ch4.Matrix;


import com.company.ch4.TripleNode;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

//特殊矩阵的压缩存储-对称矩阵的操作
public class SymmetricMatrix {
    /***
     * maxSize最大行数
     * tripleNodes矩阵
     * row行数
     */
    final private int maxSize = 100;
    private TripleNode[] tripleNodes;
    private int row;

    public SymmetricMatrix(int row) {
        int num = row * (row + 1) / 2;
        tripleNodes = new TripleNode[num];
        for (int i = 0; i < num; i++)
            tripleNodes[i] = new TripleNode();
        this.row = row;
    }

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
    }

    public void disPlay() {
        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (i >= j) {
                    k = (i + 1) * i / 2 + j;
                } else {
                    k = (j + 1) * j / 2 + i;
                }
                //System.out.println("i:"+i+" j: "+j+" k: "+k);
                System.out.print(this.tripleNodes[k].getValue() + "\t");
            }
            System.out.println();
        }
    }
}
