public class exp_lanqiao {
    //题目是这样的，一个数组中，计算相邻节点连续递增最多的数量
    public static int getMaxContinuousCount(int[] param) {
        int max = -1;
        int count = 1;
        for (int index = 1; index < param.length; index++) {
            if (param[index] > param[index - 1]) {
                //System.out.println("param[index]: "+param[index]+" param[index-1]: "+param[index-1]);
                count++;
            } else {
                if (max < count) {
                    max = count;
                }
                count = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] param1 = {1, 5, 2, 3, 4, 1};
        int[] param2 = {10, 7, 2, 5, 6, 8, 3};
        int[] param3 = {7, 8, 10, 1, 2, 3, 4, 3, 1, 0};
        System.out.println(getMaxContinuousCount(param1));
        System.out.println(getMaxContinuousCount(param2));
        System.out.println(getMaxContinuousCount(param3));
    }
}
