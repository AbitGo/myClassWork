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

        System.out.println("对树深度查询1<递归>为:");
        count = biTreeTest.getDepth1(biTreeTest.getRoot());
        System.out.println("count:"+count);
        System.out.println();
        System.out.println("-------------");

        System.out.println("对树深度查询2<非递归>为:");
        count = biTreeTest.getDepth2(biTreeTest.getRoot());
        System.out.println("count:"+count);
        System.out.println();
        System.out.println("-------------");


        BiTree biTree2 = createBiTree();
        System.out.println("对两棵树进行<方法一>对比:");
        Boolean result_equals = biTreeTest.isEqual1(biTreeTest.getRoot(),biTree2.getRoot());
        if(result_equals==true){
            System.out.println("两棵树一致");
        }else {
            System.out.println("两棵树不一致");
        }
        System.out.println("-------------");

        System.out.println("对两棵树进行<方法二>对比:");
        result_equals = biTreeTest.isEqual2(biTreeTest.getRoot(),biTree2.getRoot());
        if(result_equals==true){
            System.out.println("两棵树一致");
        }else {
            System.out.println("两棵树不一致");
        }
        System.out.println("-------------");

        System.out.println("利用前序遍历以及中序遍历生成二叉树:");
        String preOrder = "ABDEGCFH";
        String inOrder = "DBGEAFHC";
        BiTree testa = new BiTree(preOrder,inOrder,0,0,preOrder.length());
        System.out.println("后根遍历");
        testa.postRootTraverse(testa.getRoot());
        System.out.println("-------------");

        System.out.println("标明空子树的先根遍历创建一颗二叉树:");
        String preOrder1 = "AB##CD###";
        BiTree testb = new BiTree(preOrder1);
        System.out.println("先根遍历");
        testa.preRootTraverse(testb.getRoot());
        System.out.println("中根遍历");
        testa.inRootTraverse(testb.getRoot());
        System.out.println("后根遍历");
        testa.postRootTraverse(testb.getRoot());
        System.out.println("-------------");

        System.out.println("完全二叉树的顺序存储结构建立其二叉链式存储结构:");
        String preOrder2 = "ABCDEFGH";

        BiTreeNode a = new BiTree().createBiTree_method(preOrder2,0);
        System.out.println("先根遍历");
        testa.preRootTraverse(a);
        System.out.println();
        System.out.println("中根遍历");
        testa.inRootTraverse(a);
        System.out.println();
        System.out.println("后根遍历");
        testa.postRootTraverse(a);
        System.out.println();
        System.out.println("-------------");

    }
}
