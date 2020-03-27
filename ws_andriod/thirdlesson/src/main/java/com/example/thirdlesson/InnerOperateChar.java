package com.example.thirdlesson;

public class InnerOperateChar {
    private String number;
    private char operater;
    private boolean isNum;

    public InnerOperateChar(String number) {
        this.number = number;
        this.operater = '#';
        this.isNum = true;
    }

    public InnerOperateChar(char operater) {
        this.number ="#";
        this.operater = operater;
        this.isNum = false;
    }

    public boolean isNum() {
        return isNum;
    }

    public void setNum(boolean num) {
        isNum = num;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public char getOperater() {
        return operater;
    }

    public void setOperater(char operater) {
        this.operater = operater;
    }
}
