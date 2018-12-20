# 925. Long Pressed Name
12/20/18
*Easy*

Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.



Example 1:
```
Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
```
Example 2:
```
Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
```
Example 3:
```
Input: name = "leelee", typed = "lleeelee"
Output: true
```
Example 4:
```
Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.
```

## Attempts
* one pass
* O(n) time, n is length of typed
```Java
public boolean isLongPressedName(String name, String typed) {
    if (name.length() > typed.length()) {
        return false;
    }
    for (int i = 0, j = 0; i < name.length(); i++) {
        if (j >= typed.length()) {
            return false;
        }
        if (name.charAt(i) != typed.charAt(j)) {
            return false;
        }

        while (i < name.length() - 1 && name.charAt(i) != name.charAt(i + 1)
            && j < typed.length() - 1 && typed.charAt(j) == typed.charAt(j + 1)) {
            j++;
        }
        j++;
    }
    return true;
}
```

## Solutions
### 1. Group into blocks
* For each letter in strings, record char-freq with a linked list
* check the count of the second string is always greater than the first one
* O(n+t) time, O(n+t) space

### 2. Two pointer
* O(n+t) time, O(1) space
