# 62. Unique Paths
*Medium*
09/13/18

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:
```
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
```
Example 2:
```
Input: m = 7, n = 3
Output: 28
```

## Solutions
* dp
  - ```int[][] dp = new int[m][n]```记录到每一个格子的方法数
  - 如果到达的格子在边界上，则只有一种方法。如果不在边界上，则等于到达上面格子的方法数加上到达前面格子的方法数的和
  - O(n^2) time O(n^2) space
```
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (i == 0 || j == 0) {
                dp[i][j] = 1;
            } else {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
    }
    return dp[m - 1][n - 1];
}
```
