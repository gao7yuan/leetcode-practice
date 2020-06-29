# 53. Reverse Words in a String
*Easy*
10/08/18

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Clarification

What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.

* reverse O(1) space O(n) time
template:
```
for (int start = 0, int end = n - 1; start < end; start++, end--) {
  swap(A[start], A[end]);
}
```

* Attempt
  - note: regex: X+? once or more
```
public class Solution {
    /*
     * @param s: A string
     * @return: A string
     */
    public String reverseWords(String s) {
        // write your code here
        String[] arr = s.split(" +");
        int len = arr.length;
        if (len == 0) {
            return s;
        }
        for (int start = 0, end = len - 1; start < end; start++, end--) {
            swap(arr, start, end);
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < len; i++) {
            str.append(" ").append(arr[i]);
        }
        return str.toString().substring(1);
    }
    private void swap(String[] arr, int a, int b) {
        String temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
```
