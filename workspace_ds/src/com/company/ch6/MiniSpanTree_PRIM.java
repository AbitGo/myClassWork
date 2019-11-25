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
        //n个顶点最多生成n-1条边
        Object[][] tree = new Object[g.getVexNum()-1][2];
        //边的个数
        int count = 0;
        //任意一边到其他点的值
        CloseEdge[] closeEdges = new CloseEdge[g.getVexNum()];
        //定位位置，初始位置
        int k = g.locateVex(u);
        //进行分别赋值，一共是n-1条边
        for(int j = 0;j< g.getVexNum();j++){
            //当自己到自己的时候不需要进行赋值
            if(j!=k){
                closeEdges[j]=new CloseEdge(u,g.getArcs()[k][j]);
            }
        }
        //自己到自己是0，与上面进行了呼应
        closeEdges[k]=new CloseEdge(u,0);
        //选择其余的n-1条边
        //一定要是从1->n-1或者0->n-2
        for(int i = 1;i<g.getVexNum();i++){
            //求出该点到其他边权值最小的一个边
            k = getMiniMun(closeEdges);
            //进行启示位置的赋值
            tree[count][0] = closeEdges[k].adjVex;
            //对权值的大小进行赋值
            tree[count][1] = g.getVexs()[k];
            //边的个数+1
            count++;
            //对刚才进行过赋值的边进行赋值以免造成影响
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
