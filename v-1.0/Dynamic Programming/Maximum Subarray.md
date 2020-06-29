# 53. Maximum Subarray
*Easy*
09/10/18

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
```
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
```
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

## Solutions
* dp - Optimization problems can be solved by dp
  - subproblem: 到i为止，以i结束的连续的数的和 - 用int[] dp记录，若前一个max sum为负则等于nums[i]，否则前一个max sum + nums[i]
  - max记录dp[]里所有值的最大值
```
public int maxSubArray(int[] nums) {
    int max = nums[0];
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
        dp[i] = dp[i - 1]> 0 ? dp[i - 1] + nums[i] : nums[i];
        max = max > dp[i] ? max : dp[i];
    }
    return max;  
}
```
