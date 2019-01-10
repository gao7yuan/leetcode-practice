# 557. Reverse Words in a String III
1/8/19
*Easy*

Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
```
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
```
Note: In the string, each word is separated by single space and there will not be any extra space in the string.

## Attempts
* Two pointer
```Java
public String reverseWords(String s) {
    if (s == null || s.length() == 0) {
        return s;
    }
    char[] c = s.toCharArray();
    int n = c.length;
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
    return new String(c);
}

private void reverse(char[] c, int i, int j) {
    while (i < j) {
        char tmp = c[i];
        c[i++] = c[j];
        c[j--] = tmp;
    }
}
```
