import java.util.ArrayList;
import java.util.List;

public class Exp28 {
    //二分查找
    public static boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null) {
            return false;
        }
        if (matrix.length == 0) {
            return false;
        }
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int row = search1(matrix, target, 0, m);
        return search2(matrix, target, 0, n, row);

    }

    public static boolean search2(int[][] matrix, int target, int begin, int end, int row) {
        if (begin <= end) {
            int mid = (begin + end) / 2;
            if (matrix[row][mid] < target) {
                return search2(matrix, target, mid + 1, end, row);
            } else if (matrix[row][mid] > target) {
                return search2(matrix, target, begin, mid - 1, row);
            } else {
                return true;
            }
        }
        return false;
    }

    public static int search1(int[][] matrix, int target, int begin, int end) {
        if (begin <= end) {
            int mid = (begin + end) / 2;
            if (matrix[mid][0] < target) {
                return search1(matrix, target, mid + 1, end);
            } else if (matrix[mid][0] > target) {
                return search1(matrix, target, begin, mid - 1);
            } else {
                return mid;
            }
        }
        if (begin == 0) {
            return begin;
        }
        return begin - 1;
    }

    public static void main(String[] args) {
        System.out.println("start:" + System.currentTimeMillis() / 1000);
        int[][] a = {{5}};
        System.out.println(searchMatrix(a, 2));
        System.out.println("end:" + System.currentTimeMillis() / 1000);
    }
}
