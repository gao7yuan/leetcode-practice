# 60. Search Insert Position
*Easy*
10/02/18

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume NO duplicates in the array.

Example

[1,3,5,6], 5 → 2

[1,3,5,6], 2 → 1

[1,3,5,6], 7 → 4

[1,3,5,6], 0 → 0

Challenge

O(log(n)) time

* 找第一个 >= target的位置
```
public class Solution {
    /**
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
        if (A.length == 0) return 0;
        if (A[0] > target) return 0;

        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (A[start] >= target) {
            return start;
        } else if (A[end] >= target) {
            return end;
        } else {
            return end + 1; // 不要忘记这个情况
        }
    }
}
```
* or: 找最后一个 < target的位置
```
public class Solution {
    /**
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
        if (A.length == 0) return 0;
        if (A[0] > target) return 0;

        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (A[end] == target) {
            return end;
        } else if (A[end] < target) {
            return end + 1;
        } else if (A[start] == target) {
            return start;
        } else {
            return start + 1;
        }
    }
}
```
* 二分法转化成：first/last position of...
