# 154. Find Minimum in Rotated Sorted Array II
*Hard*
10/13/18

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:
```
Input: [1,3,5]
Output: 1
```
Example 2:
```
Input: [2,2,2,0,1]
Output: 0
```

Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?

## Attempts
* Brute force
  - Big theta (n) time
```
public int findMin(int[] nums) {
    int min = Integer.MAX_VALUE;
    for (int num : nums) {
        if (min > num) {
            min = num;
        }
    }
    return min;
}
```
* Binary search
  - Worst case O(n) time
```
public int findMin(int[] nums) {
    int start = 0;
    int end = nums.length - 1;
    int mid;
    while (start + 1 < end) {
        mid = start + (end - start) / 2;
        if (nums[mid] > nums[end]) {
            start = mid;
        } else if (nums[mid] == nums[end]) {
            end--;
        } else {
            end = mid;
        }
    }
    return nums[start] < nums[end] ? nums[start] : nums[end];
}
```
