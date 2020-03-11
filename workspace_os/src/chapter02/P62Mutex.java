package chapter02;

public class P62Mutex {
    //mutex为1的时候:没有线程进入临界区
    //mutex为0的时候:有一个线程进入临界区
    //mutex为-1的时候:即另外一个线程进入临界区，但是需要等待
    private static int mutex = 1;

    public static void main(String[] args) {
        InnerThread thead1 = new InnerThread();
        thead1.setName("线程1");
        thead1.start();

        InnerThread thead2 = new InnerThread();
        thead2.setName("线程2");
        thead2.start();
    }

    static class InnerThread extends Thread {

        @Override
        public void run() {
            while (true) {

                if (mutex >=0) {
                    //wait操作
                    mutex--;
                    //临界区
                    try {
                        System.out.println(this.getName() + "已经进入线程中");
                        Thread.sleep(10);
                        //signal操作
                        mutex++;
                        System.out.println(this.getName() + "完成操作，释放信号量");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        System.out.println("当前已经有线程进入了临界区,"+this.getName()+"线程等待");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    //时间片流转
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
