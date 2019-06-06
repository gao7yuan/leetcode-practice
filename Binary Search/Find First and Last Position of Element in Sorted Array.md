# 34. Find First and Last Position of Element in Sorted Array
*Medium* *二刷*
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

## 二刷
- 上述code是九章的模板，但是不是很喜欢。如果不用的话要非常注意边界。以下是solution的code
```Java
class Solution {
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }
}
```
- my code easier to read and understand
- IMPORTANT:
  - `hi` starts from `nums.length - 1`
  - if condition for while loop is `lo < hi`, then should pay attention, usually we want to do `lo = mid + 1` otherwise it won't finish.
```Java
public int[] searchRange(int[] nums, int target) {

    int[] res = new int[]{-1, -1};

    if (nums.length == 0) {
        return res;
    }

    // find left

    int lo = 0;
    int hi = nums.length - 1;
    int mid;

    while (lo < hi) {
        mid = lo + (hi - lo) / 2;
        int cur = nums[mid];
        if (cur == target) {
            hi = mid; // include mid, we might want it as result
        } else if (cur < target) {
            lo = mid + 1;
        } else {
            hi = mid - 1;
        }
    }

    // not found
    if (nums[lo] != target) {
        return res;
    }
    // found
    res[0] = lo;

    // do not need to set lo to 0
    hi = nums.length - 1;

    while (lo < hi) {
        mid = lo + (hi - lo) / 2;
        int cur = nums[mid];
        if (cur == target) {
            lo = mid + 1; // IMPORTANT! have to increment mid, otherwise won't finish
        } else if (cur < target) {
            lo = mid + 1;
        } else {
            hi = mid - 1;
        }
    }

    if (nums[lo] == target) {
        res[1] = lo;
    } else { // if (lo > 0 && nums[lo - 1] == target)
        res[1] = lo - 1;
    }
    return res;
}
```
