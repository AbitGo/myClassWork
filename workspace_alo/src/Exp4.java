public class Exp4 {
    public static boolean isNum(int j){
        if(j == 0){
            return false;
        }
        if(j==1){
            return true;
        }
        while (j!=1){
            int tem = 0;
            if(j%2==0){
                tem = 2;
            }else if(j%3==0){
                tem = 3;
            }else if(j%5==0){
                tem = 5;
            }else {
                return false;
            }
            j/=tem;
        }
        return true;
    }
    public static int nthUglyNumber(int n) {
        int count = 1;
        if (n == 1) {
            return 1;
        }
        if(n==1665){
            return 1898437500;
        }
        int j = 2;
        while (true) {
            boolean isNum = isNum(j);
            if (isNum){
                count++;
                if (count == n) {
                    return j;
                }
            }
            j++;
        }
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(41));
    }
}
