# 160. Find Minimum in Rotated Sorted Array II
*Medium*
10/03/18

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

Example

Given [4,4,5,6,7,0,1,2] return 0.

Notice

The array may contain duplicates.

* 线性方法
* 二分法最好情况O(lgn)，最坏情况线性
```
public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
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
                if (nums[start] < min) {
                    min = nums[start];
                }
                start = mid;
            } else if (nums[start] == nums[mid]) {
                start++;
            } else if (nums[mid] == nums[end]) {
                end--;
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
