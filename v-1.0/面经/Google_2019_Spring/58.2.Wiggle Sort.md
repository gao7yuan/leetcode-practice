# 280. Wiggle Sort
*Medium*
09/30/18

Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:
```
Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
```

## Attempts
* sort first, then swap from the second element
  - O(nlgn) to sort, if heapsort, in place, O(1) time
```
public void wiggleSort(int[] nums) {
    Arrays.sort(nums);
    int temp; // for swap
    for (int i = 1; i < nums.length - 1; i += 2) {
        temp = nums[i];
        nums[i] = nums[i + 1];
        nums[i + 1] = temp;
    }
}
```
* one pass. if two elements don't meet the requirement simply swap them
  - O(n) time, O(1) space
```
public void wiggleSort(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
        if (i % 2 == 0 && nums[i] >= nums[i + 1]) swap(nums, i, i + 1);
        if (i % 2 == 1 && nums[i] <= nums[i + 1]) swap(nums, i, i + 1);
    }
}

void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```
