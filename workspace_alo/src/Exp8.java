public class Exp8 {
    public static void rotateString(char[] str, int offset) {
        // write your code here

        int len = str.length;
        if(len==0){
            System.out.print("");
            return;
        }
        offset %=len;
        char[] result = new char[len];
        int i;

        for(i=0;i<offset;i++){
            int index = (len-offset+i)%len;
            result[i]=str[index];
        }
        for(int j=0;i<len;i++){
            result[i]=str[j++];
        }
        for(int k=0;k<len;k++){
            str[k]=result[k];
        }
        System.out.print(result);
    }

    public static void main(String[] args) {
        String abc = new String("");
        char[] chars = abc.toCharArray();

        rotateString(chars, 10);
    }
}
