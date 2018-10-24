# 345. Reverse Vowels of a String
*Easy*
10/23/18

Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
```
Input: "hello"
Output: "holle"
```
Example 2:
```
Input: "leetcode"
Output: "leotcede"
```
Note:
The vowels does not include the letter "y".

## Attempts
* Two pointers
  - O(n) time, O(1) space
```
public String reverseVowels(String s) {
    if (s == null || s.length() == 0) {
        return s;
    }
    char[] chars = s.toCharArray();
    int start = 0;
    int end = chars.length - 1;
    while (start < end) {
        if (!isVowel(chars[start])) {
            start++;
        } else if (!isVowel(chars[end])) {
            end--;
        } else {
            swap(chars, start, end);
            start++;
            end--;
        }
    }
    return new String(chars);
}

boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
        || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
}

void swap(char[] chars, int a, int b) {
    char tmp = chars[a];
    chars[a] = chars[b];
    chars[b] = tmp;
}
```
