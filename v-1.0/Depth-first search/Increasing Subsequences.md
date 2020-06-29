# 491. Increasing Subsequences
3/8/19
*Medium*

Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
```
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
```
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

## Attempts
- dp, O(n^2) time, O(n^2) space
```Java
public List<List<Integer>> findSubsequences(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums.length <= 1) {
        return res;
    }
    int n = nums.length;
    // all increasing sequences ending at the same index are stored in one set
    List<Set<List<Integer>>> dp = new ArrayList<>();
    for (int i = 1; i < n; i++) {
        // the set of sequences ending at index i
        Set<List<Integer>> set = new HashSet<>();
        for (int j = 0; j < i; j++) {
            if (nums[i] >= nums[j]) {
                // first, create a sequence with nums[j] & nums[i], add it to the set
                List<Integer> newlist = new ArrayList<>();
                newlist.add(nums[j]);
                newlist.add(nums[i]);
                set.add(newlist);
                // if j > 0, it means there exists at least a sequence ending at j
                if (j > 0) {
                    // get the set of sequence ending at j from dp list
                    // since j = 0 -> no sequence, it starts to have sequences at j = 1. so get(j-1)
                    Set<List<Integer>> preSet = dp.get(j - 1); // get the set of sequences ending at j
                    for (List<Integer> preList : preSet) {
                        List<Integer> list = new ArrayList<>();
                        list.addAll(preList);
                        list.add(nums[i]);
                        set.add(list);
                    }   
                }
            }
        }
        dp.add(set);
    }
    Set<List<Integer>> resSet = new HashSet<>();
    for (Set<List<Integer>> set : dp) {
        for (List<Integer> list : set) {
            resSet.add(list);
        }
    }
    for (List<Integer> list : resSet) {
        res.add(list);
    }
    return res;
}
```
