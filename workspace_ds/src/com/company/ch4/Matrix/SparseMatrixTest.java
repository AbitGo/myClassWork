package com.company.ch4.Matrix;

public class SparseMatrixTest {
    public static void main(String[] args) {
        int a[][]={{0,0,8,0,0},{0,0,0,0,0},{5,0,0,0,16},{0,0,18,0,0},{0,0,0,9,0}};
        SparseMatrix sparseMatrix = new SparseMatrix(a);
        sparseMatrix.display();
        System.out.println();
        SparseMatrix sm1 = sparseMatrix.transpose();
        sm1.display();
        System.out.println();

    }
}
