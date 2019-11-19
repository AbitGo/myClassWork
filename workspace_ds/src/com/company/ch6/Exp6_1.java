package com.company.ch6;

import com.company.ch3.queue.LinkSqeue;

public class Exp6_1 {
    public final static int INFINITY = Integer.MAX_VALUE;

    public static void CC_BFS(IGraph g) throws Exception {
        boolean[] visited = new boolean[g.getVexNum()];
        //初始化数组
        for (int i = 0; i < g.getVexNum(); i++) {
            visited[i] = false;
        }
        //辅助队列q
        LinkSqeue q = new LinkSqeue();
        //用户记录连通分量得顶点
        LinkSqeue record = new LinkSqeue();

        int i = 0;

        for (int v = 0; v < g.getVexNum(); v++) {
            //用户记录连通分量对队列清零
            record.clear();
            if (!visited[v]) {
                visited[v] = true;
                record.offer(g.getVex(v));
                q.offer(v);
                while (!q.isEmpty()) {//当不为空时
                    int u = (Integer) q.poll();
                    for (int w = g.firstAdjVex(u); w >= 0; w = g.nextAdjVex(u, w)) {
                        if (!visited[w]) {
                            visited[w] = true;
                            record.offer(g.getVex(w));
                            q.offer(w);
                        }
                    }
                }
                System.out.println("图的第" + (++i) + "个连通分量为:");
                while (!record.isEmpty()) {
                    System.out.print(record.poll().toString() + " ");
                }
                System.out.println();
            }
        }

    }

    public static void main(String[] args) throws Exception {

        Object vexs[] = {"A", "B", "C", "D", "E", "F", "G"};
        int[][] arcs = {
                {0, 1, INFINITY, 1, INFINITY, INFINITY, INFINITY},
                {1, 0, 1, INFINITY, INFINITY, INFINITY, INFINITY},
                {INFINITY, 1, 0, 1, INFINITY, INFINITY, INFINITY},
                {1, INFINITY, 1, 0, INFINITY, INFINITY, INFINITY},
                {INFINITY, INFINITY, INFINITY, INFINITY, 0, 1, INFINITY},
                {INFINITY, INFINITY, INFINITY, INFINITY, 1, 0, 1},
                {INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 1, 0}
        };
        MGraph g = new MGraph(GraphKind.UDG, 7, 6, vexs, arcs);
        CC_BFS(g);
    }
}
