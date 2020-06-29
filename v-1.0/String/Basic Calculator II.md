# 227. Basic Calculator II
4/17/19
*Medium*

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, `+`, `-`, `*`, `/` operators and empty spaces . The integer division should truncate toward zero.

Example 1:
```
Input: "3+2*2"
Output: 7
```
Example 2:
```
Input: " 3/2 "
Output: 1
```
Example 3:
```
Input: " 3+5 / 2 "
Output: 5
```
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.

## Attempts
- 当遇到乘除时，需要结合前面的数，所以可以考虑用stack
```Java
public int calculate(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    Stack<Integer> stack = new Stack<>(); // store all the numbers to add together
    int len = s.length();
    int num = 0; // the number to push into stack
    char sign = '+'; // the sign before number
    int res = 0;
    for (int i = 0; i < len; i++) {
        // if char is digit, add to num
        if (Character.isDigit(s.charAt(i))) {
            num = num * 10 + s.charAt(i) - '0';
        }
        // if char is not digit and is not a whitespace, or is at the end of the string
        // add something to the stack according to sign, and update sign
        if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == len - 1) {
            // +
            if (sign == '+') {
                stack.push(num);
            }
            // -
            if (sign == '-') {
                stack.push(-num);
            }
            // *
            if (sign == '*') {
                stack.push(stack.pop() * num);
            }
            // /
            if (sign == '/') {
                stack.push(stack.pop() / num);
            }
            sign = s.charAt(i);
            num = 0;
        }
    }
    for (int n : stack) {
        res += n;
    }
    return res;
}
```
