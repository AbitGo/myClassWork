package com.company.ch6;

import com.company.ch3.queue.LinkSqeue;

public class GraphFS {
    private static boolean[] visited;

    public static void BFSTraverse(IGraph g) throws Exception {
        visited = new boolean[g.getVexNum()];
        //全部进行初始化，但是java初始化的时候都是false
        for (int v = 0; v < g.getVexNum(); v++) {
            visited[v] = false;
        }
        for (int v = 0; v < g.getVexNum(); v++) {
            //如果当前没被访问
            if (visited[v] == false) {
                BFS(g,v);
            }
        }
    }

    public static void BFS(IGraph g,int v) throws Exception {
        //该节点已经被访问过了
        visited[v]=true;
        System.out.println(g.getVex(v).toString()+" ");
        //创建队列
        LinkSqeue linkSqeue = new LinkSqeue();
        //入队
        linkSqeue.offer(v);
        //当队列不为空时
        while (!linkSqeue.isEmpty()){
            //队头元素出队，并赋值给u
            int u = (Integer) linkSqeue.poll();
            //w为第一个邻接点，并且依次往下读取
            for(int w = g.firstAdjVex(u);w>=0;w=g.nextAdjVex(u,w)){
                //当当前邻接点还没有被读取的时候，则进行状态的翻转
                if(!visited[w]){
                    visited[w]=true;
                    System.out.println(g.getVex(w).toString()+" ");
                    linkSqeue.offer(w);
                }
            }
        }
    }

    //深度优先搜索算法的实现
    public static void DFSTraverse(IGraph g)throws Exception{
        visited = new boolean[g.getVexNum()];
        for (int v = 0; v < g.getVexNum(); v++) {
            visited[v] = false;
        }
        for (int v = 0; v < g.getVexNum(); v++) {
            //如果当前没被访问
            if (visited[v] == false) {
                DFS(g,v);
            }
        }
    }

    public static void DFS(IGraph g,int v) throws Exception {
        //该节点已经被访问过了
        visited[v]=true;
        System.out.println(g.getVex(v).toString()+" ");
        for(int w = g.firstAdjVex(v);w>=0;w=g.nextAdjVex(v,w)){
            if(!visited[w]){
                DFS(g,w);
            }
        }
    }
}
