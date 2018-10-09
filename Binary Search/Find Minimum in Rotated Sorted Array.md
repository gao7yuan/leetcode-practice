# 153. Find Minimum in Rotated Sorted Array
*Medium*
10/08/18

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:
```
Input: [3,4,5,1,2]
Output: 1
```
Example 2:
```
Input: [4,5,6,7,0,1,2]
Output: 0
```

## Attempts
* binary search
  - O(lgn) time, O(1) space
```
public int findMin(int[] nums) {
    int min = Integer.MAX_VALUE;
    if (nums.length == 0) {
        return min;
    }
    int start = 0;
    int end = nums.length - 1;
    int mid;
    while (start + 1 < end) {
        mid = start + (end - start) / 2;
        if (nums[start] < nums[mid]) {
            if (min > nums[start]) {
                min = nums[start];
            }
            start = mid;
        } else {
            if (min > nums[mid]) {
                min = nums[mid];
            }
            end = mid;
        }
    }
    if (min > nums[start]) {
        min = nums[start];
    }
    if (min > nums[end]) {
        min = nums[end];
    }
    return min;
}
```
