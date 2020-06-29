# 238. Product of Array Except Self
*Medium*
10/16/18

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:
```
Input:  [1,2,3,4]
Output: [24,12,8,6]
```
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

## Attempts after reading discussion
* 先将左边所有数的乘积存入数组，再将右边的数乘入数组
  - O(n) time, O(1) space
```
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    for (int i = 0, tmp = 1; i < n; i++) {
        res[i] = tmp;
        tmp *= nums[i];
    }
    for (int i = n - 1, tmp = 1; i >= 0; i--) {
        res[i] *= tmp;
        tmp *= nums[i];
    }
    return res;
}
```
