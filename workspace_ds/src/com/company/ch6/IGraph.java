package com.company.ch6;

public interface IGraph {
    void createGraph(Object[] param1,String[] param2);
    int getVexNum();
    int getArcNum();
    Object getVex(int x) throws Exception;
    int locateVex(Object x);
    int firstAdjVex(int x) throws Exception;
    int nextAdjVex(int x,int w) throws Exception;
}
