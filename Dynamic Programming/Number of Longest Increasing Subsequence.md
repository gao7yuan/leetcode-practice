# 673. Number of Longest Increasing Subsequence
3/8/19
*Medium*

Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
```
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
```
Example 2:
```
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
```
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.

## Attempts
- similar as LIS, but use a `count` and `maxLen` to keep track of maximum length and count
- however, it is a wrong solution...
- the reason is that for LIS problem, dp[n - 1] is not the answer. you have to use a max variable to track the maximum. therefore, we actually have to record the ways to get to each length of LIS in case it is needed in the future
```Java
public int findNumberOfLIS(int[] nums) {
    if (nums.length == 0) {
        return 0;
    }
    int n = nums.length;
    int[] dp = new int[n]; // length of LIS ending at i
    int count = 0;
    int maxLen = 1; // keep track of max length of LIS so far
    for (int i = 0; i < n; i++) {
        dp[i] = 1;
        if (maxLen == 1) {
            count++;
        }
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                int newLen = dp[j] + 1;
                dp[i] = Math.max(dp[i], newLen);
                if (newLen == maxLen) {
                    count++;
                } else if (newLen > maxLen) {
                    count = 1;
                    maxLen = newLen;
                }
            }
        }
    }
    return count;
}
```

## Solution
- for sequences ending at nums[i], length[i] - length of LIS, count[i] - count of such sequences with that length ending at nums[i]
  - O(n^2) time, O(n) space
```Java
public int findNumberOfLIS(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
        return n;
    }
    int[] length = new int[n];
    int[] count = new int[n];
    int max = 0;
    int num = 0;
    for (int i = 0; i < n; i++) {
        // init, only one char
        length[i] = 1;
        count[i] = 1;
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                int len = length[j] + 1;
                if (len > length[i]) {
                    length[i] = len;
                    count[i] = count[j];
                } else if (len == length[i]) {
                    count[i] += count[j];
                }
            }
        }
        max = Math.max(max, length[i]);
    }
    for (int i = 0; i < n; i++) {
        if (length[i] == max) {
            num += count[i];
        }
    }
    return num;
}
```
### 2. Segment tree
