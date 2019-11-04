package com.company.ch5;

import com.company.ch3.Stack.CircleSqQueue;
import com.company.ch3.Stack.LinkStack.LinkStack;
import com.company.ch3.Stack.SqStack.SqStack;
import com.company.ch3.queue.LinkSqeue;
import jdk.nashorn.internal.codegen.types.BitwiseType;

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
            while (!sqStack.isEmpty()){

                //System.out.println("peek"+sqStack.peek());
                //当压入栈的是空指针及到了最深层的左子树
                while (sqStack.peek() !=null){

                    BiTreeNode node = ((BiTreeNode)sqStack.peek()).lchild;
                    sqStack.push(node);
                }

                //因为最后一个压入栈中的肯定是null,所以才造成该循环结束
                sqStack.pop();
                //当栈不为空的时候提取栈顶
                if (!sqStack.isEmpty()){
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
    public int countNode(BiTreeNode t) {
        //使用递归算法
        int count = 0;
        if (t != null) {
            ++count;
            count += countNode(t.lchild);
            count += countNode(t.rchild);
        }
        return count;
    }

    //统计二叉树中结点个数的算法
    //使用递归的方式
    public int countNode2(BiTreeNode t) {
        //使用递归算法
        if (t != null) {
            return 0;
        } else {
            return 1 + countNode2(t.lchild) + countNode2(t.rchild);
        }
    }

    //统计二叉树中结点个数的算法
    //使用非递归的方式
    public int countNode1(BiTreeNode t) {
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

    public int getDepth(BiTreeNode t) {
        if (t != null) {
            int lDepth = getDepth(t.lchild);
            int rDepth = getDepth(t.rchild);
            return 1 + (lDepth > rDepth ? lDepth : rDepth);
        }
        return 0;
    }


    public int getDepth1(BiTreeNode t) {
        if (t == null) {
            return 0;
        } else if (t.rchild == null && t.lchild == null) {
            return 1;
        } else {
            return 1 + (getDepth1(t.lchild) > getDepth1(t.rchild) ? getDepth1(t.lchild) : getDepth1(t.rchild));
        }
    }

    public boolean isEqual(BiTreeNode t1, BiTreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        //当两个节点都不为空时
        if (t1 != null && t2 != null) {
            if (t1.data.equals(t2.data)) {
                //左子树是否相等
                if (isEqual(t1.lchild, t2.lchild)) {
                    //右子树是否相等
                    if (isEqual(t1.rchild, t2.rchild)) {
                        return true;
                    }
                }
            }
        }
        //当剩下只有一个不为空，则返回false
        return false;
    }

    public boolean isEqual1(BiTreeNode t1, BiTreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        //当两个节点都不为空时
        else if (t1 != null && t2 != null) {
            //必须满足三个条件
            //1。两个结点的值相等
            //2。左子树相等
            //3。右子树相等
            return (t1.data.equals(t2.data) && (isEqual1(t1.lchild, t2.lchild)) && (isEqual1(t1.rchild, t2.rchild)));
        }
        //当剩下只有一个不为空，则返回false
        return false;
    }

    public void levelTraverse() {
        //层次历遍操作实现的非递归算法
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


}
