# 518. Coin Change 2
1/9/19
*Medium*

You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.



Example 1:
```
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
```
Example 2:
```
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
```
Example 3:
```
Input: amount = 10, coins = [10]
Output: 1
```

## Solutions
* DP, iterative, bottom up
  - count[amount + 1] - the number of ways to get to certain amount. count[0] = 1
  - for each coin value, go from i = 1 to amount. if i >= coin, count[i] = count[i] + count[i - coin]
  - O(amount * coin) time, O(amount) space
```Java
public int change(int amount, int[] coins) {
    int[] count = new int[amount + 1];
    count[0] = 1;
    for (int coin : coins) {
        for (int i = 1; i <= amount; i++) {
            if (i >= coin) {
                count[i] += count[i - coin];
            }
        }
    }
    return count[amount];
}
```

* DP, knapsack problem, 2D
  - dp[i][j] - to get amount j, only use the first i coins
  - state transition:
    - if not using ith coin, dp[i-1][j]
    - if using ith coin, dp[i][j-coin[i-1]]
  - O(amount * coin) time, O(amount * coin) space
```Java
public int change(int amount, int[] coins) {
    int[][] dp = new int[coins.length + 1][amount + 1];
    for (int i = 0; i <= coins.length; i++) {
        dp[i][0] = 1;
    }
    for (int i = 1; i <= coins.length; i++) {
        for (int j = 1; j <= amount; j++) {
            dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
        }
    }
    return dp[coins.length][amount];
}
```
