# 6. Merge Two Sorted Arrays
*Easy*
10/03/18

Merge two given sorted integer array A and B into a new sorted integer array.

Example

A=[1,2,3,4]

B=[2,4,5,6]

return [1,2,2,3,4,4,5,6]

Challenge

How can you optimize your algorithm if one array is very large and the other is very small?

```
public class Solution {
    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int len1 = A.length;
        int len2 = B.length;
        if (len1 == 0) {
            return B;
        }
        if (len2 == 0) {
            return A;
        }
        int[] res = new int[len1 + len2];
        int i = 0;
        int j = 0;
        int k = 0;
        int a, b;
        while (i < len1 || j < len2) {
            a = i < len1 ? A[i] : Integer.MAX_VALUE;
            b = j < len2 ? B[j] : Integer.MAX_VALUE;
            if (a < b) {
                res[k] = a;
                i++;
            } else {
                res[k] = b;
                j++;
            }
            k++;
        }
        return res;
    }
}
```

* Challenge
可以用二分法处理较大数组，copy连续内存较快，从算法角度run time类似
