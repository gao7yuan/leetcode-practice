# 152. Maximum Product Subarray
3/6/19
*Medium*

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:
```
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
```
Example 2:
```
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
```

## Solution
- max = max product of contiguous subarray ending with index i
  - max = MAX(max * nums[i], nums[i])
- min = min product of contiguous subarray ending with index i
  - min = MAX(min * nums[i], nums[i])
- swap min max when nums[i] < 0
- update res to MAX(res, max)
- O(n) time, O(1) space

```Java
public int maxProduct(int[] nums) {
    if (nums.length == 0) {
        return 0;
    }
    int res = nums[0];
    int max = nums[0];
    int min = nums[0];
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] < 0) {
            int tmp = max;
            max = min;
            min = tmp;
        }
        max = Math.max(max * nums[i], nums[i]);
        min = Math.min(min * nums[i], nums[i]);
        res = Math.max(max, res);
    }
    return res;
}
```
