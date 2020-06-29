# 78. Subsets
4/17/19
*Medium*

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:
```
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

## Solutions
```Java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    //Arrays.sort(nums);
    backtrack(res, new ArrayList<>(), nums, 0);
    return res;
}

// each time have the chance to add the next element, and work on the rest. or skip the next element, and work on the rest. -> powerset
private void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums, int start) {
    list.add(new ArrayList<>(temp));
    for (int i = start; i < nums.length; i++) {
        temp.add(nums[i]);
        backtrack(list, temp, nums, i + 1);
        temp.remove(temp.size() - 1);
    }
}
```
