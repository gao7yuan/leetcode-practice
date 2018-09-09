# 136. Single Number
*Easy*
08/26/18

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:
```
Input: [2,2,1]
Output: 1
```
Example 2:
```
Input: [4,1,2,1,2]
Output: 4
```

## Attempts
* Hash table: O(n) time, O(n) space.
```
public int singleNumber(int[] nums) {
    Map<Integer, Integer> numMap = new HashMap<>();
    for (int num : nums) {
        if (numMap.containsKey(num)) {
            numMap.put(num, numMap.get(num) + 1);
        } else {
            numMap.put(num, 1);
        }
    }
    for (Map.Entry<Integer, Integer> pair : numMap.entrySet()) {
        if (pair.getValue().equals(1)) {
            return pair.getKey();
        }
    }
    return -1;
}
```

## Solutions
### Approach 1
* List Operation
  - Iterate over all elements in ```nums```
  - If some number in ```nums``` is new to array, append it
  - If some number is already in the array, remove it
  - O(n^2) time, O(n) space.
### Approach 2
* Hash Table (based on Python)
  - Iterate all elements in ```nums```
  - Try if hash table has the key for ```pop```  
  - If not, set up key/value pair
  - The only element is the result.
* For Java, can use Set.
* O(n) time, O(n) space.
* My Java implementation:
```
public int singleNumber(int[] nums) {
    Set<Integer> numSet = new HashSet<>();
    for (int num : nums) {
        if (numSet.contains(num)) {
            numSet.remove(num);
        } else {
            numSet.add(num);
        }
    }
    for (Integer num : numSet) {
        return num;
    }
    return -1;
}
```
### Approach 3
* Math
  - 2*(a+b+c)-(a+a+b+b+c)=c
  - O(n) time: iterate all elements to populate a set of ```nums```.
  - O(n) space: need space for elements in ```nums```.
### Approach 4
* Bit Manipulation
  - XOR
  - a XOR 0 = a
  - a XOR a = 0
  - a XOR b XOR a = (a XOR a) XOR b = 0 XOR b = b
  - Can XOR all bits together to find the unique number
  - O(n) time, O(1) space.  
