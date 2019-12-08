public class Exp2 {
//    public static long trailingZeros(long n) {
//        // write your code here, try to do it without arithmetic operators.
//        //len =11
//        long result[] = {0l,2l,24l,249l,2499l,24999l,249999l,249999l,249999l,2499999l,24999999l,249999999l,2499999999l,2499999999l};
//        //10-2
//        //100-24
//        //1000-249
//        //10000-2499
//        //100000-24999
//
//        long count = 0;
//        int len = 0;
//        while (n>0){
//            long last_num =n%10;
//            if(last_num==0){
//                count+= 0;
//            }
//            else if(last_num==1){
//                count+= result[len];
//            }
//            else if(last_num<=5){
//                count+=result[len]+(last_num-1)*(result[len]+1);
//            }
//            else{
//                count+=result[len]*2+(last_num-2)*(result[len]+1);
//            }
//            n/=10;
//            len++;
//            System.out.println("now is:"+count);
//        }
//
//        return count;
//    }

//    public static long trailingZeros(long n) {
//        long result[] = {0l,2l,24l,249l,2499l,24999l,249999l,249999l,249999l,2499999l,24999999l,249999999l,2499999999l,2499999999l};
//
//        long n_temp = n;
//        long count = 0;
//        int len = 0;
//        long param = 1l;
//
//        while (n>10){
//            param*=10;
//            n/=10;
//            len++;
//        }
//        long last_num =n*param;
//
//        if(n==1){
//            count+= result[len];
//        }
//        else if(n<=5){
//            count+=result[len]+(n-1)*(result[len]+1);
//        }
//        else{
//            count+=result[len]*2+(n-2)*(result[len]+1);
//        }
//        for(long i = last_num;i<=n_temp;i+=5){
//            long temp = i;
//            if(temp==last_num){
//                continue;
//            }
//            while (temp%5==0){
//                temp/=5;
//                count++;
//            }
//        }
//
//        return count;
//    }

    public static long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        int count = 0;
        for(long param = 0;param<=n;param+=5){
            long temp = param;
            if(temp==0){
                continue;
            }
            while (temp%5==0){
                temp/=5;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        long old = System.currentTimeMillis();
        System.out.println(trailingZeros(111111111l));

        long time = System.currentTimeMillis()-old;
        System.out.println("time is:"+time);
    }
}
