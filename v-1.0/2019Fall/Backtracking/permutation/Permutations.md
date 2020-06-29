# 46. Permutations

- my code
```Java
private Set<Integer> used = new HashSet<>();
private List<List<Integer>> res = new ArrayList<>();
private int n; // count of numbers
private int[] nums;

public List<List<Integer>> permute(int[] nums) {
    this.nums = nums;
    this.n = nums.length;
    backtrack(new ArrayList<Integer>());
    return res;
}

private void backtrack(List<Integer> temp) {
    if (temp.size() == n) {
        // one full result
        res.add(new ArrayList<Integer>(temp));
    } else {
        // not full result yet
        for (int i = 0; i < n; i++) {
            if (used.contains(nums[i])) {
                continue;
            }
            used.add(nums[i]);
            temp.add(nums[i]);
            backtrack(temp);
            used.remove(nums[i]);
            temp.remove(temp.size() - 1);
        }
    }
}
```
