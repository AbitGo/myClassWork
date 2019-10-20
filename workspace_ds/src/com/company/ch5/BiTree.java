package com.company.ch5;

import com.company.ch3.Stack.CircleSqQueue;
import com.company.ch3.Stack.LinkStack.LinkStack;

public class BiTree {
    private BiTreeNode root;

    public BiTreeNode getRoot() {
        return root;
    }

    public void setRoot(BiTreeNode root) {
        this.root = root;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        BiTree.index = index;
    }

    //构建一颗空树
    public BiTree() {
        this.root = null;
    }

    //构造一颗树
    public BiTree(BiTreeNode root) {
        this.root = root;
    }

    //用于记录preStr的索引值
    private static int index = 0;

    //递归先序历遍
    public void preRootTraverse(BiTreeNode t) {
        //DLR
        if (t != null) {
            System.out.print(t.data);
            preRootTraverse(t.lchild);
            preRootTraverse(t.rchild);
        }
    }

    //递归中序历遍
    public void inRootTraverse(BiTreeNode t) {
        //LDR
        if (t != null) {
            inRootTraverse(t.lchild);
            System.out.print(t.data);
            inRootTraverse(t.rchild);
        }
    }

    //递归中序历遍
    public void postRootTraverse(BiTreeNode t) {
        //LRD
        if (t != null) {
            postRootTraverse(t.lchild);
            postRootTraverse(t.rchild);
            System.out.print(t.data);
        }
    }

    //非递归先序历遍
    public void preRootTraverse() throws Exception {
        //DLR
        BiTreeNode t = this.root;
        if (t != null) {
            LinkStack s = new LinkStack();
            s.push(t);
            while (!s.isEmpty()) {
                t = (BiTreeNode) s.pop();
                System.out.print(t.data);
                while (t != null) {
                    if (t.lchild != null) {
                        System.out.print(t.lchild.data);
                    }
                    if (t.rchild != null) {
                        System.out.print(t.rchild.data);
                    }
                    t = t.lchild;
                }
            }
        }
    }

    //非递归中序历遍
    public void inRootTraverse() {
        //LDR

    }

    //非递归中序历遍
    public void postRootTraverse() {
        //LRD

    }

    public BiTreeNode searchNode(BiTreeNode t,Object x){
        if(t!=null){
            if(t.data.equals(x))
                return t;
            else{
                BiTreeNode lresult = searchNode(t.lchild,x);
                return lresult !=null ? lresult :searchNode(t.rchild,x);
            }
        }
        return null;
    }

    public void levelTraverse() {
        //层次历遍操作实现的非递归算法
        BiTreeNode t = this.root;
        if (t != null) {
            CircleSqQueue circleSqQueue = new CircleSqQueue(40);
            //根节点入队
            circleSqQueue.offer(t);
            while (!circleSqQueue.isEmpty()) {
                t = (BiTreeNode) circleSqQueue.poll();
                System.out.print(t.data);
                if (t.lchild != null) {
                    circleSqQueue.offer(t.lchild);
                }
                if (t.rchild != null) {
                    circleSqQueue.offer(t.rchild);
                }
            }
        }
    }


}
