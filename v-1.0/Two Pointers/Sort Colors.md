# 75. Sort Colors
*Medium*
10/23/18

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:
```
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
```
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?

## Attempts
* counting sort
  - O(2n) = O(n) time, O(3) space
```
public void sortColors(int[] nums) {
    int[] counts = new int[3];
    for (int num : nums) {
        counts[num]++;
    }
    int i = 0;
    for (int j = 0; j < 3; j++) {
        while (counts[j] != 0) {
            nums[i++] = j;
            counts[j]--;
        }
    }
}
```

## Solutions
* two pointers
  - 把2都放在尾部，0都放在头部
```
public void sortColors(int[] nums) {
    int zero = 0;
    int two = nums.length - 1;
    for (int i = 0; i < nums.length; i++) {
        while (nums[i] == 2 && i < two) {
            swap(nums, i, two--);
        }
        while (nums[i] == 0 && i > zero) {
            swap(nums, i, zero++);
        }
    }
}
void swap(int[] nums, int a, int b) {
    int temp = nums[a];
    nums[a] = nums[b];
    nums[b] = temp;
}
```
