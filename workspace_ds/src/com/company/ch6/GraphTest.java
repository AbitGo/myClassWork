package com.company.ch6;

public class GraphTest {
    public static void main(String[] args) throws Exception {
        Object[] param1 = {"UDG",5,5,"ABCDE"};
        String[] param2 = {"AB","BC","AD","AE","CE"};
        MGraph mGraph = new MGraph();
        mGraph.createGraph(param1,param2);
        GraphFS.BFSTraverse(mGraph);
    }
}
