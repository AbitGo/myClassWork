package com.company.ch5;

public class DebugBiTree {

    public static BiTree createBiTree(){
        BiTreeNode d = new BiTreeNode("D");
        BiTreeNode g = new BiTreeNode("G");
        BiTreeNode h = new BiTreeNode("H");
        BiTreeNode e = new BiTreeNode("E",g,null);
        BiTreeNode b = new BiTreeNode("B",d,e);
        BiTreeNode f = new BiTreeNode("F",null,h);
        BiTreeNode c = new BiTreeNode("C",f,null);
        BiTreeNode a = new BiTreeNode("A",b,c);
        return new BiTree(a);

    }

    public static void main(String[] args) throws Exception {
        BiTree biTreeTest = createBiTree();

        System.out.println("递归先根遍历:");
        BiTreeNode root = biTreeTest.getRoot();
        biTreeTest.preRootTraverse(root);
        System.out.println();
        System.out.println("-------------");

        System.out.println("递归中根遍历:");
        biTreeTest.inRootTraverse(root);
        System.out.println();
        System.out.println("-------------");

        System.out.println("递归后根遍历:");
        biTreeTest.postRootTraverse(root);
        System.out.println();
        System.out.println("-------------");

        System.out.println("非递归先根遍历:");
        biTreeTest.preRootTraverse();
        System.out.println();
        System.out.println("-------------");

        System.out.println("非递归中根遍历:");
        biTreeTest.inRootTraverse();
        System.out.println();
        System.out.println("-------------");

        System.out.println("层次历遍操作实现的非递归算法:");
        biTreeTest.levelTraverse();
        System.out.println();
        System.out.println("-------------");

        System.out.println("对树进行查询:");
        BiTreeNode result =  biTreeTest.searchNode(biTreeTest.getRoot(),"B");
        if(result!=null)
            System.out.println(result.data);
        else
            System.out.println();
        System.out.println();
        System.out.println("-------------");

        System.out.println("对树节点个数进行查询:");
        int count = biTreeTest.countNode1(biTreeTest.getRoot());
        System.out.println("count:"+count);
        System.out.println();
        System.out.println("-------------");

        System.out.println("对树节点个数进行查询2:");
        count = biTreeTest.countNode2(biTreeTest.getRoot());
        System.out.println("count:"+count);
        System.out.println();
        System.out.println("-------------");

        System.out.println("对树节点个数进行查询3:");
        count = biTreeTest.countNode3(biTreeTest.getRoot());
        System.out.println("count:"+count);
        System.out.println();
        System.out.println("-------------");

    }
}
