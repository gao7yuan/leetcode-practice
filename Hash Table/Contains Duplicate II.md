# 219. Contains Duplicate II
*Easy* *已整理*
08/26/18

Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:
```
Input: nums = [1,2,3,1], k = 3
Output: true
```
Example 2:
```
Input: nums = [1,0,1,1], k = 1
Output: true
```
Example 3:
```
Input: nums = [1,2,3,1,2,3], k = 2
Output: false
```

## Attempts
* Use a hash table to keep track of numbers in ```nums```, use a stack to store all the indices.
```
public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Stack<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexMap.containsKey(nums[i])) {
                if (i - indexMap.get(nums[i]).peek() <= k) {
                    return true;
                } else {
                    indexMap.get(nums[i]).push(i);
                }
            } else {
                Stack<Integer> stack = new Stack<Integer>();
                stack.push(i);
                indexMap.put(nums[i], stack);
            }
        }
        return false;
    }
```

## Solutions
* Use a set to keep track of all elements in the index range of k.
* If an index is out of range, remove the previous element.
* Add each element. If success, it means this element does not have a duplicate in the range of k. If not, return true.

* Java note: set.add() returns a boolean!
```
public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
 }
```
