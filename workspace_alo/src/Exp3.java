public class Exp3 {
    public static int digitCounts(int k, int n) {
        int count = 0;
        if(k==0)
            count++;

        // write your code here

        for(int i = 0;i<=n;i++){
            int temp = i;
            while(temp>0){
                if(temp%10==k){
                    count++;
                }
                temp/=10;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(digitCounts(1,12));
    }
}
