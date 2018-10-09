# 50. Pow(x, n)
*Medium*
10/08/18

Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:
```
Input: 2.00000, 10
Output: 1024.00000
```
Example 2:
```
Input: 2.10000, 3
Output: 9.26100
```
Example 3:
```
Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
```
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]

## Attempts
* Brute force (超时)
```
public double myPow(double x, int n) {
    if (n == 0) {
        return 1;
    }
    double res = 1.0;
    if (n > 0) {
        for (int i = 0; i < n; i++) {
            res *= x;
        }
    } else {
        for (int i = 0; i < (0 - n); i++) {
            res *= (1 / x);
        }
    }
    return res;
}
```
  - handle edge cases 还是超时
    - O(n) time, O(1) space
```
public double myPow(double x, int n) {
    long N = n;
    if (n < 0) {
        x = 1 / x;
        N = -N;
    }
    double res = 1;
    for (long i = 0; i < N; i++) {
        res *= x;
    }
    return res;
}
```

## Solutions
### 1. Fast power algorithm recursive (divide and conquer)
* O(lgn) time, O(lgn) space (call stack)
```
public double myPow(double x, int n) {
    long N = n;
    if (N < 0) {
        x = 1 / x;
        N = -N;
    }
    return fastPow(x, N);
}
private double fastPow(double x, long n) {
    if (n == 0) {
        return 1.0;
    }
    double half = fastPow(x, n / 2);
    if (n % 2 == 0) {
        return half * half;
    } else {
        return half * half * x;
    }
}
```
### 2. Fast power algorithm iterative
* O(n) time, O(1) space
```
public double myPow(double x, int n) {
    long N = n;
    if (N < 0) {
        N = -N;
        x = 1 / x;
    }
    double res = 1.0;
    double cur = x;
    for (long i = N; i > 0; i = i / 2) {
        if (i % 2 == 1) {
            res *= cur;
        }
        cur = cur * cur;
    }
    return res;
}
```
