# 20. Valid Parentheses
*Easy*
08/31/18

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:
```
Input: "()"
Output: true
```
Example 2:
```
Input: "()[]{}"
Output: true
```
Example 3:
```
Input: "(]"
Output: false
```
Example 4:
```
Input: "([)]"
Output: false
```
Example 5:
```
Input: "{[]}"
Output: true
```

## Solutions
* Stack
  - 遍历string，每次遇到前半括号则把相应的后半括号push进stack。如果遇到后半括号则pop stack并且检查是否是相同字符。因为对于后遇到的前半括号，应该先遇到它相应的后半括号。所以遇到后半括号pop的结果和字符不相等则不是valid字符串。或者如果此时stack为空，则也不少valid字符串。iterate完后如果stack非空则也非valid。
```
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
        if (c == '(') stack.push(')');
        else if (c == '[') stack.push(']');
        else if (c == '{') stack.push('}');
        else if (stack.isEmpty() || stack.pop() != c) return false;
    }
    return stack.isEmpty();
}
```
