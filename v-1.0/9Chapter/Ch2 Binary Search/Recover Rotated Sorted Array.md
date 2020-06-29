# 39. Recover Rotated Sorted Array
*Easy*
10/08/18

Description
Given a rotated sorted array, recover it to sorted array in-place.
Have you met this question in a real interview?  Yes
Problem Correction
Clarification
What is rotated array?

For example, the orginal array is [1,2,3,4], The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
Example
[4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
Challenge
In-place, O(1) extra space and O(n) time.

* 三步翻转法
[4 5] [1 2 3]
[5 4] [3 2 1]
[1 2 3 4 5]

```
public class Solution {
    /**
     * @param nums: An integer array
     * @return: nothing
     */
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here
        if (nums.size() == 0) {
            return;
        }
        int i = 0;
        for (; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)) {
                break;
            }
        }
        if (i >= nums.size() - 1) {
            return;
        }
        int j = 0;
        int k = i;
        while (j < k) {
            swap(nums, j, k);
            j++;
            k--;
        }
        j = i + 1;
        k = nums.size() - 1;
        while (j < k) {
            swap(nums, j, k);
            j++;
            k--;
        }
        j = 0;
        k = nums.size() - 1;
        while (j < k) {
            swap(nums, j, k);
            j++;
            k--;
        }
    }

    private void swap(List<Integer> nums, int j, int k) {
        int temp = nums.get(j);
        nums.set(j, nums.get(k));
        nums.set(k, temp);
    }
}
```
