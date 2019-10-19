package com.company.ch5;

public class DebugBiTree {

    public static BiTree createBiTree(){
        BiTreeNode d = new BiTreeNode("D");
        BiTreeNode g = new BiTreeNode("G");
        BiTreeNode h = new BiTreeNode("H");
        BiTreeNode e = new BiTreeNode("E",g,null);
        BiTreeNode b = new BiTreeNode("D",d,e);
        BiTreeNode f = new BiTreeNode("F",null,h);
        BiTreeNode c = new BiTreeNode("C",f,null);
        BiTreeNode a = new BiTreeNode("A",b,c);
        return new BiTree(a);

    }

    public static void main(String[] args) {
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

        System.out.println("递归先根遍历:");
        biTreeTest.preRootTraverse(root);
        System.out.println();
        System.out.println("-------------");
    }
}
