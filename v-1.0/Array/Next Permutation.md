# 31. Next Permutation
*Medium*
11/7/18

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

## Solutions
### 1. Brute force
* find out all permutations
* O(n!) time (total number of possible permutations), O(n) space (array to store)

### 2. Single pass approach
* 如果整个数列降序，则没有合适的permutation，只需要将整个数列倒序即可。
* 从数列尾部开始向前寻找第一个i使num[i] > num[i - 1]
* 说明i之后都是降序的。依次寻找找到i-1之后最小的大于num[i - 1]的数，将其和num[i - 1]交换
* 此时，i-1之后还是降序排列的，需要将其倒序即可。
* O(n) time, O(1) space
```
public void nextPermutation(int[] nums) {
    // corner case when nums is empty
    if (nums == null || nums.length == 0) {
        return;
    }
    // from the end find first i for nums[i] > nums[i - 1]
    int i = nums.length - 1;
    for (; i > 0; i--) {
        if (nums[i] > nums[i - 1]) {
            break;
        }
    }
    // all descending
    if (i == 0) {
        reverse(nums, 0, nums.length - 1);
        return;
    }
    // find smallest number after i - 1, larger than nums[i - 1]
    int j = i;
    for (; j < nums.length - 1; j++) {
        if (nums[j] > nums[i - 1] && nums[j + 1] <= nums[i - 1]) {
            break;
        }
    }
    swap(nums, i - 1, j);
    reverse(nums, i, nums.length - 1);
}

private void reverse(int[] nums, int start, int end) {
    for (int i = start, j = end; i < j; i++, j--) {
        swap(nums, i, j);
    }
}

private void swap(int[] nums, int start, int end) {
    int temp = nums[start];
    nums[start] = nums[end];
    nums[end] = temp;
}
```
* neater code from Solutions
```
public void nextPermutation(int[] nums) {
    int i = nums.length - 2;
    while (i >= 0 && nums[i + 1] <= nums[i]) {
        i--;
    }
    if (i >= 0) {
        int j = nums.length - 1;
        while (j >= 0 && nums[j] <= nums[i]) {
            j--;
        }
        swap(nums, i, j);
    }
    reverse(nums, i + 1);
}
```
