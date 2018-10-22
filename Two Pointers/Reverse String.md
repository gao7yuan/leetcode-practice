# 344. Reverse String
*Easy*
10/20/18

Write a function that takes a string as input and returns the string reversed.

Example 1:
```
Input: "hello"
Output: "olleh"
```
Example 2:
```
Input: "A man, a plan, a canal: Panama"
Output: "amanaP :lanac a ,nalp a ,nam A"
```

## Attempts
* Recursion: stack overflow
* Iteration from end to start
```
public String reverseString(String s) {
    if (s == null || s.length() == 0) {
        return s;
    }
    StringBuilder str = new StringBuilder();
    for (int i = s.length() - 1; i >= 0; i--) {
        str.append(s.charAt(i));
    }
    return str.toString();
}
```
