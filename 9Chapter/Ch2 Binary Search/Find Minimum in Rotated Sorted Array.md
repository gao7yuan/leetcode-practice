# 159. Find Minimum in Rotated Sorted Array
*Medium*
10/03/18

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

Example

Given [4, 5, 6, 7, 0, 1, 2] return 0

Notice

You may assume no duplicate exists in the array.

```
public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        if (nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        int min = Integer.MAX_VALUE;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[start] < nums[mid]) {
                if (nums[start] < min) {
                    min = nums[start];
                }
                start = mid;
            } else {
                if (nums[mid + 1] < min) {
                    min = nums[mid + 1];
                }
                end = mid;
            }
        }
        if (nums[start] < min) {
            min = nums[start];
        }
        if (nums[end] < min) {
            min = nums[end];
        }
        return min;
    }
}
```
