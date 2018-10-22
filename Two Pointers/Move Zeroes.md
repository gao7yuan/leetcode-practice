# 283. Move Zeroes
*Easy* *Array transformation*
10/21/18

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:
```
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
```
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.

## Attempts
* Two pointers
  - zero寻找下一个0，nonzero寻找下一个非零（注意边界条件，index不能超值），如果zero小于nonzero则swap，否则寻找下一个非零
  - O(n) time, O(1) space
```
public void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 0) {
        return;
    }
    int zero = 0;
    int nonzero = 0;
    while (zero < nums.length && nonzero < nums.length) {
        while (nums[zero] != 0 && zero < nums.length - 1) {
            zero++;
        }
        while (nums[nonzero] == 0 && nonzero < nums.length - 1) {
            nonzero++;
        }
        if (zero < nonzero) {
            swap(nums, zero, nonzero);
            zero++;
            nonzero++;
        } else {
            nonzero++;
        }

    }
}

void swap(int[] nums, int a, int b) {
    int temp = nums[a];
    nums[a] = nums[b];
    nums[b] = temp;
}
```

## Solutions
Array transformation
### 1. Space sub-optimal
As a good start for an interview: 一个新array存放结果。计算有几个0，先将非0顺次放进去，再将0放进去
  - O(n) time, O(n) space
### 2. Time sub-optimal 这个方法不容易写错，而且没有比3差多少
顺次将非零元素填入
  - O(n) time, O(1) space，每个元素都会被visit

```
void moveZeroes(vector<int>& nums) {
    int lastNonZeroFoundAt = 0;
    // If the current element is not 0, then we need to
    // append it just in front of last non 0 element we found.
    for (int i = 0; i < nums.size(); i++) {
        if (nums[i] != 0) {
            nums[lastNonZeroFoundAt++] = nums[i];
        }
    }
    // After we have finished processing new elements,
    // all the non-zero elements are already at beginning of array.
    // We just need to fill remaining array with 0's.
    for (int i = lastNonZeroFoundAt; i < nums.size(); i++) {
        nums[i] = 0;
    }
}
```
### 3. Optimal
  - O(n) time, O(1) space. Number of array writes depends on the number of non-zero elements
```
void moveZeroes(vector<int>& nums) {
    for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.size(); cur++) {
        if (nums[cur] != 0) {
            swap(nums[lastNonZeroFoundAt++], nums[cur]);
        }
    }
}
```
