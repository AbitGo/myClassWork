package com.company.ch5;

import com.company.ch3.Stack.SqStack.SqStack;
import com.company.ch3.queue.LinkSqeue;

public class BiTree {
    private BiTreeNode root;


    //通过先根+中根/后根+中根建立
    public BiTree(String preOrder, String inOrder, int preIndex, int inIndex, int count) {
        if (count > 0) {
            char r = preOrder.charAt(preIndex);
            int i = 0;
            for(;i<count;i++){
                if(r==inOrder.charAt(i+inIndex)){
                    break;
                }
            }
            root = new BiTreeNode(r);
            root.lchild = new BiTree(preOrder,inOrder,preIndex+1,inIndex,i).root;
            root.rchild = new BiTree(preOrder,inOrder,preIndex+i+1,inIndex+i+1,count-i-1).root;

        }
    }

    //由标明空子树的先根遍历创建一颗二叉树
    public BiTree(String preStr){
        if(constant.index_len<=preStr.length()){
            char c = preStr.charAt(constant.index_len++);
            if(c!='#'){
                root = new BiTreeNode(c);
                root.rchild = new BiTree(preStr).root;
                root.lchild = new BiTree(preStr).root;
            }else {
                root = null;
            }
        }
    }

    //由完全二叉树的顺序存储结构建立其二叉链式存储结构
    public BiTreeNode createBiTree_method(String sqBiTree,int index){
        BiTreeNode root = null;
        if(index<sqBiTree.length()){
            root = new BiTreeNode(sqBiTree.charAt(index));
            root.lchild = createBiTree_method(sqBiTree,index*2+1);
            root.rchild = createBiTree_method(sqBiTree,index*2+2);
        }
        return root;
    }

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
        //将根节点压入栈中
        BiTreeNode t = this.root;
        if (t != null) {
            SqStack s = new SqStack();
            s.push(t);
            while (!s.isEmpty()) {
                t = (BiTreeNode) s.pop();
                System.out.print(t.data);
                while (t != null) {
                    if (t.lchild != null) {
                        //如果左孩子为空则直接打印该节点
                        System.out.print(t.lchild.data);
                    }
                    if (t.rchild != null) {
                        //右孩子非空入栈
                        s.push(t.rchild);
                    }
                    //向下一层深入左子树
                    t = t.lchild;
                }
            }
        }
    }

    //非递归中序历遍
    public void inRootTraverse() throws Exception {
        //LDR
        //将根节点压入栈中
        BiTreeNode root = this.root;
        if (root != null) {
            SqStack sqStack = new SqStack();
            sqStack.push(root);
            //当栈不为空时
            while (!sqStack.isEmpty()) {

                //System.out.println("peek"+sqStack.peek());
                //当压入栈的是空指针及到了最深层的左子树
                while (sqStack.peek() != null) {

                    BiTreeNode node = ((BiTreeNode) sqStack.peek()).lchild;
                    sqStack.push(node);
                }

                //因为最后一个压入栈中的肯定是null,所以才造成该循环结束
                sqStack.pop();
                //当栈不为空的时候提取栈顶
                if (!sqStack.isEmpty()) {
                    root = (BiTreeNode) sqStack.pop();
                    //打印该节点
                    System.out.print(root.data);
                    sqStack.push(root.rchild);
                }
            }

        }
    }

    //非递归后序历遍
    public void postRootTraverse() {
        //LRD

    }

    //层次历遍操作实现的非递归算法
    public void levelTraverse() {
        BiTreeNode t = this.root;
        if (t != null) {
            LinkSqeue circleSqQueue = new LinkSqeue();
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

    public BiTreeNode searchNode(BiTreeNode t, Object x) {
        if (t != null) {
            if (t.data.equals(x))
                return t;
            else {
                BiTreeNode lresult = searchNode(t.lchild, x);
                return lresult != null ? lresult : searchNode(t.rchild, x);
            }
        }
        return null;
    }

    //统计二叉树中结点个数的算法
    //使用递归的方式
    public int countNode1(BiTreeNode t) {
        //使用递归算法
        int count = 0;
        if (t != null) {
            ++count;
            count += countNode1(t.lchild);
            count += countNode1(t.rchild);
        }
        return count;
    }

    //统计二叉树中结点个数的算法
    //使用递归的方式
    public int countNode2(BiTreeNode t) {
        //使用递归算法
        if (t == null) {
            return 0;
        } else {
            return 1 + countNode2(t.lchild) + countNode2(t.rchild);
        }
    }

    //统计二叉树中结点个数的算法
    //使用非递归的方式
    //类似于层次历遍
    public int countNode3(BiTreeNode t) {
        //使用递归算法
        int count = 0;
        if (t != null) {
            LinkSqeue linkSqeue = new LinkSqeue();
            linkSqeue.offer(t);
            while (!linkSqeue.isEmpty()) {
                t = (BiTreeNode) linkSqeue.poll();
                ++count;
                if (t.lchild != null) {
                    linkSqeue.offer(t.lchild);
                }
                if (t.rchild != null) {
                    linkSqeue.offer(t.rchild);
                }
            }
        }
        return count;
    }

    public int getDepth1(BiTreeNode t) {
        if (t != null) {
            int lDepth = getDepth1(t.lchild);
            int rDepth = getDepth1(t.rchild);
            return 1 + (lDepth > rDepth ? lDepth : rDepth);
        }
        return 0;
    }


    public int getDepth2(BiTreeNode t) {
        if (t == null) {
            return 0;
        } else if (t.rchild == null && t.lchild == null) {
            return 1;
        } else {
            return 1 + (getDepth1(t.lchild) > getDepth1(t.rchild) ? getDepth1(t.lchild) : getDepth1(t.rchild));
        }
    }

    public boolean isEqual1(BiTreeNode t1, BiTreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        //当两个节点都不为空时
        if (t1 != null && t2 != null) {
            if (t1.data.equals(t2.data)) {
                //左子树是否相等
                if (isEqual1(t1.lchild, t2.lchild)) {
                    //右子树是否相等
                    if (isEqual1(t1.rchild, t2.rchild)) {
                        return true;
                    }
                }
            }
        }
        //当剩下只有一个不为空，则返回false
        return false;
    }

    public boolean isEqual2(BiTreeNode t1, BiTreeNode t2) {
        //当两个节点都为空时,必定为真
        if (t1 == null && t2 == null) {
            return true;
        }
        //当两个节点都不为空时
        else if (t1 != null && t2 != null) {
            //必须满足三个条件
            //1。两个结点的值相等
            //2。左子树相等
            //3。右子树相等
            return (t1.data.equals(t2.data) && (isEqual2(t1.lchild, t2.lchild)) && (isEqual2(t1.rchild, t2.rchild)));
        }
        //当剩下只有一个不为空，则返回false
        return false;
    }
}
