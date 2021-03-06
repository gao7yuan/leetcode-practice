# 265. Paint House II
3/8/19
*Hard*

There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:
```
Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
```
Follow up:
Could you solve it in O(nk) runtime?

## Attempts
- dp[i, j] = min cost to paint house 0-i with color j
- dp[i, j] = min(dp[i-1, k]) (k != j)
- O(nk) time, O(nk) space
```Java
public int minCostII(int[][] costs) {
    if (costs.length == 0 || costs[0].length == 0) {
        return 0;
    }
    int n = costs.length; // number of houses
    int k = costs[0].length; // number of colors
    int[][] dp = new int[n][k];
    // dp[i][j] = min cost to paint house i with color j
    // dp[i][j] = min(dp[i-1][k]) (k != j)

    // for each house
    for (int i = 0; i < n; i++) {
        // for each color of house i
        for (int j = 0; j < k; j++) {
            // 0th house
            if (i == 0) {
                dp[i][j] = costs[i][j];
                continue;
            }
            dp[i][j] = Integer.MAX_VALUE;
            // for each color of house i-1
            for (int x = 0; x < k; x++) {
                if (x != j) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][x] + costs[i][j]);
                }
            }
        }
    }
    int cost = Integer.MAX_VALUE;
    for (int j = 0; j < k; j++) {
        cost = Math.min(cost, dp[n - 1][j]);
    }
    return cost;
}
```

## Solution
- O(1) space
```Java
public int minCostII(int[][] costs) {
    if (costs == null || costs.length == 0) return 0;

    int n = costs.length, k = costs[0].length;
    // min1 is the index of the 1st-smallest cost till previous house
    // min2 is the index of the 2nd-smallest cost till previous house
    int min1 = -1, min2 = -1;

    for (int i = 0; i < n; i++) {
        int last1 = min1, last2 = min2;
        min1 = -1; min2 = -1;

        for (int j = 0; j < k; j++) {
            if (j != last1) {
                // current color j is different to last min1
                costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
            } else {
                costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
            }

            // find the indices of 1st and 2nd smallest cost of painting current house i
            if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                min2 = min1; min1 = j;
            } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                min2 = j;
            }
        }
    }
    return costs[n - 1][min1];
}
```
