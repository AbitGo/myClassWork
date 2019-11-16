package com.company.ch6;

public class MGraphTest {
    public static void main(String[] args) {
        System.out.println("开始创建无向图");
        Object[] param1_1= {"UDN",5,5,"ABCDE"};
        String[] param1_2 = {"AB","BC","AD","AE","CE"};
        MGraph mGraph1 = new MGraph().createGraph(param1_1,param1_2);
    }
}
