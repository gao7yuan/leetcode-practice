# 300. Longest Increasing Subsequence
*Medium* *要看O(nlgn)解法*
11/9/18

Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
```
Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
```
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?

## Solutions
### 1. Brute force
* 把每个可能的递增序列求出来，比较
* 对于每一个数有两种选择(加入序列或不加入)，一共有2^n个可能序列
* O(2^n) time
```
public int lengthOfLIS(int[] nums) {
    return lengthofLIS(nums, Integer.MIN_VALUE, 0);
}
```
* `prev`:序列中上一个数。`curpos`:现在正在看的数
* 对于每一个数`curpos`:如果大于`prev`则有两种选择：加入序列或者不加入。否则只能不加入序列。
* 初始条件：`prev`是负无穷大。`curpos`是0。
* 终止条件：`curpos`超出index。
```
public int lengthofLIS(int[] nums, int prev, int curpos) {
    if (curpos == nums.length) {
        return 0;
    }
    int taken = 0;
    if (nums[curpos] > prev) {
        taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
    }
    int nottaken = lengthofLIS(nums, prev, curpos + 1);
    return Math.max(taken, nottaken);
}
```
### 2. Recursion with memo
* memo[i][j]: length of LIS using nums[i] as the previous element considered to be included/not included in LIS, with nums[j] as the current element considered to be included/not included in LIS
* O(n^2) time, O(n^2) space
```
public int lengthOfLIS(int[] nums) {
    int memo[][] = new int[nums.length + 1][nums.length];
    for (int[] l : memo) {
        Arrays.fill(l, -1);
    }
    return lengthofLIS(nums, -1, 0, memo);
}
```

```
public int lengthofLIS(int[] nums, int previndex, int curpos, int[][] memo) {
    if (curpos == nums.length) {
        return 0;
    }
    if (memo[previndex + 1][curpos] >= 0) {
        return memo[previndex + 1][curpos];
    }
    int taken = 0;
    if (previndex < 0 || nums[curpos] > nums[previndex]) {
        taken = 1 + lengthofLIS(nums, curpos, curpos + 1, memo);
    }

    int nottaken = lengthofLIS(nums, previndex, curpos + 1, memo);
    memo[previndex + 1][curpos] = Math.max(taken, nottaken);
    return memo[previndex + 1][curpos];
}
```
### 3. DP
* 从第一个元素开始，每次查看第k个元素，直到结尾。
* dp[]表示从第一个元素到第k个元素最长LIS的长度。
* 对于第k个元素，如果存在1<=i<k，nums[i] < nums[k]则更新dp[k] = max(dp[k], dp[i] + 1)
* 最后返回dp[]中最大元素
```
public int lengthOfLIS(int[] nums) {
    int len = nums.length;
    if (len == 0) {
        return 0;
    }
    int[] dp = new int[len];
    for (int i = 0; i < len; i++) {
        dp[i] = 1;
    }
    for (int i = 1; i < len; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    Arrays.sort(dp);
    return dp[len - 1];
}
```
