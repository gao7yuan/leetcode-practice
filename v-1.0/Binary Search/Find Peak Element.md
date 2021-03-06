# 162. Find Peak Element
*Medium* *二刷*
10/12/18

A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:
```
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
```
Example 2:
```
Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5
Explanation: Your function can return either index number 1 where the peak element is 2,
             or index number 5 where the peak element is 6.
```
Note:

Your solution should be in logarithmic complexity.

## Attempts
* binary search
  - O(lgn) time, O(1) space
```
public int findPeakElement(int[] nums) {
    if (nums.length == 0) {
        return -1;
    }
    int start = 0;
    int end = nums.length - 1;
    int mid;
    while (start + 1 < end) {
        mid = start + (end - start) / 2;
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
            return mid;
        } else if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1]) {
            end = mid;
        } else {
            start = mid;
        }
    }
    if (nums[start] > nums[end]) {
        return start;
    } else {
        return end;
    }
}
```

```Java
public int findPeakElement(int[] nums) {
    int l = nums.length;
    int lo = 0;
    int hi = l - 1;
    int mid;
    while (lo < hi) {
        mid = lo + (hi - lo) / 2;
        if ((mid == 0 || nums[mid] > nums[mid - 1]) && (mid == l - 1 || nums[mid] > nums[mid + 1])) {
            return mid;
        }
        if (mid > 0 && nums[mid] < nums[mid - 1]) {
            hi = mid - 1;
        } else {
            lo = mid + 1;
        }
    }
    return lo;
}
```
