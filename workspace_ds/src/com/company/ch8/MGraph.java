package com.company.ch8;

public class MGraph implements IGraph {
    public final static int INFINITY = Integer.MAX_VALUE;
    private GraphKind graphKind;//图的种类标识
    private int vexNum,arcNum;//图当前定点数和边数
    private Object[] vexs;//顶点
    private int[][] arcs;//领接矩阵

    public MGraph(GraphKind graphKind, int vexNum, int arcNum, Object[] vexs, int[][] arcs) {
        this.graphKind = graphKind;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexs = vexs;
        this.arcs = arcs;
    }

    @Override
    public void createGraph() {

    }

    @Override
    public int getVexNum() {
        return 0;
    }

    @Override
    public int getArcNum() {
        return 0;
    }

    @Override
    public Object getVex(int x) {
        return null;
    }

    @Override
    public int locateVex(Object x) {
        return 0;
    }

    @Override
    public int firstAdjVex(int x) {
        return 0;
    }

    @Override
    public int nextAdjVex(int x, int w) {
        return 0;
    }
}
