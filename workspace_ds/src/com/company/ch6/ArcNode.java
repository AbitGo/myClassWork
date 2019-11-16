package com.company.ch6;

public class ArcNode {
    //该弧所指向的顶点位置
    public int adjVex;
    //边或弧的权值
    public int value;
    //指向下一个弧
    public ArcNode nextArc;

    public ArcNode() {
        this(-1, 0, null);
    }

    public ArcNode(int adjVex, int value, ArcNode nextArc) {
        this.adjVex = adjVex;
        this.value = value;
        this.nextArc = nextArc;
    }
    public ArcNode(int adjVex, int value) {
        this(adjVex, value, null);
    }
}
