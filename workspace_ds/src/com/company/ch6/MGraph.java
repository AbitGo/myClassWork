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

    public MGraph(){

    }

    @Override
    public void createGraph(Object[] param1,String[] param2) {
        //创建图;
        GraphKind graphKind = GraphKind.valueOf((String) param1[0]);

        switch (graphKind){
            case DG:{
                this.graphKind = GraphKind.DG;
                createDG(param1,param2);
                break;
            }
            case DN:{
                createDN(param1,param2);
                this.graphKind = GraphKind.DN;
                break;
            }
            case UDG:{
                createUDG(param1,param2);
                this.graphKind = GraphKind.UDG;
                break;
            }
            case UDN:{
                createUDN(param1,param2);
                this.graphKind = GraphKind.UDN;
                break;
            }

        }
        return;
    }

    public void createUDG(Object[] param1,String[] param2){
        //创建无向图
        System.out.println("创建无向图");
        //顶点数
        vexNum = (int)param1[1];
        //图的边数
        arcNum = (int)param1[2];
        vexs = new Object[vexNum];

        for(int v = 0;v<((String)param1[3]).length();v++){
            //分别赋值ABCDE
            vexs[v]= ((String)param1[3]).charAt(v);
        }
        arcs = new int[vexNum][vexNum];
        for (int v = 0;v<vexNum;v++){
            for(int x = 0;x<vexNum;x++){
                //赋值为int的max值，为无限大
                arcs[v][x]= 0;
            }
        }
        for(int i=0;i<arcNum;i++){
            //通过拆解字符串AB这类字符串，以获得顶点
            //这里说明AB是指A->B
            //及第A行(1)行得第二列为1
            int v = locateVex(param2[i].charAt(0));
            int u = locateVex(param2[i].charAt(1));
            arcs[v][u]=arcs[u][v]=1;
        }
    }

    public void createDG(Object[] param1,String[] param2){
        //创建有向图
        System.out.println("创建有向图");
        //顶点数
        vexNum = (int)param1[1];
        //图的边数
        arcNum = (int)param1[2];

        vexs = new Object[vexNum];

        for(int v = 0;v<((String)param1[3]).length();v++){
            //分别赋值
            vexs[v]= ((String)param1[3]).charAt(v);
        }
        arcs = new int[vexNum][vexNum];
        for (int v = 0;v<vexNum;v++){
            for(int x = 0;x<vexNum;x++){
                //赋值为int的max值，为无限大
                arcs[v][x]= 0;
            }
        }
        for(int i=0;i<arcNum;i++){
            //通过拆解字符串AB这类字符串，以获得顶点
            //这里说明AB是指A->B
            //及第A行(1)行得第二列为1
            int v = locateVex(param2[i].charAt(0));
            int u = locateVex(param2[i].charAt(1));
            arcs[v][u]=1;
        }
    }

    public void createUDN(Object[] param1,String[] param2){
        //创建无向网
        System.out.println("创建无向网");
        //顶点数
        vexNum = (int)param1[1];
        //网的边数
        arcNum = (int)param1[2];

        vexs = new Object[vexNum];

        for(int v = 0;v<((String)param1[3]).length();v++){
            //分别赋值ABCDE
            vexs[v]= ((String)param1[3]).charAt(v);
        }
        arcs = new int[vexNum][vexNum];
        for (int v = 0;v<vexNum;v++){
            for(int x = 0;x<vexNum;x++){
                //赋值为int的max值，假装为无限大
                arcs[v][x]= INFINITY;
            }
        }
        for(int i=0;i<arcNum;i++){
            //通过拆解字符串AB这类字符串，以获得顶点
            //这里说明AB是指A->B
            //及第A行(1)行得第二列为1
            int v = locateVex(param2[i].charAt(0));
            int u = locateVex(param2[i].charAt(1));
            arcs[v][u]=arcs[u][v]=(int)param2[i].charAt(2);
        }
    }

    public void createDN(Object[] param1,String[] param2){
        //创建有向网
        System.out.println("创建有向网");
        //顶点数
        vexNum = (int)param1[1];
        //图的边数
        arcNum = (int)param1[2];

        vexs = new Object[vexNum];

        for(int v = 0;v<vexNum;v++){
            //分别赋值ABCDE
            vexs[v]= 'A'+v;
        }
        arcs = new int[vexNum][vexNum];
        for (int v = 0;v<vexNum;v++){
            for(int x = 0;x<vexNum;x++){
                //赋值为int的max值，假装为无限大
                arcs[v][x]= INFINITY;
            }
        }
        for(int i=0;i<arcNum;i++){
            //通过拆解字符串AB这类字符串，以获得顶点
            //这里说明AB是指A->B
            //及第A行(1)行得第二列为1
            int v = locateVex(param2[i].charAt(0));
            int u = locateVex(param2[i].charAt(1));
            arcs[v][u]=(int)param2[i].charAt(2);
        }
    }

    @Override
    public int getVexNum() {
        //返回顶点数
        return this.vexNum;
    }

    public int[][] getArcs() {
        return arcs;
    }

    public Object[] getVexs() {
        return vexs;
    }

    public void setVexs(Object[] vexs) {
        this.vexs = vexs;
    }

    @Override
    public int getArcNum() {
        //返回边数
        return this.arcNum;
    }

    @Override
    public Object getVex(int x) {
        //返回v表示节点的值0<=v<vexNum
        return this.vexs[x];
    }

    @Override
    public int locateVex(Object x) {
        //给定顶点的值vex，返回其在图中的位置，如果不包含此顶点则返回-1
        for(int v = 0;v<vexNum;v++){
            if(vexs[v].equals(x)){
                return v;
            }
        }
        return -1;
    }

    @Override
    public int firstAdjVex(int x) throws Exception {
        //返回v的第一个邻接点，若v没有邻接点则返回-1，0<=v<vexNum
        if(x<0 || x>=vexNum){
            throw new Exception("顶点不存在");
        }
        //为什么这样的呢，因为在查找A的第一个邻接点必定是0
        for(int j=0;j<vexNum;j++){
            if(arcs[x][j]!=0 && arcs[x][j]<INFINITY){
                return j;
            }
        }
        return -1;
    }

    @Override
    public int nextAdjVex(int v, int w) throws Exception {
        //w是firstAdjVex获取，如果要一直获取就可以了
        //返回v的下一个邻接点，若w是v的最后一个邻接点，则返回-1，其中0<=v,w<vexNum
        if(v<0 || v>=vexNum){
            throw new Exception("顶点不存在");
        }
        for(int j= w+1;j<vexNum;j++){
            if(arcs[v][j]!=0 && arcs[v][j]<INFINITY){
                return j;
            }
        }
        return -1;
    }
}
