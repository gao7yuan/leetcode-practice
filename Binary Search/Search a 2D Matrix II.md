# 240. Search a 2D Matrix II
*Medium*
10/12/18

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:
```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```
Given target = 5, return true.

Given target = 20, return false.

## Attempts
* Search space reduction
  - O(m+n) time, O(1) space
```
public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    int rowCount = matrix.length;
    int colCount = matrix[0].length;
    int row = rowCount - 1;
    int col = 0;
    int cur;
    while (row >= 0 && col < colCount) {
        cur = matrix[row][col];
        if (cur == target) {
            return true;
        } else if (cur < target) {
            col++;
        } else {
            row--;
        }
    }
    return false;
}
```

## Solutions
### 1. Brute force
* O(mn) time, O(1) space
### 2. Binary search
* 按照对角线，从0到较短dimension的最大值，纵横方向分别binary search
* 每次O(lgk)
* 总体O(lg(n!)) time, O(1) space
### 3. Divide and conquer
* O(nlgn) time, O(lgn) space
### 4. 我的方法~
