# 268. Missing Number
4/17/19
*Easy*

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:
```
Input: [3,0,1]
Output: 2
```
Example 2:
```
Input: [9,6,4,2,3,5,7,0,1]
Output: 8
```
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

## Attempts
- two passes, O(n) time, O(1) space
```Java
public int missingNumber(int[] nums) {
    for (int num : nums) {
        int abs = Math.abs(num); // get the original value in the slot
        // ignore n for now
        if (abs == nums.length) {
            continue;
        }
        if (abs == Integer.MAX_VALUE) {
            abs = 0;
        }

        // if value not zero, make negative
        if (nums[abs] != 0) {
            nums[abs] *= -1;
        } else {
            // if value is zero, make max value
            nums[abs] = Integer.MAX_VALUE;
        }
    }
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] >= 0 && nums[i] != Integer.MAX_VALUE) {
            return i;
        }
    }
    return nums.length;
}
```

## Solutions
### 1. Sorting
- O(nlgn) time, O(1) space or O(n)
### 2. HashSet
- O(n) time, O(n) space
### 3. XOR
- n XOR all the other indices and numbers
- O(n) time, O(1) space
### 4. Gauss's formula
- sum(0...n) then minus sum of all integers in array
- O(n) time, O(1) space
