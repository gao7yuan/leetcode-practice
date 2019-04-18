# 47. Permutations II
4/17/19
*Medium*

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:
```
Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

## Attempts
- similar as Permutations but use a set
```Java
public List<List<Integer>> permuteUnique(int[] nums) {
    Set<List<Integer>> res = new HashSet<>();
    backtrack(res, nums, 0);
    return new ArrayList<>(res);
}

void backtrack(Set<List<Integer>> set, int[] nums, int target) {
    if (target == nums.length) {
        List<Integer> add = new ArrayList<>();
        for (int num : nums) {
            add.add(num);
        }
        set.add(add);
    }
    for (int i = target; i < nums.length; i++) {
        swap(nums, target, i);
        backtrack(set, nums, target + 1);
        swap(nums, target, i);
    }
}

void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

## Solutions
- 不用set的方法，一个个元素加入list
```Java
public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(res, new ArrayList<Integer>(), nums, new boolean[nums.length]);
    return res;
}

void backtrack(List<List<Integer>> list, List<Integer> temp, int[] nums, boolean[] used) {
    if (temp.size() == nums.length) {
        list.add(new ArrayList<>(temp));
    } else {
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            temp.add(nums[i]);
            backtrack(list, temp, nums, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}
```
