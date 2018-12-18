# 80. Remove Duplicates from Sorted Array II
12/18/18
*Medium*

Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
```
Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
```
Example 2:
```
Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.
```

## Solutions
* Go through the array once and if the number meets the requirement add it to the array
* i: the exact index we want to add number to
* visit each num in nums, if i < 2, or if num > nums[i - 2], then we add this num to nums[i], and increment i.
* O(n) time, O(1) space
```Java
public int removeDuplicates(int[] nums) {
    int i = 0;
    for (int num : nums) {
        if (i < 2 || num > nums[i - 2]) {
            nums[i] = num;
            i++;
        }
    }
    return i;
}
```
