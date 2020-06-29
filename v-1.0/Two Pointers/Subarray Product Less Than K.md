# 713. Subarray Product Less Than K
12/18/18
*Medium*

Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
```
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
```
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.

## Attempts
* Brute force (time limit exceeded)
```Java
public int numSubarrayProductLessThanK(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
        int product = 1;
        for (int j = i; j < nums.length; j++) {
            product *= nums[j];
            if (product >= k) {
                break;
            }
            count++;
        }
    }
    return count;
}
```

## Solutions
### 1. Binary search
* log of product = sum of log
* longest subarray [i, j] with product < k, number of subarrays = width of [i, j]
```Java
public int numSubarrayProductLessThanK(int[] nums, int k) {
    if (nums.length == 0 || k == 0) {
        return 0;
    }
    int count = 0;
    double logk = Math.log(k);
    double[] sum = new double[nums.length + 1];
    for (int i = 1; i <= nums.length; i++) {
        sum[i] = sum[i - 1] + Math.log(nums[i - 1]);
    }
    for (int i = 0; i < nums.length; i++) {
        int start = i + 1;
        int end = nums.length;
        int mid;
        double toFind = sum[i] + logk - 1e-9; // don't know why
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (sum[mid] >= toFind) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (sum[end] < toFind) {
            count += end - i;
        } else if (sum[start] < toFind) {
            count += start - i;
        }
    }
    return count;
}
```
## 2. Sliding window, two pointers
* find largest windows [i, j] with product < k
* cnt += j - i + 1 for cnt of subarrays ending at j with product < k
* O(n) time
```Java
public int numSubarrayProductLessThanK(int[] nums, int k) {
    if (nums.length == 0 || k <= 1) {
        return 0;
    }
    int count = 0;
    int prod = 1;
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
        prod *= nums[j];
        while (i <= j && prod >= k) {
            prod /= nums[i++];
        }
        count += j - i + 1;
    }
    return count;
}
```
