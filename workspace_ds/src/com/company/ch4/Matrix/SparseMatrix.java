package com.company.ch4.Matrix;

import com.company.ch4.TripleNode;

public class SparseMatrix {
    private TripleNode data[];
    private int rows;
    private int cols;
    private int nums;

    public SparseMatrix(int maxSize) {
        data = new TripleNode[maxSize];
        for (int i = 0; i < data.length; i++) {
            data[i] = new TripleNode();
        }
        this.rows = 0;
        this.cols = 0;
        this.nums = 0;
    }

    public SparseMatrix(int mat[][]){
        int count = 0;
        this.rows = mat.length;//行数
        this.cols = mat[0].length;//列数

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(mat[i][j]!=0){
                    count++;
                }
            }
        }
        this.nums = count;
        int k=0;
        data = new TripleNode[this.nums];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(mat[i][j]!=0){
                    data[k++] = new TripleNode(i,j,mat[i][j]);
                }
            }
        }

    }
    public void display(){
        for(int i=0;i<this.nums;i++){
            System.out.println("行数："+this.data[i].getRow()+" 列数："+this.data[i].getColumn()+" 数值："+this.data[i].getValue());
        }
    }
    public SparseMatrix transpose(){
        SparseMatrix tm = new SparseMatrix(nums);
        int count = 0;
        tm.cols = this.rows;
        tm.rows = this.cols;
        tm.nums = this.nums;
        for(int i=0;i<rows;i++){
            for(int j=0;j<nums;j++){
                if(data[j].getColumn()==i){
                    tm.data[count].setColumn(this.data[j].getRow());
                    tm.data[count].setRow(this.data[j].getColumn());
                    tm.data[count].setValue(this.data[j].getValue());
                    count++;
                }
            }
        }
        return tm;
    }

    public SparseMatrix fastTranspose(){
        SparseMatrix tm = new SparseMatrix(nums);
        int count = 0;
        tm.cols = this.rows;
        tm.rows = this.cols;
        tm.nums = this.nums;
        tm.data = new TripleNode[nums];

        int[] button = new int[nums];//存放每列非零数的数组

        for(int i=0;i<nums;i++){
            button[this.data[i].getColumn()]++;
        }
        for(int i:button){

        }

        return tm;
    }
}
