package com.company.ch6;

public class Exp6_4 {
    public final static int INFINIFY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Object vexs[] = {"v0","v1","v2","v3","v4","v5"};
        int[][] arcs = {
                {INFINIFY,7,1,5,INFINIFY,INFINIFY},
                {7,INFINIFY,6,INFINIFY,3,INFINIFY},
                {1,6,INFINIFY,7,6,4},
                {5,INFINIFY,7,INFINIFY,INFINIFY,2},
                {INFINIFY,3,6,INFINIFY,INFINIFY,7},
                {INFINIFY,INFINIFY,4,2,7,INFINIFY}
        };
        //public MGraph(GraphKind graphKind, int vexNum, int arcNum, Object[] vexs, int[][] arcs) {
        MGraph g = new MGraph(GraphKind.UDG,6,10,vexs,arcs);
        Object[][] tree = new MiniSpanTree_PRIM().PRIM(g,"v1");
        for(int i = 0;i<tree.length;i++){
            System.out.println(tree[i][0]+" - "+tree[i][1]);
        }
    }
}
