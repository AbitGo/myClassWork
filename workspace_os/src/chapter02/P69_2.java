package chapter02;

public class P69_2 {
    static int[] chopstick = {1,1,1,1,1};

    public static void main(String[] args) {
        InnerThread a0 = new InnerThread();
        a0.setName("0");
        a0.start();

        InnerThread a1 = new InnerThread();
        a1.setName("1");
        a1.start();

        InnerThread a2 = new InnerThread();
        a2.setName("2");
        a2.start();

        InnerThread a3 = new InnerThread();
        a3.setName("3");
        a3.start();

        InnerThread a4 = new InnerThread();
        a4.setName("4");
        a4.start();
    }

    static class InnerThread extends Thread{
        @Override
        public synchronized void run() {
            while (true){
                int index = Integer.parseInt(this.getName());
                if(chopstick[index]==1){
                    chopstick[index]=0;
                    System.out.println("哲学家"+this.getName()+"获得左筷子"+index+"序号为");
                }else{
                    //否则睡眠
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(chopstick[((index+1)%chopstick.length)]==1){
                    chopstick[((index+1)%chopstick.length)]=0;
                    System.out.println("哲学家"+this.getName()+"获得右筷子"+index+"序号为");

                    System.out.println("哲学家"+this.getName()+"吃饭");
                    try {
                        Thread.sleep(1000);
                        //进行赋值
                        chopstick[index]=1;
                        chopstick[((index+1)%chopstick.length)]=1;
                        System.out.println("哲学家"+this.getName()+"思考");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    //否则睡眠
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
