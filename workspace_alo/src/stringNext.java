public class stringNext {
    public static void initNextArray(String p){
        int[] next = new int[p.length()];
        int k = -1;
        int j = 0;
        next[0] = -1;
        while(j < p.length()-1){
            if(k == -1 || p.charAt(k) == p.charAt(j))
                next[++j] = ++k;
            else
                k = next[k];
        }
        for(int i = 0;i<next.length;i++){
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args) {
        initNextArray("ababaaababaa");
    }

}
