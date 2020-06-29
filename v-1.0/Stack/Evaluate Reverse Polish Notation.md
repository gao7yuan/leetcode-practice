# 150. Evaluate Reverse Polish Notation
7/8/19
*Medium*

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are `+`, `-`, `*`, `/`. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:
```
Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
```
Example 2:
```
Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
```
Example 3:
```
Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation:
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
```

## Attempts
- use a stack

```Java
public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();
    int i = 0;
    while (i < tokens.length) {
        while (i < tokens.length && isOperator(tokens[i])) {
            int second = stack.pop();
            int first = stack.pop();
            int result = 0;
            if (tokens[i].equals("+")) {
                result = first + second;
            }
            if (tokens[i].equals("-")) {
                result = first - second;
            }
            if (tokens[i].equals("*")) {
                result = first * second;
            }
            if (tokens[i].equals("/")) {
                result = first / second;
            }
            stack.push(result);
            i++;
        }
        if (i < tokens.length) {
            stack.push(Integer.parseInt(tokens[i++]));
        }
    }
    return stack.pop();
}

boolean isOperator(String s) {
    return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
}
```
