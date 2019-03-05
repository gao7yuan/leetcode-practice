# 46. Permutations
3/4/19
*Medium*

## NOTE
- Backtracking is an algorithm for finding all solutions by exploring all potential candidates. If the solution candidate turns to be not a solution (or at least not the last one), backtracking algorithm discards it by making some changes on the previous step, i.e. backtracks and then try again.

Given a collection of distinct integers, return all possible permutations.

Example:
```
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```
## Solutions
- backtracking
  - index of first integer to consider as argument `backtrack(first)`
  - if `first` is n then finished backtracking
  - iterate over the integers from index `first` to `n - 1`
    - place i-th integer first in permutation, `swap(nums[first], nums[i])`
    - create all permutations starting from ith integer `backtrack(first + 1)`
    - backtrack. `swap(nums[first], nums[i])` back


```Java
class Solution {
  public void backtrack(int n, ArrayList<Integer> nums,
  List<List<Integer>> output, int first) {
    // if all integers are used up
    if (first == n)
      output.add(new ArrayList<Integer>(nums));
    for (int i = first; i < n; i++) {
      // place i-th integer first
      // in the current permutation
      Collections.swap(nums, first, i);
      // use next integers to complete the permutations
      backtrack(n, nums, output, first + 1);
      // backtrack
      Collections.swap(nums, first, i);
    }
  }

  public List<List<Integer>> permute(int[] nums) {
    // init output list
    List<List<Integer>> output = new LinkedList();

    // convert nums into list since the output is a list of lists
    ArrayList<Integer> nums_lst = new ArrayList<Integer>();
    for (int num : nums)
      nums_lst.add(num);

    int n = nums.length;
    backtrack(n, nums_lst, output, 0);
    return output;
  }
}
```

- my code
```Java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums.length == 0) {
        return res;
    }
    ArrayList<Integer> numList = new ArrayList<>();
    for (Integer num : nums) {
        numList.add(num);
    }
    backtrack(nums.length, res, numList, 0);
    return res;
}

private void backtrack(int n, List<List<Integer>> output, ArrayList<Integer> nums, int first) {
    if (first == n) {
        output.add(new ArrayList<Integer>(nums));
    }
    for (int i = first; i < n; i++) {
        Collections.swap(nums, first, i);
        backtrack(n, output, nums, first + 1);
        Collections.swap(nums, first, i);
    }
}
```
