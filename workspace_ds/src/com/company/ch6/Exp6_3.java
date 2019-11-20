package com.company.ch6;

import com.company.ch3.Stack.LinkStack.LinkStack;

public class Exp6_3 {
    //访问标志数组
    private boolean[] visited;
    //
    private LinkStack s = new LinkStack();
    //表示是否已经找到了环
    private boolean find = false;

    public void findCircle(IGraph g)throws Exception{
        visited = new boolean[g.getVexNum()];
        for(int i = 0;i<g.getVexNum();i++){
            visited[i]=false;
        }
        for(int v = 0;v<g.getVexNum();v++){
            if(!visited[v]){
                find_DFS(g,v);
            }
        }
        if(find){
            System.out.println("该有向图存在环");
        }else{
            System.out.println("该有向图不存在环");
        }
    }

    public void find_DFS(IGraph g,int v) throws Exception {
        if(!find){
            visited[v]=true;
            s.push(v);
            for(int w = g.firstAdjVex(v);w>=0;w=g.nextAdjVex(v,w)){
                if(visited[v]&&isDuplicate(w)){
                    find = true;
                }else {
                    find = false;
                }
            }
            s.pop();
        }
    }

    private boolean isDuplicate(Integer w)throws Exception{
        //辅助栈
        LinkStack stack = new LinkStack();
        while(!s.isEmpty() && !((Integer)s.peek()).equals(w)){
            stack.push(s.pop());
        }
        if(s.isEmpty()){
            while(!stack.isEmpty()){
                s.push(stack.pop());
            }
            return false;
        }else
            return true;

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
        Exp6_3 exp6_3 = new Exp6_3();
        exp6_3.findCircle(g);
    }
}
