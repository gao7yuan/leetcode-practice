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
  - O(n) time, iterate the whole string from back to start. O(n) space to store all chars
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

## Solutions
* Two pointers - swap
  - O(n) time, O(n) space - used char array to store
```Java
public String reverseString(String s) {
    if (s == null || s.length() == 0) {
        return s;
    }
    char[] chars = s.toCharArray();
    int i = 0;
    int j = s.length() - 1;
    while (i < j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        i++;
        j--;
    }
    return new String(chars);
}
```
