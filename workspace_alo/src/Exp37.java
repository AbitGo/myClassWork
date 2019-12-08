public class Exp37 {
    public static int reverseInteger(int number) {
        // write your code here
        int result = number%10;
        number/=10;
        while (number>0){
            result*=10;
            result+=number%10;
            number/=10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(reverseInteger(123));
    }
}
