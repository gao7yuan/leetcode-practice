# 360. Sort Transformed Array
2/19/19
*Medium*

Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example 1:
```
Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]
```
Example 2:
```
Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
```

## Attempts
- brute force
  - O(nlgn) time, O(n) space to store results
```Java
public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
    int n = nums.length;
    int res[] = new int[n];
    for (int i = 0; i < n; i++) {
        res[i] = a * nums[i] * nums[i] + b * nums[i] + c;
    }
    Arrays.sort(res);
    return res;
}
```

## Solutions
- f(x) = ax^2 + bx + c is a parabolic.
  - a > 0 -> min at x = -a/2b. at two ends values are bigger
  - a < 0 -> max at x = -a/2b. at two ends values are smaller
  - a == 0 -> linear, depends on b > 0 or b < 0
- O(n) time, O(n) space  

- my code
```Java
public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
    int n = nums.length;
    int[] res = new int[n];
    // apply quadratic function to original array
    for (int i = 0; i < n; i++) {
        nums[i] = a * nums[i] * nums[i] + b * nums[i] + c;
    }
    if (a > 0) {
        int left = 0;
        int right = n - 1;
        int index = n - 1;
        while (left <= right) {
            if (nums[left] > nums[right]) {
                res[index--] = nums[left++];
            } else {
                res[index--] = nums[right--];
            }
        }
    } else if (a < 0) {
        int left = 0;
        int right = n - 1;
        int index = 0;
        while (left <= right) {
            if (nums[left] < nums[right]) {
                res[index++] = nums[left++];
            } else {
                res[index++] = nums[right--];
            }
        }
    } else {
        for (int i = 0; i < n; i++) {
            if (b > 0) {
                res[i] = nums[i];
            } else {
                res[i] = nums[n - i - 1];
            }
        }
    }
    return res;
}
```
- better code of mine
  - note `left <= right` condition
```Java
public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
    int n = nums.length;
    int[] res = new int[n];
    int left = 0;
    int right = n - 1;
    int index = a >= 0 ? n - 1 : 0;
    if (a >= 0) {
        while (left <= right) {
            if (quadratic(a, b, c, nums[left]) > quadratic(a, b, c, nums[right])) {
                res[index--] = quadratic(a, b, c, nums[left++]);
            } else {
                res[index--] = quadratic(a, b, c, nums[right--]);
            }
        }
    } else {
        while (left <= right) {
            if (quadratic(a, b, c, nums[left]) < quadratic(a, b, c, nums[right])) {
                res[index++] = quadratic(a, b, c, nums[left++]);
            } else {
                res[index++] = quadratic(a, b, c, nums[right--]);
            }
        }
    }
    return res;
}

private int quadratic(int a, int b, int c, int x) {
    return a * x * x + b * x + c;
}
```
