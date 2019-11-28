public class Solution {

    static int param_totle[] = new int[10];
    static int param_special[] = new int[10];

    public static void param_totle_num() {
        param_totle[0] = 2;
        int PTP=1;
        for (int i = 1; i < 10; i++) {
            PTP *=10;
            int x = i;
            param_totle[i] = (i+1)*PTP+1;
        }
        param_special[0]=0;
        for(int i = 1;i<10;i++){
            param_special[i]=param_totle[i]-9*param_totle[i-1]+7;
        }
    }

//    public static int countDigitOne(int n) {
//        param_totle_num();
//        int count = 0;
//
//        //将数字分解
//        //3333=3000+300+33
//        //为什么使用33，而不是30+3，是因为我懒得写了
//        for (int i = 0; i <= n%100; i++) {
//            int temp = i;
//            while (temp > 0) {
//                if (temp % 10 == 1)
//                    count++;
//                temp /= 10;
//            }
//        }
//
//        n/=100;
//        //数组对应的索引
//        int index = 2;
//        while (n>0){
//            int temp = n%10;
//            int addNum = 0;
//            if(temp==9){
//                addNum=param_totle[index-1]*2+param_special[index]+(param_totle[index-1]-1)*6;
//            }else if(temp==1){
//                addNum=param_totle[index-1];
//            }else if(temp==2){
//                addNum=param_totle[index-1]+param_special[index];
//            }else if(temp==0){
//                addNum=0;
//            }else{//temp=3,4,5,6,7,8
//                addNum=param_totle[index-1]+param_special[index]+(temp-2)*(param_totle[index-1]-1);
//            }
//            count+=addNum;
//            System.out.println("n: "+n+" temp: "+temp+" addNum: "+addNum);
//            n/=10;
//            index++;
//        }
//        return count;
//
//    }

    public static int countDigitOne(int n) {
        param_totle_num();
        int count = 0;
        //数组对应的索引
        int index = 0;

        //临时变量
        int temp_n = n/10;
        int temp_head = 0;
        int startNum = 1;
        while (temp_n>0){
            temp_head = temp_n%10;
            temp_n/=10;
            index++;
            startNum*=10;
        }
        startNum*=temp_head;

        if(temp_head==9){
            count=param_totle[index-1]*2+param_special[index]+(param_totle[index-1]-1)*6;
        }else if(temp_head==1){
            count=param_totle[index-1];
        }else if(temp_head==2){
            count=param_totle[index-1]+param_special[index];
        }else if(temp_head==0){
            count=0;
        }else{//temp=3,4,5,6,7,8
            count=param_totle[index-1]+param_special[index]+(temp_head-2)*(param_totle[index-1]-1);
        }
        System.out.println("count: "+count+" startNum: "+startNum);

        //3001-3333
        for (int i = startNum+1; i <= n; i++) {
            int temp = i;
            while (temp > 0) {
                if (temp % 10 == 1)
                    count++;
                temp /= 10;
            }
        }
        return count;

    }


    public static void main(String[] args) {
        System.out.println("start:" + System.currentTimeMillis() / 1000);
        System.out.println("count:" + countDigitOne(328524377));

        //328524377
        //370170378

        //110208530
        //97620675
        System.out.println("end:" + System.currentTimeMillis() / 1000);
    }
}
