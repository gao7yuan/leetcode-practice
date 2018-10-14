# 34. Find First and Last Position of Element in Sorted Array
*Medium*
10/12/18

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:
```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```
Example 2:
```
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```

## Attempts
* Binary search
  - O(lgn) time
```
public int[] searchRange(int[] nums, int target) {
    int[] res = new int[2];
    if (nums.length == 0) {
        res[0] = -1;
        res[1] = -1;
        return res;
    }
    int start = 0;
    int end = nums.length - 1;
    int mid;
    // find first:
    while (start + 1 < end) {
        mid = start + (end - start) / 2;
        if (nums[mid] < target) {
            start = mid;
        } else {
            end = mid;
        }
    }
    if (nums[start] == target) {
        res[0] = start;
    } else if (nums[end] == target) {
        res[0] = end;
    } else {
        res[0] = -1;
        res[1] = -1;
        return res;
    }
    start = 0;
    end = nums.length - 1;
    while (start + 1 < end) {
        mid = start + (end - start) / 2;
        if (nums[mid] > target) {
            end = mid;
        } else {
            start = mid;
        }
    }
    if (nums[end] == target) {
        res[1] = end;
    } else if (nums[start] == target) {
        res[1] = start;
    } else {
        res[0] = -1;
        res[1] = -1;
        return res;
    }
    return res;
}
```
