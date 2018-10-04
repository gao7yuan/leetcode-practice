# 28. Search a 2D Matrix
*Easy*
10/03/18

Write an efficient algorithm that searches for a value in an m x n matrix.

This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example

Consider the following matrix:

[
    [1, 3, 5, 7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
]
Given target = 3, return true.

Challenge

O(log(n) + log(m)) time

* 先确定行，再确定元素
```
public class Solution {
    /**
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;

        int start = 0;
        int end = matrix.length - 1;
        int mid = start + (end - start) / 2;
        int rowsize = matrix[0].length;

        while (start + 1 < end
        && (matrix[mid][0] > target || matrix[mid][rowsize - 1] < target)) {
            mid = start + (end - start) / 2;
            if (matrix[mid][0] > target) {
                end = mid;
            } else if (matrix[mid][rowsize - 1] < target) {
                start = mid;
            }
        }

        if (matrix[start][0] > target || matrix[end][rowsize - 1] < target) return false;

        int row;
        if (matrix[start][rowsize - 1] >= target) row = start;
        else if (matrix[end][0] <= target) row = end;
        else row = mid;

        start = 0;
        end = rowsize - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (matrix[row][start] == target || matrix[row][end] == target) return true; // 不要忘记这个操作。。。

        return false;
    }
}
```
* 全局考虑
```
public class Solution {
    /**
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rownum = matrix.length;
        int colnum = matrix[0].length;
        int start = 0;
        int end = colnum * rownum - 1;
        int mid, row, col, num;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            row = mid / colnum;
            col = mid % colnum;
            num = matrix[row][col];
            if (num == target) {
                return true;
            } else if (num > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (matrix[start / colnum][start % colnum] == target
        || matrix[end / colnum][end % colnum] == target) {
            return true;
        }

        return false;
    }
}
```
