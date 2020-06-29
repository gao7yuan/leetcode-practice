# 326. Power of Three
5/14/19
*Easy*

Given an integer, write a function to determine if it is a power of three.

Example 1:
```
Input: 27
Output: true
```
Example 2:
```
Input: 0
Output: false
```
Example 3:
```
Input: 9
Output: true
```
Example 4:
```
Input: 45
Output: false
```
Follow up:
Could you do it without using any loop / recursion?

## Attempts
- note edge case 0.
```Java
public boolean isPowerOfThree(int n) {
    while (n > 1) {
        if (n % 3 != 0) {
            return false;
        }
        n /= 3;
    }
    return n == 1;
}
```

## Solutions
1. Loop
```Java
public class Solution {
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}
```
2. base conversion. base 3
3. n = 3^i -> i = logn / log3
