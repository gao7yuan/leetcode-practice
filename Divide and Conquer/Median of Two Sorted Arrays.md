# 4. Median of Two Sorted Arrays
*Hard*
10/01/18

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:
```
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
```
Example 2:
```
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
```

## Solutions
* Divide and conquer, binary search
  - Arrays A and B of length m and n
  - What is the role of medium
>> Dividing the set into two equal length subsets, where one set is always small thant the other.

  - Cut A at position i, 0 <= i <= m, so that
    - len(left_A) = i (A[0...i-1])
    - len(right_A) = m - i (A[i...m-1])
  - Same with B.
    - len(left_B) = j
    - len(right_B) = n - j

  - To find medium, ensure
  1. len(left) == len(right) if m + n is even, and len(left) == len(right) + 1 if m + n is odd
  2. max(left) <= min(right)
  - Then medium = (max(left) + min(right)) / 2 if m + n is even, and max(left) if m + n is odd

  - To ensure these two conditions
  1. i + j = m - i + n - j (or m - i + n - j + 1 if total is odd)
    - for n >= m, j = (m + n + 1) / 2 - i for both odd and even, because ```(m + n + 1) / 2``` is **floored**.
  2. A[i - 1] <= B[j] && B[j - 1] <= A[i]
    - Did not consider i = 0 or i = m or j = 0 or j = n

  - Do a binary search
    - imin = 0, imax = m, i = (imin + imax) / 2, j = (m + n + 1) / 2 - i.
    - if A[i - 1] <= B[j] && B[j - 1] <= A[i], stop searching
    - if A[i - 1] > B[j], i too large. search left part, imax = i - 1
    - if B[j - 1] > A[i], i too small. search right part, imin = i + 1
  - if total is odd, medium = max(A[i - 1], B[j - 1])
  - if total is even, medium = (max(A[i - 1], B[j - 1]) + min(A[i], B[j])) / 2

  - Edge values i = 0 , i = m, j = 0, j = n
    - if one of A[i - 1], A[i], B[j - 1], B[j] doesn't exist, then we just need to check one of the two conditions
    - find i in [0, m] such that
    1. B[j - 1] <= A[i] or B[j - 1] or A[i] does not exist (j == 0 or i == m)
    and
    2. A[i - 1] <= B[j] or A[i - 1] or B[j] does not exist (i == 0 or j == n)

  - Therefore in the loop we encounter these three conditions:
  1. (j == 0 or i == m or B[j - 1] <= A[i]) and (j == n or i == 0 or A[i - 1] <= B[j])
  2. j > 0 and i < m and B[j - 1] > A[i] (in fact, i < m ==> j > 0)
  3. j < n and i > 0 and A[i - 1] > B[j] (in fact, i > 0 ==> j < n)

```
public double findMedianSortedArrays(int[] A, int[] B) {
    int m = A.length;
    int n = B.length;
    if (m > n) { // to ensure m<=n
        int[] temp = A; A = B; B = temp;
        int tmp = m; m = n; n = tmp;
    }
    int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
    while (iMin <= iMax) {
        int i = (iMin + iMax) / 2;
        int j = halfLen - i;
        if (i < iMax && B[j-1] > A[i]){
            iMin = i + 1; // i is too small
        }
        else if (i > iMin && A[i-1] > B[j]) {
            iMax = i - 1; // i is too big
        }
        else { // i is perfect
            int maxLeft = 0;
            if (i == 0) { maxLeft = B[j-1]; }
            else if (j == 0) { maxLeft = A[i-1]; }
            else { maxLeft = Math.max(A[i-1], B[j-1]); }
            if ( (m + n) % 2 == 1 ) { return maxLeft; }

            int minRight = 0;
            if (i == m) { minRight = B[j]; }
            else if (j == n) { minRight = A[i]; }
            else { minRight = Math.min(B[j], A[i]); }

            return (maxLeft + minRight) / 2.0;
        }
    }
    return 0.0;
}
```
