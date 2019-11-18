package com.company.ch3.queue;

import com.company.ch2.SequenceTable.SequenceTable;
import com.company.ch3.queue.LinkSqeue;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Exp35 {
    final static int maxSize = 10;

    public static void main(String[] args) throws Exception {
        //顺序表
        SequenceTable sequenceTable = new SequenceTable();
        sequenceTable.insert(0, 1);
        //队列
        LinkSqeue linkSqeue = new LinkSqeue();
        for (int i = 2; i <= maxSize; i++) {
            //插入十个元素
            linkSqeue.offer(i);
        }
        SequenceTable sequenceTable1 =inserRing(linkSqeue,sequenceTable,2,maxSize);
        if (sequenceTable1==null){
            System.out.println("null");
        }
        sequenceTable1.display();
    }


    public static SequenceTable inserRing(LinkSqeue sqeue, SequenceTable link, int m, int n) throws Exception {
        int count = 0;
        while (!sqeue.isEmpty() && count <=n - m) {
            //队列最后一个
            int p = (int) sqeue.poll();
            //线性表最后一个
            int q = (int) link.get(link.getLenght() - 1);
            //当是队列最后一个元素的时候
            if (m == n) {
                if (isPrime(p + q) && isPrime(p + 1)) {
                    link.insert(link.getLenght(),p);
                    return link;
                }else{
                    sqeue.offer(p);
                }
            }else if(isPrime(p+q)){
                //不是队列最后一个元素并且符合运算
                link.insert(link.getLenght(),p);
                if(inserRing(sqeue,link,m+1,n)!=null){
                    return link;
                }
                link.remove(link.getLenght()-1);
                sqeue.offer(p);
            }else{
                sqeue.offer(p);
            }
            ++count;
        }
        return null;
    }

    public static boolean isPrime(int sum) {
        for (int i = 2; i < sum; i++) {
            if (sum % i == 0) {
                return false;
            }
        }
        return true;
    }
}
