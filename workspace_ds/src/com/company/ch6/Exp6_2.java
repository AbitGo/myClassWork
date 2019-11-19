package com.company.ch6;

public class Exp6_2 {
    //访问标志数组
    private boolean[] visited;
    //辅助变量，在历遍中用于记录从起始点触发的路径长度
    private int i = 0;
    private boolean find = false;

    public void findPath(IGraph g, int u, int v, int k) throws Exception {
        visited = new boolean[g.getVexNum()];
        for (int i = 0; i < g.getVexNum(); i++) {
            visited[i] = false;
        }
        find_DFS(g, u, v, k);

        if (find) {
            System.out.println(g.getVex(u) + "和" + g.getVex(v) + "之间存在长度为" + k + "的简单路径");
        } else {
            System.out.println(g.getVex(u) + "和" + g.getVex(v) + "之间不存在长度为" + k + "的简单路径");
        }
    }

    public void find_DFS(IGraph g, int u, int v, int k) throws Exception {
        //路径长度等于k时候
        //为什么有u==v的时候因为递归调用
        if (i == k && u == v){
            find = true;
        }else if(!find){
            //访问过后进行true赋值
            visited[u] = true;
            for(int w = g.firstAdjVex(u);w>=0;w=g.nextAdjVex(u,w)){
                //当当前节点没有访问过的时候
                if(!visited[w]){
                    if(i<k){
                        i++;
                        //对v尚未访问的邻接点w递归调用find_DFS
                        //接下来就是w-v之间的空间
                        find_DFS(g,w,v,k);
                    }else {
                        //i==k的时候
                        break;
                    }
                }
                //回退一个节点
                --i;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArcNode ab = new ArcNode(1);
        VNode A = new VNode("A",ab);

        ArcNode bc = new ArcNode(1);
        ArcNode be = new ArcNode(4,0,bc);
        VNode B = new VNode("B",be);

        ArcNode cd = new ArcNode(3);
        VNode C = new VNode("C",cd);

        ArcNode de = new ArcNode(4);
        VNode D =new VNode("D",de);

        ArcNode ef = new ArcNode(5);
        VNode E =new VNode("E",ef);

        ArcNode fa = new ArcNode(0);
        ArcNode fb = new ArcNode(1,0,fa);
        VNode F =new VNode("F",fb);

        VNode[] ves = {A,B,C,D,E,F};
        ALGraph g = new ALGraph(GraphKind.DG,6,8,ves);
        Exp6_2 exp6_2 = new Exp6_2();
        exp6_2.findPath(g,0,5,3);

    }
}
