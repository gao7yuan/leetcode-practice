# 41. First Missing Positive
3/8/19
*Hard*

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
```
Input: [1,2,0]
Output: 3
```
Example 2:
```
Input: [3,4,-1,1]
Output: 2
```
Example 3:
```
Input: [7,8,9,11,12]
Output: 1
```
Note:

Your algorithm should run in O(n) time and uses constant extra space.

## Attempts
- use a set, O(n) time and O(n) space
```Java
public int firstMissingPositive(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
        set.add(num);
    }
    int i = 1;
    while (set.contains(i)) {
        i++;
    }
    return i;
}
```
## Solution
### index as a hash key
- get rid of negatives and zeros
- get rid of num > n b/c answer <= n + 1
- replace with 1's (after checking if 1 exists)
  - my thoughts: change nums[num-1] to negative to mark as exsting
  - O(n) time O(1) space
```Java
public int firstMissingPositive(int[] nums) {
    boolean hasone = false;
    // if nums contains 1
    for (int num : nums) {
        if (num == 1) {
            hasone = true;
            break;
        }
    }
    // if nums does not contain 1, return 1
    if (!hasone) {
        return 1;
    }
    int n = nums.length;
    // make all zeros, negatives, and num > n -> 1 so that all numbers are from 1 to n
    for (int i = 0; i < n; i++) {
        if (nums[i] <= 0 || nums[i] > n) {
            nums[i] = 1;
        }
    }

    // for each positive number, make its value at index = number - 1 -> negative
    for (int num : nums) {
        int abs = Math.abs(num);
        if (nums[abs - 1] > 0) {
            nums[abs - 1] *= (-1);
        }
    }

    for (int i = 0; i < n; i++) {
        if (nums[i] > 0) {
            return i + 1;
        }
    }
    return n + 1;
}```
