package chapter02;

public class P175Optimal {
    public static void main(String[] args) {
        int[] block = new int[]{7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int[] place = new int[]{-1, -1, -1};
        for (int index = 0; index < block.length; index++) {
            for(int i = 0;i<place.length;i++){
                //最近需求定位
                int minNeedIndexK = -1;
                int minNeedIndexI = -1;
                //当且仅当，当前所需页面不为空，并且所需页面不再页面中时候。
                if(place[i]!=-1 && place[i]!=block[index]){
                    for(int k = i+1;k<block.length;k++){
                        if(block[k]==place[i]){
                            if(minNeedIndexK<k){
                                minNeedIndexK = k;
                                minNeedIndexI = i;
                            }
                        }
                    }
                    place[minNeedIndexI] = block[index];

                }else {
                    //空内存

                    place[i]=block[index];
                    for(int param:place){
                        System.out.print(param+" ");
                    }
                    System.out.println();
                }
            }
        }
    }
}
