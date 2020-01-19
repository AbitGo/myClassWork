package chapter02;

import java.util.List;
import java.util.Queue;


public class P058RecSignal {
    //系统中某类资源的数目，又称资源信号量
    private static int value;

    public static void main(String[] args) {
        value = 2;
        InnerCustThread a = new InnerCustThread();
        a.setName("进程A");
        InnerCustThread b = new InnerCustThread();
        b.setName("进程B");
        InnerCustThread c = new InnerCustThread();
        c.setName("进程C");

        a.start();
        b.start();
        c.start();

    }

    static class InnerCustThread extends Thread{
        @Override
        public void run() {
            while (true){
                //如果当前资源大于0
                if(value>0){
                    //已经获取一个资源
                    value--;
                    System.out.println("当前"+this.getName()+"已经获取了一个资源，当前value:"+value);
                    //进行资源使用1s之后则进行资源得自动释放
                    try {
                        sleep(3000);
                        value++;
                        System.out.println("当前"+this.getName()+"已经释放了一个资源，当前value:"+value);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    //负责没有资源的时候，则等待资源释放
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
