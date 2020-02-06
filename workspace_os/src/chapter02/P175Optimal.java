package chapter02;

public class P175Optimal {
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
    public static int getMaxIndexOfNeed(int[] place,int[] block,int start){
        //最近需求定位
        int minBlockIndex = -1;
        int minPlaceIndex = -1;
        for(int PlaceIndex = 0;PlaceIndex<place.length;PlaceIndex++){
            for (int BlockIndex = start + 1; BlockIndex < block.length; BlockIndex++) {
                if (block[BlockIndex] == place[PlaceIndex]) {
                    if (minBlockIndex < BlockIndex) {
                        minBlockIndex = BlockIndex;
                        minPlaceIndex = PlaceIndex;
                    }
                    break;
                }
                //这操作是查找获取最大距离值的时，发现内存中的元素以后永久不使用的元素时候
                if(BlockIndex==block.length-1 && block[BlockIndex]!=place[PlaceIndex]){
                    return PlaceIndex;
                }
            }
        }
        return minPlaceIndex;
    }
    public static void main(String[] args) {
        int[] block = new int[]{7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int[] place = new int[]{-1, -1, -1};
        for (int index = 0; index < block.length; index++) {
            //假设元素存在则不需要进行任何操作
            if(paramExist(place,block[index])){
                continue;
            }else {
                int emptyIndex = existEmpty(place);
                //当前已经元素满了
                if(emptyIndex==-1){
                    int maxIndex = getMaxIndexOfNeed(place,block,index);
                    place[maxIndex] = block[index];
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
