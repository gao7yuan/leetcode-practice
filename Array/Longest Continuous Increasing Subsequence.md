# 647. Longest Continuous Increasing Subsequence
3/8/19
*Easy*

Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

Example 1:
```
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
```
Example 2:
```
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1.
Note: Length of the array will not exceed 10,000.
```

## Attempts
- two pointers
  - one pass, O(n) time, O(1) space
```Java
public int findLengthOfLCIS(int[] nums) {
    if (nums.length == 0) {
        return 0;
    }
    int n = nums.length;
    int len = 1;
    int i = 0, j;
    while (i < n) {
        int sublen = 1;
        j = i + 1;
        while (j < n && nums[j] > nums[j - 1]) {
            j++;
            sublen++;
        }
        len = Math.max(len, sublen);
        i = j;
    }
    return len;
}
```

## Solution
- sliding window using anchor
  - O(n) time O(1) space
```Java
public int findLengthOfLCIS(int[] nums) {
    int ans = 0, anchor = 0;
    for (int i = 0; i < nums.length; ++i) {
        if (i > 0 && nums[i-1] >= nums[i]) anchor = i;
        ans = Math.max(ans, i - anchor + 1);
    }
    return ans;
}
```
