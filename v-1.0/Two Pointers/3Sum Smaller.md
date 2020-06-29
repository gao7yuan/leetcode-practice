# 259. 3Sum Smaller
12/19/18
*Medium*

Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:
```
Input: nums = [-2,0,1,3], and target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
```
Follow up: Could you solve it in O(n2) runtime?

## Attempts
* Brute force: O(n^3) time
* Binary search
  - sort: O(nlgn) time
  - nested for loops, find third number (largest one with sum < target) using binary search
  - O(n^2lgn) time
```Java
public int threeSumSmaller(int[] nums, int target) {
    if (nums.length == 0) {
        return 0;
    }
    int count = 0;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
        for (int j = i + 1; j < nums.length - 1; j++) {
            int start = j + 1;
            int end = nums.length - 1;
            int mid;
            int toFind = target - nums[i] - nums[j];
            while (start + 1 < end) {
                mid = start + (end - start) / 2;
                if (nums[mid] >= toFind) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
            int k = 0;
            if (nums[end] < toFind) {
                k = end;
            } else if (nums[start] < toFind) {
                k = start;
            }
            if (k != 0) {
                count += k - j;
            }
        }
    }
    return count;
}
```

## Solutions
* Two pointers
  - j, k use two pointers to find longest subarray
  - O(n) time, O(1) space

```Java
public int threeSumSmaller(int[] nums, int target) {
    if (nums.length == 0) {
        return 0;
    }
    int count = 0;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
        int j = i + 1;
        int k = nums.length - 1;
        while (j < k) {
            if (nums[i] + nums[j] + nums[k] < target) {
                count += k - j;
                j++;
            } else {
                k--;
            }
        }
    }
    return count;
}
```
