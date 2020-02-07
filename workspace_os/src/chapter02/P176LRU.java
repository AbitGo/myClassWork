package chapter02;
public class P176LRU {
    //查找数组中是否存在并且未存储元素的索引
    public static int existEmpty(int[] place){
        for (int i = 0; i < place.length; i++) {
            if(place[i]==-1)
                return i;
        }
        //不为空
        return -1;
    }

    //查找元素是否在数组中存在
    public static boolean paramExist(int[] place,int param){
        for (int i = 0; i < place.length; i++) {
            if(place[i]==param)
                return true;
        }
        //不为空
        return false;
    }

    //获取最大距离值
    public static int getMaxIndexOfNeed(int[] place,int[] placeCount){
        int maxCount = -1;
        int maxIndex = -1;
        for(int i = 0;i<placeCount.length;i++){
            if(placeCount[i]>maxCount){
                maxCount = placeCount[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public static void main(String[] args) {
        int[] block = new int[]{7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int[] place = new int[]{-1, -1, -1};
        int[] placeCount = new int[]{0,0,0};
        for (int index = 0; index < block.length; index++) {
            //假设元素存在则不需要进行任何操作
            //并且需要进行使用次数的增加
            if(paramExist(place,block[index])){
                //使用次数的增加
                for(int i = 0;i<placeCount.length;i++){
                    placeCount[i]++;
                }
                continue;
            }else {
                int emptyIndex = existEmpty(place);
                //当前已经元素满了
                if(emptyIndex==-1){
                    int maxIndex = getMaxIndexOfNeed(place,placeCount);
                    place[maxIndex] = block[index];
                    //使用次数清零
                    placeCount[maxIndex] = 0;
                    for (int param : place) {
                        System.out.print(param + " ");
                    }
                    System.out.println();
                }else{
                    place[emptyIndex] = block[index];

                }
            }
        }
    }
}
