package chapter02;

//the class is the operation system's code
public class P057Swap {
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

    public static boolean swap(boolean param){
        boolean temp = lock;
        lock = param;
        param = temp;
        //将替换的param参数回调回去
        return param;
    }
    static class ProvThreadClass extends Thread{
        @Override
        public void run() {
            while(true){
                //缓冲池满的话
                if(buffer==10){

                }
                else{
                    boolean key = true;
                    do{
                        //将key值兑换，除非当前的lock为false的时候就可以跳出循环
                        key = swap(key);
                    }while (key!=false);
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
                    boolean key = true;
                    do{
                        //将key值兑换，除非当前的lock为false的时候就可以跳出循环
                        key = swap(key);
                    }while (key!=false);
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
