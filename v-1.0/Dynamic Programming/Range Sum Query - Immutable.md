# 303. Range Sum Query - Immutable
*Easy*
09/11/18

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
```
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
```
Note:
You may assume that the array does not change.
There are many calls to sumRange function.

## Attempts
* Brute force
  - Each sum takes O(n) time O(1) space
* Use a hash table to store each sum, O(n^2) time in constructor. O(1) time calling the method. O(n^2) space.
* Use an array to store sum up to index i
  - Constructor takes O(n) time, but calling the method takes O(1) time
  - O(n) space
```
class NumArray {

    int[] sum;

    public NumArray(int[] nums) {
        sum = new int[nums.length];
        if (nums.length == 0) return; //要考虑输入array为空的情况
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) return sum[j];
        return sum[j] - sum[i - 1];
    }
}
```
或者
```
private int[] sum;

public NumArray(int[] nums) {
    sum = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
        sum[i + 1] = sum[i] + nums[i];
    }
}

public int sumRange(int i, int j) {
    return sum[j + 1] - sum[i];
}
```
