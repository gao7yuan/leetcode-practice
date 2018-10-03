# 61. Search for a Range
*Medium*
10/02/18

Given a sorted array of n integers, find the starting and ending position of a given target value.

If the target is not found in the array, return [-1, -1].

Example

Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

Challenge

O(log n) time.

```
public class Solution {
    /**
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        // write your code here
        int[] res = new int[2];
        if (A.length == 0) {
            res[0] = -1;
            res[1] = -1;
            return res;
        }

        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                end = mid;
            } else if (A[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (A[start] == target) {
            res[0] = start;
        } else if (A[end] == target) {
            res[0] = end;
        } else {
            res[0] = -1;
            res[1] = -1;
            return res;
        }

        start = 0;
        end = A.length - 1;

        while(start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                start = mid;
            } else if (A[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (A[end] == target) {
            res[1] = end;
        } else if (A[start] == target) {
            res[1] = start;
        } else {
            res[0] = -1;
            res[1] = -1;
            return res;
        }

        return res;
    }
}
```
