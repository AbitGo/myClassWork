package com.company.ch5;

public class BiTreeNode {
    public Object data;
    public BiTreeNode lchild,rchild;
    //构造一个空节点
    public BiTreeNode(){
        this(null);
    }
    //构造一颗左右子域为空的二叉树
    public BiTreeNode(Object data){
        this(data,null,null);
    }
    //构建一个数据域和左、右孩子域都不为空的二叉树
    public BiTreeNode(Object data,BiTreeNode lchild,BiTreeNode rchild){
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
    }

}
