package com.company.ch6;

public class MGraphTest {
    public static void main(String[] args) throws Exception {
        //无向图
        Object[] param1_1_1= {"UDN",5,5,"ABCDE"};
        String[] param1_1_2 = {"AB1","BC2","AD3","AE4","CE5"};
        MGraph mGraph1 = new MGraph();
        mGraph1.createGraph(param1_1_1,param1_1_2);
        GraphFS.BFSTraverse(mGraph1);
        GraphFS.DFSTraverse(mGraph1);

        //有向图
        Object[] param1_2_1= {"UDN",5,5,"ABCDE"};
        String[] param1_2_2 = {"AB1","BC2","AD3","AE4","CE5"};
        MGraph mGraph2 = new MGraph();
        mGraph2.createGraph(param1_2_1,param1_2_2);
        GraphFS.BFSTraverse(mGraph2);
        GraphFS.DFSTraverse(mGraph2);
    }
}
