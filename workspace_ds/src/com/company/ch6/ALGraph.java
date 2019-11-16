package com.company.ch6;

public class ALGraph implements IGraph {
    private GraphKind graphKind;//图的种类标识
    private int vexNum, arcNum;//图当前定点数和边数
    private VNode[] vexs;//顶点

    public ALGraph(GraphKind graphKind, int vexNum, int arcNum, VNode[] vexs) {
        this.graphKind = graphKind;
        this.vexNum = vexNum;
        this.arcNum = arcNum;
        this.vexs = vexs;
    }

    public ALGraph() {
        this(null, 0, 0, null);
    }

    @Override
    public void createGraph(Object[] param1, String[] param2) {
        //创建图;
        GraphKind graphKind = GraphKind.valueOf((String) param1[0]);

        switch (graphKind) {
            case DG: {
                this.graphKind = GraphKind.DG;
                createDG(param1, param2);
                break;
            }
            case DN: {
                createDN(param1, param2);
                this.graphKind = GraphKind.DN;
                break;
            }
            case UDG: {
                createUDG(param1, param2);
                this.graphKind = GraphKind.UDG;
                break;
            }
            case UDN: {
                createUDN(param1, param2);
                this.graphKind = GraphKind.UDN;
                break;
            }

        }
        return;
    }

    public void createUDG(Object[] param1, String[] param2) {
        //创建无向图
        System.out.println("创建无向图");
        //顶点数
        vexNum = (int) param1[1];
        //图的边数
        arcNum = (int) param1[2];

        vexs = new VNode[vexNum];

        for (int v = 0; v < ((String) param1[3]).length(); v++) {
            //分别赋值ABCDE
            vexs[v] = new VNode(((String) param1[3]).charAt(v));
        }
        for (int i = 0; i < arcNum; i++) {
            //通过拆解字符串AB这类字符串，以获得顶点
            int v = locateVex(param2[i].charAt(0));
            int u = locateVex(param2[i].charAt(1));
            //图无权值
            addArc(v, u, 1);
            addArc(u, v, 1);
        }
    }

    public void createDG(Object[] param1, String[] param2) {
        //创建有向图
        System.out.println("创建无向图");
        //顶点数
        vexNum = (int) param1[1];
        //图的边数
        arcNum = (int) param1[2];

        vexs = new VNode[vexNum];

        for (int v = 0; v < ((String) param1[3]).length(); v++) {
            //分别赋值ABCDE
            vexs[v] = new VNode(((String) param1[3]).charAt(v));
        }
        for (int i = 0; i < arcNum; i++) {
            //通过拆解字符串AB这类字符串，以获得顶点
            int v = locateVex(param2[i].charAt(0));
            int u = locateVex(param2[i].charAt(1));
            //图无权值
            addArc(v, u, 1);
        }
    }

    public void createUDN(Object[] param1, String[] param2) {
        //创建无向网
        System.out.println("创建无向图");
        //顶点数
        vexNum = (int) param1[1];
        //图的边数
        arcNum = (int) param1[2];

        vexs = new VNode[vexNum];

        for (int v = 0; v < ((String) param1[3]).length(); v++) {
            //分别赋值ABCDE
            vexs[v] = new VNode(((String) param1[3]).charAt(v));
        }
        for (int i = 0; i < arcNum; i++) {
            //通过拆解字符串AB这类字符串，以获得顶点
            int v = locateVex(param2[i].charAt(0));
            int u = locateVex(param2[i].charAt(1));
            int value = (int) param2[i].charAt(2);
            addArc(v, u, value);
            addArc(u, v, value);
        }
    }

    public void createDN(Object[] param1, String[] param2) {
        //创建有向网
        System.out.println("创建无向图");
        //顶点数
        vexNum = (int) param1[1];
        //图的边数
        arcNum = (int) param1[2];

        vexs = new VNode[vexNum];

        for (int v = 0; v < ((String) param1[3]).length(); v++) {
            //分别赋值ABCDE
            vexs[v] = new VNode(((String) param1[3]).charAt(v));
        }
        for (int i = 0; i < arcNum; i++) {
            //通过拆解字符串AB这类字符串，以获得顶点
            int v = locateVex(param2[i].charAt(0));
            int u = locateVex(param2[i].charAt(1));
            int value = (int) param2[i].charAt(2);
            addArc(v, u, value);
        }
    }

    @Override
    public int getVexNum() {
        return this.vexNum;
    }

    @Override
    public int getArcNum() {
        return this.arcNum;
    }

    @Override
    public Object getVex(int x) throws Exception {
        if (x < 0 || x > vexNum) {
            throw new Exception("顶点不存在");
        }
        return vexs[x].getData();
    }

    @Override
    public int locateVex(Object x) {
        for (int v = 0; v < vexNum; v++) {
            if (vexs[v].data.equals(v)) {
                return v;
            }
        }
        return -1;
    }

    public GraphKind getGraphKind() {
        return graphKind;
    }

    public void setGraphKind(GraphKind graphKind) {
        this.graphKind = graphKind;
    }

    @Override
    public int firstAdjVex(int x) throws Exception {
        if (x < 0 || x > vexNum) {
            throw new Exception("顶点不存在");
        }
        VNode vex = vexs[x];
        //指向第一条依附于该顶点的弧不为空时
        if (vex.firsArc != null) {
            //返回该弧指向的顶点位置
            return vex.firsArc.adjVex;
        } else
            return -1;
    }

    @Override
    public int nextAdjVex(int x, int w) throws Exception {
        if (x < 0 || x > vexNum) {
            throw new Exception("顶点不存在");
        }
        VNode vex = vexs[x];
        ArcNode arcw = null;
        for (ArcNode arc = vex.firsArc; arc != null; arc = arc.nextArc) {
            if (arc.adjVex == w) {
                arcw = arc;
                break;
            }
        }
        if (arcw != null && arcw.nextArc != null) {
            return arcw.nextArc.adjVex;
        } else
            return -1;
    }

    public void addArc(int v, int u, int value) {
        ArcNode arc = new ArcNode(u, value);
        arc.nextArc = vexs[v].firsArc;
        vexs[v].firsArc = arc;
    }
}
