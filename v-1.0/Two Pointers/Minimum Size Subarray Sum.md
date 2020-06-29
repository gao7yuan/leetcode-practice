# 209. Minimum Size Subarray Sum
12/18/18
*Medium*

Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example:
```
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
```
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(nlog n).

## Attempts
* Brute force
  - calculate sum starting from each index
  - O(n^2) time, O(1) space
```Java
public int minSubArrayLen(int s, int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int min = Integer.MAX_VALUE;
    int sum;
    for (int i = 0; i < nums.length; i++) {
        sum = 0;
        for (int j = i; j < nums.length; j++) {
            sum += nums[j];
            if (sum >= s) {
                min = Math.min(min, j - i + 1);
                break;
            }
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
```

## Solutions
## 1.
* Binary search
  - one condition not used in Brute force: POSITIVE integer array
  - This means we can construct sum[] array and sum will be monotonically increasing
  - Binary search
  - O(nlgn) time, O(n) space
  - 注意binary search边界！注意要search的target的值是s + sum[i - 1]
```Java
public int minSubArrayLen(int s, int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int min = Integer.MAX_VALUE;
    int[] sum = new int[nums.length];
    sum[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
        sum[i] = sum[i - 1] + nums[i];
    }
    for (int i = 0; i < nums.length; i++) {
        int target = i == 0 ? s : s + sum[i - 1];
        // binary search
        int j = binarySearch(sum, target, i, sum.length - 1);
        if (j != Integer.MAX_VALUE) {
            min = Math.min(min, j - i + 1);
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}

private int binarySearch(int[] sum, int target, int s, int e) {
    int start = s;
    int end = e;
    int mid;
    while (start + 1 < end) {
        mid = start + (end - start) / 2;
        if (sum[mid] >= target) {
            end = mid;
        } else {
            start = mid;
        }
    }
    if (sum[start] >= target) {
        return start;
    } else if (sum[end] >= target) {
        return end;
    } else {
        return Integer.MAX_VALUE;
    }
}
```
## 2.
* Two pointers
  - sliding window
  - O(n) time, O(1) space
```Java
public int minSubArrayLen(int s, int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int min = Integer.MAX_VALUE;
    int sum = 0;
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
        sum += nums[j];
        while (sum >= s) {
            min = Math.min(min, j - i + 1);
            sum -= nums[i];
            i++;  
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
```
