# 1. Two Sum
*Easy*
8/2/18

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

**Example:**
```
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

## Attempts & Solution
* Brute Force
  - O(n^2) time. O(1) space.
```
public int[] twoSum(int[] nums, int target) {
    int[] indices = new int[2];
    for (int i = 0; i < nums.length - 1; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] == target) {
                indices[0] = i;
                indices[1] = j;
                // it is OK not to return here, because each iteration we only check elements after it.
                // Or return here and throw exception after the for loops.
            }
        }
    }
    return indices;
}
```    
* Two-pass Hash Table
  - Loop the array once to populate the hashtable (or hashmap) with num-index pairs.
  - Loop the array one more time to find its complement in the hashtable. If it exists, return the index.
  - The index of the complement cannot be the same as the original.
  - O(n) time. O(n) space.
```
public int[] twoSum(int[] nums, int target) {
    Hashtable<Integer, Integer> numsTable = new Hashtable<>();
    for (int i=0; i<nums.length; i++) {
        numsTable.put(nums[i], i);
    }
    for (int i=0; i<nums.length; i++) {
        int comp = target - nums[i];
        if (numsTable.keySet().contains(comp) && numsTable.get(comp) != i) {
            int index = numsTable.get(comp);
            return new int[] {i, index};
            // Have to return here otherwise will repeat this step with its complement and return the indices in a wrong order.
        }
    }
    throw new IllegalArgumentException("no two sum solution");
}
```
* One-pass Hash Table
  - While iterating the array, check whether its complement is in the hashtable.
  - Have to put complement's index before the original's index. Because num with smaller index is added to the hashtable first.
  - O(1) time. O(1) space.
```
public int[] twoSum(int[] nums, int target) {
    Hashtable<Integer, Integer> numsTable = new Hashtable<>();
    for (int i=0; i<nums.length; i++) {
        int comp = target - nums[i];
        if (numsTable.keySet().contains(comp)) {
            return new int[]{numsTable.get(comp), i};
        }
        numsTable.put(nums[i], i);
    }
    throw new IllegalArgumentException("no two sum solution");
}
```
