# 287. Find the Duplicate Number
*Medium*
10/12/18

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:
```
Input: [1,3,4,2,2]
Output: 2
```
Example 2:
```
Input: [3,1,3,4,2]
Output: 3
```
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

## Solutions
### 1. Sorting (does not meet requirement)
* O(nlgn) time, O(1) space (altered the array)
### 2. Set (does not meet requirement)
* O(n) time, O(n) space
### 3. Floyd's Tortoise and Hare (Cycle Detection)
```
public int findDuplicate(int[] nums) {
    int slow = 0;
    int fast = 0;
    do {
        slow = nums[slow];
        fast = nums[fast];
        fast = nums[fast];
    } while (slow != fast);
    int start = 0;
    while (start != slow) {
        start = nums[start];
        slow = nums[slow];
    }
    return start;
}
```
