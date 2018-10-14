# 29. Divide Two Integers
*Medium*
10/12/18

Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:
```
Input: dividend = 10, divisor = 3
Output: 3
```
Example 2:
```
Input: dividend = 7, divisor = -3
Output: -2
```
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.

## Attempts
* Binary search
  - Recursion
  - O(lgn) time, space??
```
    public int divide(int dividend, int divisor) {
        // deal with sign of final result
        int sign = 1;
        if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) {
            sign = -1;
        }
        // use long to avoid overflow
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        // deal with edge cases
        if (ldividend == 0 || ldividend < ldivisor) {
            return 0;
        }
        // calculate regular cases
        long lans = ldivide(ldividend, ldivisor);
        // assign sign to final result
        lans = sign == 1 ? lans : -lans;
        // deal with overflow
        if (lans > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (lans < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) lans;
    }

    private long ldivide(long ldividend, long ldivisor) {
        // base case for recursion
        if (ldividend < ldivisor) {
            return 0;
        }
        long sum = ldivisor;
        long multiplier = 1;
        while (sum + sum <= ldividend) {
            sum += sum;
            multiplier += multiplier;
        }
        return multiplier + ldivide(ldividend - sum, ldivisor);
    }
```
  - Iterative
```
private long ldivide(long ldividend, long ldivisor) {
    // base case for recursion
    if (ldividend < ldivisor) {
        return 0;
    }
    long sum, multiplier;
    long total = ldividend;
    long accumulator = 0;
    while (total >= ldivisor) {
        multiplier = 1;
        sum = ldivisor;
        while (sum + sum <= total) {
            sum += sum;
            multiplier += multiplier;
        }
        total -= sum;
        accumulator += multiplier;
    }
    return accumulator;
}
```

## Solutions
* Binary search
  - can use bitwise operation << (x2)
  - Two cases may cause overflow:
  1. divisor = 0;
  2. dividend = INT_MIN and divisor = -1 (because abs(INT_MIN) = INT_MAX + 1).
```
public int divide(int dividend, int divisor) {
    int sign = dividend < 0 ^ divisor < 0 ? -1 : 1;
    if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }
    long ldvd = Math.abs((long) dividend);
    long ldvs = Math.abs((long) divisor);
    int res = 0;
    while (ldvd >= ldvs) {
        long sum = ldvs;
        long multiplier = 1;
        while (sum << 1 <= ldvd) {
            sum <<= 1;
            multiplier <<= 1;
        }
        res += multiplier;
        ldvd -= sum;
    }
    return sign == 1 ? res : -res;
}
```
