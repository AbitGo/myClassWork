package com.company.ch6;

import java.util.Scanner;

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
        //创建图
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入图的类型:");
        GraphKind graphKind = GraphKind.valueOf(scanner.next());
        switch (graphKind){
            case DG:{
                createDG();
                break;
            }
            case DN:{
                createDN();
                break;
            }
            case UDG:{
                createUDG();
                break;
            }
            case UDN:{
                createUDN();
                break;
            }

        }
        return;
    }

    public void createUDG(){
        //创建无向图
    }

    public void createDG(){
        //创建有向图
    }

    public void createUDN(){
        //创建无向网
        System.out.println("创建无向网");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请分别输入图的顶点数,图的边数");
        //顶点数
        vexNum = scanner.nextInt();
        //图的边数
        arcNum = scanner.nextInt();
    }

    public void createDN(){
        //创建有向网
    }

    @Override
    public int getVexNum() {
        //返回顶点数
        return 0;
    }

    @Override
    public int getArcNum() {
        //返回边数
        return 0;
    }

    @Override
    public Object getVex(int x) {
        //返回v表示节点的值0<=v<vexNum
        return null;
    }

    @Override
    public int locateVex(Object x) {
        //给定顶点的值vex，返回其在图中的位置，如果不包含此顶点则返回-1
        return 0;
    }

    @Override
    public int firstAdjVex(int x) throws Exception {
        //返回v的第一个邻接点，若v没有邻接点则返回-1，0<=v<vexNum
        if(x<0 || x>=vexNum){
            throw new Exception("顶点不存在");
        }
        for(int j=0;j<vexNum;j++){
            if(arcs[x][j]!=0 && arcs[x][j]<INFINITY){
                return j;
            }
        }
        return -1;
    }

    @Override
    public int nextAdjVex(int x, int w) {
        //返回v的下一个邻接点，若w是v的最后一个邻接点，则返回-1，其中0<=v,w<vexNum
        return 0;
    }
}
