# 151. Reverse Words in a String
1/8/19
*Medium*

Given an input string, reverse the string word by word.

Example:  
```
Input: "the sky is blue",
Output: "blue is sky the".
```
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
Follow up: For C programmers, try to solve it in-place in O(1) space.

## Attempts
* Two pointer
  - `end` points to end of a word, `start` points start of a word.
  - `end--` until finds a non `' '` character, that is the last char of a word.
  - `start = end` then `start--` until finds a character with previous char as `' '`, that is the first char of a word
  - Note the case where s == ' '
  - O(2n) = O(n) time, O(n) space
```Java
public String reverseWords(String s) {
    if (s == null || s.length() == 0) {
        return s;
    }
    StringBuilder str = new StringBuilder();
    int end = s.length() - 1;
    int start = end;
    while (start >= 0 && end >= 0) {
        // find the last character of the last word
        while (end > 0 && s.charAt(end) == ' ') {
            end--;
        }
        if (s.charAt(end) == ' ') {
            break;
        }
        start = end;
        // find the first character of the last word
        while (start > 0 && s.charAt(start - 1) != ' ') {
            start--;
        }
        // append the last word to str
        str.append(s.substring(start, end + 1));
        str.append(" ");
        end = start - 1;
    }
    String string = str.toString();
    return string.length() > 0 ? string.substring(0, string.length() - 1) : "";
}
```

## Solutions
* Two pointer, no StringBuilder

```Java
public String reverseWords(String s) {
  if (s == null) return null;

  char[] a = s.toCharArray();
  int n = a.length;

  // step 1. reverse the whole string
  reverse(a, 0, n - 1);
  // step 2. reverse each word
  reverseWords(a, n);
  // step 3. clean up spaces
  return cleanSpaces(a, n);
}

void reverseWords(char[] a, int n) {
  int i = 0, j = 0;
  // i: start of a word. j: end of a word.
  // corner case: " "
  // n = 1
  // i -> 1, j -> 1
  // reverse(a, 1, 0), which does nothing
  while (i < n) {
    while (i < j || i < n && a[i] == ' ') i++; // skip spaces
    while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
    reverse(a, i, j - 1);                      // reverse the word
  }
}

// trim leading, trailing and multiple spaces
String cleanSpaces(char[] a, int n) {
  int i = 0, j = 0;

  while (j < n) {
    while (j < n && a[j] == ' ') j++;             // skip spaces
    while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
    while (j < n && a[j] == ' ') j++;             // skip spaces
    if (j < n) a[i++] = ' ';                      // keep only one space
  }

  return new String(a).substring(0, i);
}

// reverse a[] from a[i] to a[j]
private void reverse(char[] a, int i, int j) {
  while (i < j) {
    char t = a[i];
    a[i++] = a[j];
    a[j--] = t;
  }
}
```
