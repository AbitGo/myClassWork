package com.company.ch4.Matrix;

public class TriangularMatrixTest {
    public static void main(String[] args) {
        System.out.println("上三角矩形");
        TriangularMatrix triangularMatrix1 = new TriangularMatrix(8,true,99);
        triangularMatrix1.autoGenerate();
        triangularMatrix1.disPlay();

        System.out.println("下三角矩形");
        TriangularMatrix triangularMatrix2 = new TriangularMatrix(8,false,88);
        triangularMatrix2.autoGenerate();
        triangularMatrix2.disPlay();
    }
}
