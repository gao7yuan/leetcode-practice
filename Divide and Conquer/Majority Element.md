# 169. Majority Element
*Easy*
10/01/18

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
```
Input: [3,2,3]
Output: 3
```
Example 2:
```
Input: [2,2,1,1,1,2,2]
Output: 2
```

## Attempts
* hashmap （不能用array因为int可能是负数）
  - O(n) time, O(n) space
  - test通过了，发现没有仔细审题，题目要求是出现次数大于n/2。但是因为这个方法return的是次数最多的元素所以ok
```
public int majorityElement(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
        if (map.containsKey(num)) map.put(num, map.get(num) + 1);
        else map.put(num, 1);
    }
    int max = Integer.MIN_VALUE;
    int maj = Integer.MIN_VALUE;
    for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
        if (pair.getValue() > max) {
            max = pair.getValue();
            maj = pair.getKey();
        }
    }
    return maj;
}
```

## Solutions
### 1. Brute force
* O(n^2) time, O(1) space
### 2. Hashmap
* 和我的类似
### 3. Sorting
* sort the array first, and then find the element with index n / 2
  - O(nlgn) time, O(1) or O(n) space depending on whether sorting in place
### 4. Random
* O(infinity) time
### 5. Divide and conquer
* Recursively find the majority of left and right. If left != right then count their occurrences.
  - O(nlgn) time, O(lgn) space due to stack
```
public int majorityElement(int[] nums) {
    return majorityRecursion(nums, 0, nums.length - 1);
}

private int count(int[] nums, int num, int lo, int hi) {
    int counts = 0;
    for (int i = lo; i <= hi; i++) {
        if (nums[i] == num) counts++;
    }
    return counts;
}

private int majorityRecursion(int[] nums, int lo, int hi) {
    if (lo == hi) return nums[lo];
    int mid = lo + (hi - lo) / 2;
    int leftMaj = majorityRecursion(nums, lo, mid);
    int rightMaj = majorityRecursion(nums, mid + 1, hi);
    if (leftMaj == rightMaj) return leftMaj;
    int leftCount = count(nums, leftMaj, lo, mid);
    int rightCount = count(nums, rightMaj, mid + 1, hi);
    return leftCount > rightCount ? leftMaj : rightMaj;
}
```
### 6. Boyer-Moore Voting Algorithm
* 从第一个数开始，每次遇到这个数+1，否则-1，每次到0的时候换成新的数。。。
  - O(n) time, O(1) space
```
public int majorityElement(int[] nums) {
    int count = 0;
    Integer candidate = null;

    for (int num : nums) {
        if (count == 0) {
            candidate = num;
        }
        count += (num == candidate) ? 1 : -1;
    }

    return candidate;
}
```
