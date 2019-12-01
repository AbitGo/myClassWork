package com.company.ch6;

import com.company.ch3.queue.LinkSqeue;

public class CSTreeNode {
    public Object data;
    public CSTreeNode firstChild,nextSibling;

    public CSTreeNode() {
        this(null);
    }

    public CSTreeNode(Object data) {
        this(data,null,null);
    }

    public CSTreeNode(Object data, CSTreeNode firstChild, CSTreeNode nextSibling) {
        this.data = data;
        this.firstChild = firstChild;
        this.nextSibling = nextSibling;
    }

    public void preRootTraverse(CSTreeNode t){
        if(t!=null){
            System.out.print(t.data);
            preRootTraverse(t.firstChild);
            //先根访问其他的结点
            preRootTraverse(t.nextSibling);
        }
    }
    public void postRootTraverse(CSTreeNode t){
        if(t!=null){
            postRootTraverse(t.firstChild);
            System.out.print(t.data);
            postRootTraverse(t.nextSibling);
        }
    }

    public void levelTraverse(CSTreeNode t){
        if(t!=null){
            LinkSqeue sqeue = new LinkSqeue();
            sqeue.offer(t);
            //当队列不为空时
            while (!sqeue.isEmpty()){
                //一直向下获取下一个链
                for(t=(CSTreeNode)sqeue.poll();t!=null;t= t.nextSibling){
                    //访问结点及其所有兄弟结点
                    System.out.print(t.data+" ");
                    //第一个孩子结点非空入队列
                    if(t.firstChild!=null){
                        sqeue.offer(t.firstChild);
                    }
                }
            }
        }
    }
}
