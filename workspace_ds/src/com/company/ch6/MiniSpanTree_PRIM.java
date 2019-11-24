package com.company.ch6;

import javax.rmi.ssl.SslRMIClientSocketFactory;

public class MiniSpanTree_PRIM {
    //内部类辅助记录从顶点集U到V-U的代价最小的边
    private class CloseEdge{
        Object adjVex;//开始节点
        int lowCost;//最小权值边

        public CloseEdge(Object adjVex, int lowCost) {
            this.adjVex = adjVex;
            this.lowCost = lowCost;
        }
    }

    public Object[][] PRIM(MGraph g,Object u){
        Object[][] tree = new Object[g.getVexNum()-1][2];
        int count = 0;
        CloseEdge[] closeEdges = new CloseEdge[g.getVexNum()];
        int k = g.locateVex(u);
        for(int j = 0;j< g.getVexNum();j++){
            if(j!=k){
                closeEdges[j]=new CloseEdge(u,g.getArcs()[k][j]);
            }
        }
        closeEdges[k]=new CloseEdge(u,0);
        for(int i = 0;i<g.getVexNum();i++){
            System.out.println("getVexNum:"+i+"   len:"+closeEdges.length);
            k = getMiniMun(closeEdges);
            tree[count][0] = closeEdges[k].adjVex;
            tree[count][1] = g.getVexs()[k];
            count++;
            closeEdges[k].lowCost = 0;
            for(int j = 0;j<g.getVexNum();j++){
                if(g.getArcs()[k][j] <closeEdges[j].lowCost){
                    closeEdges[j] = new CloseEdge(g.getVex(k),g.getArcs()[k][j]);
                }
            }
        }
        return tree;
    }
    //在closeEdge中选出lowCast最小且不为0的顶点
    public int getMiniMun (CloseEdge[] closeEdges){
        int min = Integer.MAX_VALUE;
        int v = -1;
        for(int i = 0;i<closeEdges.length;i++)
            if(closeEdges[i].lowCost!=0 && closeEdges[i].lowCost<min){
                min = closeEdges[i].lowCost;
                v = i;
            }
        return v;
    }

}
