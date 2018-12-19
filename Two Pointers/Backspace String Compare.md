# 844. Backspace String Compare
12/19/18
*Easy*

Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:
```
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
```
Example 2:
```
Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
```
Example 3:
```
Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
```
Example 4:
```
Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
```
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?

## Solutions
### 1. Stack
* 看到字符串和backspace等，想到stack
* build两个stack，construct两个string，比较是否一样。甚至不需要construct string，直接pop stack比较是否一样
* O(n+m) time, O(n+m) space
```Java
public boolean backspaceCompare(String S, String T) {
    Stack<Character> s = buildStack(S);
    Stack<Character> t = buildStack(T);

    if (s.size() != t.size()) {
        return false;
    }

    while (!s.isEmpty()) {
        if (s.pop() != t.pop()) {
            return false;
        }
    }

    return true;
}

private Stack<Character> buildStack(String S) {
    Stack<Character> s = new Stack<>();
    for (int i = 0; i < S.length(); i++) {
        char c = S.charAt(i);
        if (c != '#') {
            s.push(c);
        } else {
            if (!s.isEmpty()) {
                s.pop();
            }
        }
    }
    return s;
}
```
### 2. Two pointers
* start from end, ignore one letter for one #
```Java
public boolean backspaceCompare(String S, String T) {
    int i = S.length() - 1;
    int j = T.length() - 1;
    int sSkip = 0;
    int tSkip = 0;
    while (i >= 0 || j >= 0) {
        while (i >= 0) {
            if (S.charAt(i) == '#') {
                sSkip++;
                i--;
            } else if (sSkip > 0) {
                sSkip--;
                i--;
            } else {
                break;
            }
        }
        while (j >= 0) {
            if (T.charAt(j) == '#') {
                tSkip++;
                j--;
            } else if (tSkip > 0) {
                tSkip--;
                j--;
            } else {
                break;
            }
        }
        if ((i >= 0) != (j >= 0)) {
            return false;
        }
        if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) {
            return false;
        }
        i--;
        j--;
    }
    return true;
}
```
