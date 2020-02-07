package chapter02;
public class P176LRU {
    //查找元素是否在数组中存在
    public static int paramExist(int[] place,int param){
        for (int i = 0; i < place.length; i++) {
            if(place[i]==param)
                return i;
        }
        //不为空
        return -1;
    }

    //查找元素是否在数组中存在
    public static void ChangePlaceCount(int[] placeIndex,int NotNeedIndex){
        for (int i = 0; i < placeIndex.length; i++) {
            if(i==NotNeedIndex)
                placeIndex[i]=0;
            else
                placeIndex[i]++;
        }
    }

    //获取最大距离值
    public static int getMaxIndexOfCount(int[] place,int[] placeCount){
        int maxCount = placeCount[0];
        int maxIndex = 0;
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
        int max = block.length;
        int[] place = new int[]{-1,-1, -1};
        int[] placeCount = new int[]{max,max,max};
        for (int index = 0; index < block.length; index++) {
            //假设元素存在则不需要进行任何操作
            int exitsIndex = paramExist(place,block[index]);
            if(exitsIndex!=-1){
                //当前existIndex需要清零，其他的需要进行++
                ChangePlaceCount(placeCount,exitsIndex);
                continue;
            }
            //元素不存在,即为-1
            else {
                int maxIndex = getMaxIndexOfCount(place,placeCount);
                place[maxIndex] = block[index];
                ChangePlaceCount(placeCount,maxIndex);
                for (int param : place) {
                    System.out.print(param + " ");
                }
                System.out.println();
            }
        }
    }
}
