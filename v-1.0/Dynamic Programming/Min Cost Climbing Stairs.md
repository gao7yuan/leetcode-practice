# 746. Min Cost Climbing Stairs
*Easy*
09/11/18

On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

Example 1:
```
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
```
Example 2:
```
Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
```
Note:
cost will have a length in the range [2, 1000].
Every cost[i] will be an integer in the range [0, 999].

## Attempts
* dp
  - dp[]记录到达某一级台阶需要的cost，到达i级需要的cost是i-1级加上cost[i]或者i-2级加上cost[i]
  - 最后return到达最后级或者倒数第二级中的较小值
  - O(n) time, O(n) space
```
public int minCostClimbingStairs(int[] cost) {
    int[] dp = new int[cost.length];
    dp[0] = cost[0];
    dp[1] = cost[1];
    for (int i = 2; i < cost.length; i++) {
        dp[i] = Math.min(dp[i - 2] + cost[i], dp[i - 1] + cost[i]);
    }
    return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
}
```
* 改进 O(1) space。每次iteration只需要前两个的值，不需要array
```
public int minCostClimbingStairs(int[] cost) {
    int dp1 = cost[0];
    int dp2 = cost[1];
    for (int i = 2; i < cost.length; i++) {
        int dp0 = Math.min(dp1 + cost[i], dp2 + cost[i]);
        dp1 = dp2;
        dp2 = dp0;
    }
    return Math.min(dp1, dp2);
}
```
