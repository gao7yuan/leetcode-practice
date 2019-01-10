# 186. Reverse Words in a String II
1/8/19
*Medium*

Given an input string , reverse the string word by word.

Example:
```
Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
```
Note:

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?

## Attempts
* Two pointer
```Java
public void reverseWords(char[] str) {
    if (str == null || str.length == 0) {
        return;
    }
    int n = str.length;
    reverse(str, 0, n - 1);
    reverseWords(str, n);
}

private void reverse(char[] c, int i, int j) {
    while (i < j) {
        char tmp = c[i];
        c[i++] = c[j];
        c[j--] = tmp;
    }
}

private void reverseWords(char[] c, int n) {
    int i = 0, j = 0;
    while (i < n) {
        while (i < j || i < n && c[i] == ' ') {
            i++;
        }
        while (j < i || j < n && c[j] != ' ') {
            j++;
        }
        reverse(c, i, j - 1);
    }
}
```
