# 70. Climbing Stairs
*Easy*
09/11/18

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:
```
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
```
Example 2:
```
Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
```

## Solution
### Approach 1: Brute Force
* 从第i阶到第n阶的方法一共有多少种：```climb_stairs(i,n)```
  - climb_stais(i, n) = climb_stairs(i+1, n) + climb_stairs(i+2, n)
  - O(2^n) time.recursion tree每增加一层，problem size扩大一倍。
  - O(n) space.recursion tree最多n层
  - redundantly calculating each step
```
public int climbStairs(int n) {
    climb_Stairs(0, n);
}
public int climb_Stairs(int i, int n) {
    if (i > n) {
        return 0;
    }
    if (i == n) {
        return 1;
    }
    return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
}
```
### Approach 2: Recursion with memorization
* 和第一种方法思路类似，用一个memo array记录每一阶到最终阶的方法总和，这样不需要重复计算
  - O(n) time O(n) space
```
public int climbStairs(int n) {
    int memo[] = new int[n + 1];
    return climb_Stairs(0, n, memo);
}
public int climb_Stairs(int i, int n, int memo[]) {
    if (i > n) {
        return 0;
    }
    if (i == n) {
        return 1;
    }
    if (memo[i] > 0) {
        return memo[i]; // 为什么？？？？？
    }
    memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
    return memo[i];
}
```
### Approach 3: Dynamic Programming
* 到达第i阶有两种办法：从i-1走一步过来，或者从i-2走两步过来。因此到达第i阶的方法数是到达第i-2和i-1阶的方法数量总和。
  - O(n) time O(n) space
```
public int climbStairs(int n) {
     if (n == 1) {
         return 1;
     }
     int[] dp = new int[n + 1];
     dp[1] = 1;
     dp[2] = 2;
     for (int i = 3; i <= n; i++) {
         dp[i] = dp[i - 1] + dp[i - 2];
     }
     return dp[n];
 }
```
### Approach 4 & 5 Fibonacci Formula
