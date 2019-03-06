# 55. Jump Game
3/5/19
*Medium*

## NOTE
- 可以用贪心算法

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:
```
Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
```
Example 2:
```
Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
```

## Attempts
- DP
  - f[i]=if you can reach index i
  - f[i]=OR_0<=j<i(f[j] AND i-j<=nums[j])
    - you can reach j and you can jump from j to i
  - O(n^2) time, O(n) space
```Java
public boolean canJump(int[] nums) {
    boolean[] dp = new boolean[nums.length];
    dp[0] = true;
    for (int i = 1; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && i - j <= nums[j]) {
                dp[i] = true;
                continue;
            }
        }
    }
    return dp[nums.length - 1];
}
```

- DP recursion with memo
```Java
public boolean canJump(int[] nums) {
    int[] memo = new int[nums.length];
    Arrays.fill(memo, -1);
    int canJump = canJump(nums, nums.length - 1, memo);
    return memo[nums.length - 1] == 1 ? true : false;
}

private int canJump(int[] nums, int dest, int[] memo) {
    if (dest == 0) {
        memo[dest] = 1;
        return 1;
    }
    if (memo[dest] != -1) {
        return memo[dest];
    }
    for (int i = 0; i < dest; i++) {
        if (canJump(nums, i, memo) == 1 && dest - i <= nums[i]) {
            memo[dest] = 1;
            return 1;
        }
    }
    memo[dest] = 0;
    return 0;
}
```
