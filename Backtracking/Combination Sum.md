# 39. Combination Sum
4/17/19
*Medium*

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:
```
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
```
Example 2:
```
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

## Attempts
- similar as `Subsets`, but check sum, and can use one element for unlimited times. (so note in recursive call of `backtrack`, we do not increment `i`).
```Java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    backtrack(res, new ArrayList<>(), 0, candidates, 0, target);
    return res;
}

void backtrack(List<List<Integer>> list, List<Integer> temp, int sum, int[] nums, int start, int target) {
    if (sum == target) {
        list.add(new ArrayList<>(temp));
    }
    if (sum < target) {
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            sum += nums[i];
            backtrack(list, temp, sum, nums, i, target);
            sum -= nums[i];
            temp.remove(temp.size() - 1);
        }
    }
}
```
