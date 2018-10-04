# 64. Merge Sorted Array
*Easy*
10/03/18

Given two sorted integer arrays A and B, merge B into A as one sorted array.

Example

A = [1, 2, 3, empty, empty], B = [4, 5]

After merge, A will be filled as [1, 2, 3, 4, 5]

Notice

You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.

* 从后往前填补
  - O(m + n) time
```
public class Solution {
    /*
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        if (n <= 0) {
            return;
        }
        int ind1 = m - 1;
        int ind2 = n - 1;
        int k = m + n - 1;
        int a, b;
        while (ind1 >= 0 || ind2 >= 0) {
            a = ind1 >= 0 ? A[ind1] : Integer.MIN_VALUE;
            b = ind2 >= 0 ? B[ind2] : Integer.MIN_VALUE;
            if (a > b) {
                A[k] = a;
                ind1--;
            } else {
                A[k] = b;
                ind2--;
            }
            k--;
        }
    }
}
```
