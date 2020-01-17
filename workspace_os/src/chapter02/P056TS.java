package chapter02;

import java.util.Currency;

//the class is the operation system's code
public class P056TS {
    private static int buffer;
    private static boolean lock;

    public static void main(String[] args) {
        lock = false;
        buffer = 0;
        Thread prov = new ProvThreadClass();
        prov.start();

        Thread cust = new CustThreadClass();
        cust.start();
    }

    public static boolean TS(){
        boolean old = lock;
        lock = true;
        return old;
    }
    static class ProvThreadClass extends Thread{
        @Override
        public void run() {
            while(true){
                //缓冲池满的话
                if(buffer==10){

                }
                else{
                    while (TS());
                    buffer+=1;
                    System.out.println("生产出1件产品，目前剩余产品个数:"+buffer);
                    lock = false;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class CustThreadClass extends Thread{
        @Override
        public void run() {
            while(true){
                //缓冲池空的话
                if(buffer==0){

                }
                else{
                    while (TS());
                    buffer-=1;
                    System.out.println("消费1件产品，目前剩余产品个数:"+buffer);
                    lock = false;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
