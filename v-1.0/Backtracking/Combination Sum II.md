# 40. Combination Sum II
4/17/19
*Medium*

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:
```
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```
Example 2:
```
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
```

## Attempts
- similar as `Combination Sum` and `Subsets II`
```Java
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(res, new ArrayList<>(), candidates, 0, 0, target);
    return res;
}

void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums, int sum, int start, int target) {
    if (sum == target) {
        list.add(new ArrayList<>(temp));
    }
    if (sum < target) {
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            sum += nums[i];
            backtrack(list, temp, nums, sum, i + 1, target);
            temp.remove(temp.size() - 1);
            sum -= nums[i];
        }
    }
}
```
