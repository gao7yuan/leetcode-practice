# 48. Rotate Image
11/27/18
*Medium*

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:
```
Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
```

Example 2:
```
Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```

## Attempts
* rotate前后index的关系: i, j -> j, n - i - 1 (n是matrix维度)
* 写一个helper rotate四个相关的点
* main method nested for loops注意边界。n为奇数时尤其Tricky

```Java
public void rotate(int[][] matrix) {
    int n = matrix.length;
    int m = n % 2 == 0 ? n / 2 : (n / 2 + 1);
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n / 2; j++) {
            rotatePoint(matrix, i, j);
        }
    }
}

private void rotatePoint(int[][] matrix, int i, int j) {
    int[] temp = new int[4];
    int n = matrix.length;
    int r = i;
    int c = j;
    for (int k = 0; k < 4; k++) {
        temp[k] = matrix[r][c];
        if (k > 0) {
            matrix[r][c] = temp[k - 1];
        }
        int tmp = r;
        r = c;
        c = n - tmp - 1;
    }
    matrix[i][j] = temp[3];
}
```

## Solutions
* 更简单清晰，群论思想：C4 = 中心对称 x 水平轴对称
* i, j -> j, i -> j, n - i - 1
```Java
public void rotate(int[][] matrix) {
    int n = matrix.length;
    // i,j -> j,i
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
        }
    }

    // j,i -> j,n-i-1
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n / 2; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[i][n - j - 1];
            matrix[i][n - j - 1] = temp;
        }
    }
}
```
