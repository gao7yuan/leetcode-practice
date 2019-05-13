# 528. Random Pick with Weight
5/12/19
*Medium*

Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:
```
Input:
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
```
Example 2:
```
Input:
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
```
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.

## Attempts
- Prefix Sum and Binary Search
- 思路：random没办法改变，只能改变每个index出现的次数，naive的方法是将index按照权重平铺开，但是空间复杂度会很大
- 减小空间复杂度：用和input相同长度的array，存放到此index为止所有权重之和。random出来的数范围是1~总权重，用binary search查找。
- example:
  - array: [1, 2, 1]
  - count: [1, 3, 4]
  - rand产生1~4范围内的随机数，用二分法查找产生的数所在的index
  - 注意：二分法中，若没有相等的数，则round up index
- Time: O(n) preprocessing, O(lgn) to find
- Space: O(n)

```Java
class Solution {

    // array to record sum of weights up to this index
    private int[] count;
    private Random rand = new Random();

    public Solution(int[] w) {
        count = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            if (i == 0) {
                count[i] = w[i];
            } else {
                count[i] = count[i - 1] + w[i];
            }
        }
    }

    public int pickIndex() {
        int len = count.length;
        int max = count[len - 1];
        int randNum = rand.nextInt(max) + 1; // to find
        return binarySearch(randNum);
    }

    // given a number, find its index in count array. 1 <= number <= max
    private int binarySearch(int num) {
        int lo = 0;
        int hi = count.length - 1;
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (count[mid] < num) {
                // if count[mid] < num, we don't want this index for sure
                lo = mid + 1;
            } else if (count[mid] > num) {
                // if count[mid] > num, we might still want this index
                hi = mid;
            } else {
                return mid;
            }
        }
        // lo == hi
        return lo;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
 ```
