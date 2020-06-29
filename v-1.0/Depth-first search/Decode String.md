# 394. Decode String
12/26/18
*Medium*

Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:
```
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
```

## Solutions
* Stack
  - `res` 一层层展开结果。
  - O(n) time, O(n) space
```Java
public String decodeString(String s) {
    StringBuilder res = new StringBuilder();
    Stack<Integer> countStack = new Stack<>();
    Stack<StringBuilder> resStack = new Stack<>();
    int index = 0;
    while (index < s.length()) {
        if (Character.isDigit(s.charAt(index))) {
            int count = 0;
            while (Character.isDigit(s.charAt(index))) {
                count = 10 * count + s.charAt(index) - '0';
                index++;
            }
            countStack.push(count);
        } else if (s.charAt(index) == '[') {
            resStack.push(res);
            res = new StringBuilder();
            index++;
        } else if (s.charAt(index) == ']') {
            StringBuilder temp = new StringBuilder(resStack.pop());
            int count = countStack.pop();
            for (int i = 0; i < count; i++) {
                temp.append(res);
            }
            res = temp;
            index++;
        } else {
            res.append(s.charAt(index++));
        }
    }
    return res.toString();
}
```
