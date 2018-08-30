# 27. Remove Element
08/29/18

Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example 1:
```
Given nums = [3,2,2,3], val = 3,

Your function should return length = 2, with the first two elements of nums being 2.

It doesn't matter what you leave beyond the returned length.
```
Example 2:
```
Given nums = [0,1,2,2,3,0,4,2], val = 2,

Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.

Note that the order of those five elements can be arbitrary.

It doesn't matter what values are set beyond the returned length.
```

## Attempts
* Two pointers
  - two pointers i and j. i - slow-runner, j - fast-runner. both start at 0.
  - when nums[j] == val, keep incrementing j. when nums[j] != val, copy this value to nums[i], and increment i.
  - finally return i **(note: not i + 1 because i is already incremented!!)**
  - O(n) time, O(1) space.
```
public int removeElement(int[] nums, int val) {
    if (nums.length == 0) return 0;
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
        if (nums[j] != val) {
            nums[i++] = nums[j];
        }
    }
    return i;
}
```  

## Solutions
### Approach 1: Two pointers: similar as Remove Duplicates from Sorted Array
### Approach 2: Two pointers - when elements to remove are rare
* Using Approach 1, we do unnecessary duplication steps.
* When we encounter nums[i] = val, we can swap the current element out with the last element and dispose the last one. This essentially reduces the array's size by 1.
* Remember to still check this element in the next iteration because the last element that was swapped could be the val you want to remove.
* Each iteration, find the updated length of array with removing the targeted val.
```
public int removeElement(int[] nums, int val) {
    int i = 0;
    int n = nums.length;
    while (i < n) {
        if (nums[i] == val) {
            nums[i] = nums[n - 1];
            // reduce array size by one
            n--;
        } else {
            i++;
        }
    }
    return n;
}
```
```
public int removeElement(int[] nums, int val) {
    int len = nums.length;
    if (len == 0) return 0; // not necessary
    int i = 0;
    for (i = 0; i < len; i++) {
        if (nums[i] == val) {
            nums[i] = nums[len - 1];
            len--;
            i--;
        }
    }
    return len; // or return i;
}
```
