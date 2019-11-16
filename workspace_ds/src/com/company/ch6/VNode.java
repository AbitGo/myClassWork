package com.company.ch6;

public class VNode {
    public Object data;
    public ArcNode firsArc;

    public VNode() {
        this(null, null);
    }

    public VNode(Object data, ArcNode firsArc) {
        this.data = data;
        this.firsArc = firsArc;
    }

    public VNode(Object data) {
        this(data, null);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ArcNode getFirsArc() {
        return firsArc;
    }

    public void setFirsArc(ArcNode firsArc) {
        this.firsArc = firsArc;
    }
}
