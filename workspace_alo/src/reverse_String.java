public class reverse_String {

    public static void main(String[] args) {
        int[] a = {2,4,5};

        int len = (int)Math.pow(2,a.length);
        int[] b = {0,2,4,5,6,9,7,11};
        int count=0;

        //求幂阶{2,4,5}->{0,2,4,5,6,9,7,11}
//        for(int i = 1;i<len;i++){
//            b[i]=
//        }

        for(int i = 1;i<=100;i++){
            int param = i;
            for(int j = b.length-1;j>0;j--){
                if(param%b[j]!=0){
                    param%=b[j];
                }else {
                    break;
                }
                if((j==1 && param%b[1]!=0)&&(i%a[0]!=0 && i%a[1]!=0 && i%a[2]!=0)){
                    System.out.println(" i:"+i+" j:"+j+" param:"+param);
                    count++;
                }
            }
        }
        System.out.println("count:"+count);
    }

}
