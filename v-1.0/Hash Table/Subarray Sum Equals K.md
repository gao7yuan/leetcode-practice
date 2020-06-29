# 560. Subarray Sum Equals K
1/31/19
*Medium* *二刷* *见谷歌面经描述*

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
```
Input:nums = [1,1,1], k = 2
Output: 2
```
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

## Attempts
- Brute force
  - O(n^2) time, O(1) space
```Java
public int subarraySum(int[] nums, int k) {
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
        int sum = 0;
        for (int j = i; j < nums.length; j++) {
            sum += nums[j];
            if (sum == k) {
                count++;
            }
        }
    }
    return count;
}
```

## Solutions
- Hashmap
  - Idea:
    - if sum up to two index points are the same, then the sum of numbers between the two indices is zero
    - if difference of sum between sum[i] - sum[j] = k, then sum of elements between indices i and j is k
    - use a hashmap to store all cumulative sums up to all indices & num of times the same sum occurs
    - O(n) time, O(n) space
```Java
public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>(); // sum - occurrence pairs
    int sum = 0;
    int count = 0;
    map.put(0, 1); // nothing added, sum = 0
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        if (map.containsKey(sum - k)) {
            count += map.get(sum - k);
        }
        if (map.containsKey(sum)) {
            map.put(sum, map.get(sum) + 1);
        } else {
            map.put(sum, 1);
        }
    }
    return count;
}
```
