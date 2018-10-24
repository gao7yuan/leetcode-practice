# 18. 4Sum
*Medium*
10/23/18

Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:
```
Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
```

## Attempts
* 开始想分别从两头开始二分但是发现是错的
* 转化成3sum
  - O(n^3) time
```
public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
        for (int one = 0; one < nums.length - 3; one++) {
            if (one > 0 && nums[one] == nums[one - 1]) {
                continue;
            }
            for (int two = one + 1; two < nums.length - 2; two++) {
                if (two > one + 1 && nums[two] == nums[two - 1]) {
                    continue;
                }
                int three = two + 1;
                int four = nums.length - 1;
                while (three < four) {
                    if (three > two + 1 && nums[three] == nums[three - 1]) {
                        three++;
                    } else if (four < nums.length - 1 && nums[four] == nums[four + 1]) {
                        four--;
                    } else {
                        int sum = nums[one] + nums[two] + nums[three] + nums[four];
                        if (sum < target) {
                            three++;
                        } else if (sum > target) {
                            four--;
                        } else {
                            List<Integer> sub = new ArrayList<>();
                            sub.add(nums[one]);
                            sub.add(nums[two]);
                            sub.add(nums[three]);
                            sub.add(nums[four]);
                            res.add(sub);
                            three++;
                            four--;
                        }
                    }
                }
            }
        }
        return res;
    }
```
