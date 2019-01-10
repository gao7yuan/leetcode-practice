# 442. Find All Duplicates in an Array
1/9/19
*Medium*

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
```
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
```

## Attempts
* sort and compare: O(nlgn) time to sort
* use hashset: O(n) space
* Note that one condition is not used:
`1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.`
  - make use of the range of the numbers in the array. number - 1 is the index of the array. for a number we can make the number matching the index negative as a flag, meaning the number has apeared once.
  - O(n) time, one pass, O(1) space
```Java
public List<Integer> findDuplicates(int[] nums) {
    List<Integer> res = new ArrayList<>();
    if (nums == null || nums.length == 0) {
        return res;
    }
    for (int i = 0; i < nums.length; i++) {
        int num = Math.abs(nums[i]);
        if (nums[num - 1] < 0) {
            res.add(num);
        } else {
            nums[num - 1] *= -1;
        }
    }
    return res;
}
```
