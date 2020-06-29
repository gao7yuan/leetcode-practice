# 217. Contains Duplicate
*Easy* *已整理*
08/26/18

Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:
```
Input: [1,2,3,1]
Output: true
```
Example 2:
```
Input: [1,2,3,4]
Output: false
```
Example 3:
```
Input: [1,1,1,3,3,4,3,2,4,2]
Output: true
```

## Attempts
* Use a set to keep track of nums in ```nums```
* O(n) time, O(n) space.
```
public boolean containsDuplicate(int[] nums) {
    Set<Integer> numSet = new HashSet<>();
    for (int num : nums) {
        if (numSet.contains(num)) {
            return true;
        } else {
            numSet.add(num);
        }
    }
    return false;
}
```

## Solutions
### Approach 1
* Naive Linear Search
  - For each integer, search all previous integers for duplicates.
  - O(n^2) time, O(1) space.
### Approach 2
* Sorting
  - Any duplicates will be consecutive after sorting.
  - O(nlogn) time (e.g. *heapsort*)
  - O(1) space (for *heapsort*).
```
public boolean containsDuplicate(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; ++i) {
        if (nums[i] == nums[i + 1]) return true;
    }
    return false;
}
```
### Approach 3
* Hash Table (use a set)
  - O(n) time, O(n) space.

> Note
For certain test cases with not very large n, the runtime of this method can be slower than Approach 2. The reason is hash table has some overhead in maintaining its property. One should keep in mind that real world performance can be different from what the Big-O notation says. The Big-O notation only tells us that for sufficiently large input, one will be faster than the other. Therefore, when n is not sufficiently large, an O(n) algorithm can be slower than an O(nlogn) algorithm.
