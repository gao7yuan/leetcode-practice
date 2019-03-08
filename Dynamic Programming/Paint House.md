# 256. Paint House
3/7/19
*Easy*

There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:
```
Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
             Minimum cost: 2 + 5 + 3 = 10.
```

## Attempts
- dp[i, 0] dp[i, 1] dp[i, 2] - min cost of painting ith house as red, blue and green
- O(n) time O(n) space
```Java
public int minCost(int[][] costs) {
    int n = costs.length;
    if (n == 0) {
        return 0;
    }
    int[][] dp = new int[n + 1][3];
    for (int j = 0; j < 3; j++) {
        dp[0][j] = 0;
    }
    for (int i = 1; i <= n; i++) {
        dp[i][0] = Math.min(dp[i - 1][1] + costs[i - 1][0], dp[i - 1][2] + costs[i - 1][0]);
        dp[i][1] = Math.min(dp[i - 1][0] + costs[i - 1][1], dp[i - 1][2] + costs[i - 1][1]);
        dp[i][2] = Math.min(dp[i - 1][1] + costs[i - 1][2], dp[i - 1][0] + costs[i - 1][2]);
    }
    return Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]);
}
```

- other code
```Java
public int minCost(int[][] C) {
  int n = C.length;
  if (n == 0) {
    return 0;
  }
  int[][] f = new int[n + 1][3];
  f[0][0] = f[0][1] = f[0][2] = 0;
  int i, j, k;
  // first i houses
  for (i = 1; i <= n; i++) {
    // house i - 1's color is j
    for (j = 0; j < 3; j++) {
      f[i][j] = Integer.MAX_VALUE;

      // house i - 2's color is k
      for (k = 0; k < 3; k++) {
        if (j == k) {
          continue;
        }
        f[i][j] = Math.min(f[i][j], f[i - 1][k] + C[i - 1][j]);
      }
    }
  }
  return Math.min(f[n][0], Math.min(f[n][1], f[n][2]));
}
```
