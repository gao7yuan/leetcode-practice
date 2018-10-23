# 16. 3Sum Closest
*Medium*
10/22/18

Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:
```
Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
```

## Attempts
* similar as three sum
  - i start from 0 to length - 3
  - start be i + 1, end be length - 1, each time check the sum, progress toward the target.
```
public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int threesum = nums[0] + nums[1] + nums[2];
    for (int i = 0; i < nums.length - 2; i++) {
        int twosum = target - nums[i];
        int j = i + 1;
        int k = nums.length - 1;
        int sum = nums[i] + nums[j] + nums[k];
        while (j + 1 < k) {
            if (nums[j] + nums[k] > twosum) {
                k--;
            } else if (nums[j] + nums[k] < twosum) {
                j++;
            } else {
                break;
            }
            sum = nums[i] + nums[j] + nums[k];
            threesum = Math.abs(threesum - target) < Math.abs(sum - target) ? threesum : sum;
        }
        threesum = Math.abs(threesum - target) < Math.abs(sum - target) ? threesum : sum;
    }
    return threesum;
}
```

## Solutions
* three sum - neater code
```
public int threeSumClosest(int[] num, int target) {
    int result = num[0] + num[1] + num[num.length - 1];
    Arrays.sort(num);
    for (int i = 0; i < num.length - 2; i++) {
        int start = i + 1, end = num.length - 1;
        while (start < end) {
            int sum = num[i] + num[start] + num[end];
            if (sum > target) {
                end--;
            } else {
                start++;
            }
            if (Math.abs(sum - target) < Math.abs(result - target)) {
                result = sum;
            }
        }
    }
    return result;
}
```
* my modified code
```
public int threeSumClosest(int[] nums, int target) {
    int threesum = nums[0] + nums[1] + nums[nums.length - 1];
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
        int start = i + 1;
        int end = nums.length - 1;
        while (start < end) {
            int sum = nums[i] + nums[start] + nums[end];
            if (nums[i] + nums[start] + nums[end] > target) {
                end--;
            } else if (nums[i] + nums[start] + nums[end] < target) {
                start++;
            } else {
                return target;
            }
            threesum = Math.abs(threesum - target) < Math.abs(sum - target) ? threesum : sum;
        }
    }
    return threesum;
}
```
