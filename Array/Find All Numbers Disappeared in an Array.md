# 448. Find All Numbers Disappeared in an Array
12/28/18
*Easy*

Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:
```
Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
```

## Solutions
* 合理利用nums[i]和index的关系
  - O(n) time (2 pass), O(1) space
```Java
public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> res = new ArrayList<>();
    if (nums.length == 0) {
        return res;
    }
    for (int i = 0; i < nums.length; i++) {
        int val = Math.abs(nums[i]) - 1; // some are already negative, need to handle it
        if (nums[val] > 0) {
            nums[val] = -nums[val];
        }
    }
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] > 0) {
            res.add(i + 1);
        }
    }
    return res;
}
```
