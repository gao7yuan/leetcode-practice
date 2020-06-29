# 532. K-diff Pairs in an Array
12/19/18
*Easy*

Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
```
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
```
Example 2:
```
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
```
Example 3:
```
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
```
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].

## Solutions
### 1. Two pointers
* sort the array first, then use sliding window
* O(nlgn) time, O(1) space
```Java
public int findPairs(int[] nums, int k) {
    if (nums.length == 0 || k < 0) {
        return 0;
    }
    int count = 0;
    int i = 0;
    int j = 1;
    Arrays.sort(nums);
    while (j < nums.length) {
        int first = nums[i];
        int second = nums[j];
        if (second - first < k) {
            j++;
        } else if (second - first > k) {
            i++;
        } else {
            count++;
            while (i < nums.length && nums[i] == first) {
                i++;
            }
            while (j < nums.length && nums[j] == second) {
                j++;
            }
        }
        if (i == j) {
            j++;
        }
    }
    return count;
}
```

### 2. HashMap
* use a map to record number - freq pairs
* if k == 0, for each entry, the value should be >= 2
* otherwise, for each entry, check if key + k exists in key set
* O(n) time, O(n) space
```Java
public int findPairs(int[] nums, int k) {
    if (nums.length == 0 || k < 0) {
        return 0;
    }
    int count = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        if (k == 0) {
            count += entry.getValue() > 1 ? 1 : 0;
        } else {
            count += map.containsKey(entry.getKey() + k) ? 1 : 0;
        }
    }
    return count;
}
```
