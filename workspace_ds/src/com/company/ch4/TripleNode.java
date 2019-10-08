package com.company.ch4;

public class TripleNode {
    private int row;
    private int column;
    private int value;

    public TripleNode(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }
    public TripleNode(){
        this(0,0,0);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setRowColumnValue(int row,int column,int value){
        this.row = row;
        this.column = column;
        this.value = value;
    }
}
