# 7. Reverse Integer
*Easy*
10/22/18

Given a 32-bit signed integer, reverse digits of an integer.

Example 1:
```
Input: 123
Output: 321
```
Example 2:
```
Input: -123
Output: -321
```
Example 3:
```
Input: 120
Output: 21
```
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

## Attempts
* 从末尾起将数字加入结果，每次乘以10并且加上下一位数字。
  - O(n) time (n是数字位数), or O(lgx) time, x 是数的大小。O(1) space
```
public int reverse(int x) {
    long lx = (long) x;
    int sign = lx >= 0 ? 1 : -1;
    lx = lx >= 0 ? lx : (-lx);

    long reversed = 0;
    while (lx > 0) {
        reversed *= 10;
        reversed += lx % 10;
        lx /= 10;
    }
    reversed *= sign;
    if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
        reversed = 0;
    }
    return (int) reversed;
}
```
## Solutions
* 不用long，每一步测overflow```(result != (temp - last) / 10)```
```
public int reverse(int x) {
    int result = 0;
    while (x != 0) {
        int last = x % 10;
        int temp = result * 10 + last;
        if (result != (temp - last) / 10) {
            return 0;
        }
        result = temp;
        x /= 10;
    }
    return result;
}
```
