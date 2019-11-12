package com.company.ch8;

public interface IGraph {
    void createGraph();
    int getVexNum();
    int getArcNum();
    Object getVex(int x);
    int locateVex(Object x);
    int firstAdjVex(int x);
    int nextAdjVex(int x,int w);
}
