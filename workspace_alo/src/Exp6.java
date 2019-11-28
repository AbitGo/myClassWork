public class Exp6 {

    public static int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int result[] = new int[A.length+B.length];
        int i_A= 0;
        int i_B=0;
        int temp=0;
        while (i_A<A.length&&i_B<B.length){
            if (A[i_A]<B[i_B]){
                result[temp]=A[i_A];
                temp++;
                i_A++;
            }else{
                result[temp]=B[i_B];
                temp++;
                i_B++;
            }
            System.out.println("temp:"+result[temp-1]);
        }
        while (i_A<A.length){
            result[temp]=A[i_A];
            temp++;
            i_A++;
        }
        while (i_B<B.length){
            result[temp]=B[i_B];
            temp++;
            i_B++;
        }
        return result;
    }
    public static void main(String[] args){
        int[] a = {1,2,3,4,5};
        int[] b = {2,3,4,5};
        int[] result = mergeSortedArray(a,b);
        for(int x:result){
            System.out.println(x);
        }
    }
}
