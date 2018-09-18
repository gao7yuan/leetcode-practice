# 69. Sqrt(x)
*Easy* *已整理*
08/27/18

Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:
```
Input: 4
Output: 2
```
Example 2:
```
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.
```

## Attempts
* Binary search
  - each iteration should do ```left = mid + 1``` or ```right = mid - 1``` but did ```left++``` or ```right--```
  - be aware of corner cases

## Solutions
* Binary search
```
public int sqrt(int x) {
    if (x == 0)
        return 0;
    int left = 1, right = x/2;
    while (true) {
        int mid = left + (right - left)/2;  // or: (left + right) / 2
        if (mid > x/mid) {
            right = mid - 1;
        } else {
            if (mid + 1 > x/(mid + 1))
                return mid;
            left = mid + 1;
        }
    }
}
```
* Newton's method
```
long r = x;
while (r*r > x)
    r = (r + x/r) / 2;
return (int) r;
```
