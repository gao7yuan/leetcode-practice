# 90. Subsets II
4/17/19
*Medium*

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:
```
Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```

## Attempts
- similar as Subsets, but use a set to avoid duplicates
```Java
public List<List<Integer>> subsetsWithDup(int[] nums) {
    Set<List<Integer>> res = new HashSet<>();
    //Arrays.sort(nums);
    backtrack(res, new ArrayList<>(), nums, 0);
    return new ArrayList<>(res);
}

void backtrack(Set<List<Integer>> set, List<Integer> temp, int[] nums, int start) {
    set.add(new ArrayList<>(temp));
    for (int i = start; i < nums.length; i++) {
        temp.add(nums[i]);
        backtrack(set, temp, nums, i + 1);
        temp.remove(temp.size() - 1);
    }
}
```
- or do not use Set
```Java
public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(res, new ArrayList<>(), nums, 0);
    return res;
}

void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums, int start) {
    list.add(new ArrayList<>(temp));
    for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i - 1]) continue;
        temp.add(nums[i]);
        backtrack(list, temp, nums, i + 1);
        temp.remove(temp.size() - 1);
    }
}
```
