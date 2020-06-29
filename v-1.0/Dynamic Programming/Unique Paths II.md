# 63. Unique Paths II
*Medium*
09/13/18

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:
```
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
```

## Attempts
* dp similar as 62. Unique Paths
  - int[][] dp to record paths to a grid
  - 如果obstacleGrid为1则dp为0，如果在边界上且前面有obstacle则边界上的paths都是0
  - O(n^2) time O(n^2) space
```
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
            else if (i == 0 && j > 0 && dp[i][j - 1] == 0
                     || j == 0 && i > 0 && dp[i - 1][j] == 0)
                dp[i][j] = 0;
            else if (i == 0 || j == 0) dp[i][j] = 1;
            else dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
        }
    }
    return dp[m - 1][n - 1];
}
```

## Solutions
* dp思路类似但是只要一个1D array
  - O(n^2) time O(n) space
```
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int width = obstacleGrid[0].length;
    int[] dp = new int[width];
    dp[0] = 1; // 到达第一列每一格的方法默认为1，除非某一格有obstacle之后都是0.
    for (int[] row : obstacleGrid) {
        for (int j = 0; j < width; j++) {
            if (row[j] == 1)
                dp[j] = 0;
            else if (j > 0)
                dp[j] += dp[j - 1]; //如果某一格没有障碍，到达这一格的方法数为上一格加前一格的方法数和
        }
    }
    return dp[width - 1];
}
```
