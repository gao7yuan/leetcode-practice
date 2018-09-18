# 28. Implement strStr()
*Easy*
08/29/18

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:
```
Input: haystack = "hello", needle = "ll"
Output: 2
```
Example 2:
```
Input: haystack = "aaaaa", needle = "bba"
Output: -1
```
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

## Attempts
* 参考了一些discussion
* Use 2 pointers: i and j. i - the head of each substring. j - iterate through all the chars in the substring. i from 0 to threshold, where threshold is l1 - l2. j from i to i + l2.
* If any char does not equal to corresponding char in needles, break.
* *Note terminating condition*: ```j == i + l2```. If all chars equal, ```j++``` is operated last. so ```j == i + l2```. If the last char does not equal, we ```break``` the code block, so we do not execute ```j++``` at last.
* O(n^2) time.
```
public int strStr(String haystack, String needle) {
    int l1 = haystack.length();
    int l2 = needle.length();
    if (l1 < l2) return -1;
    if (l2 == 0) return 0;
    int threshold = l1 - l2;
    int j = 0;
    for (int i = 0; i <= threshold; i++) {
        for (j = i; j < i + l2; j++) {
            if (haystack.charAt(j) != needle.charAt(j - i)) {
                break;
            }
        }
        if (j == i + l2) return i;
    }
    return -1;
}
```

## Solutions
### Approach 1: Brute force
* Same as above
  - O(k*n) time, k - length of haystack, n - length of needle.
```
public int strStr(String haystack, String needle) {
  for (int i = 0; ; i++) {
    for (int j = 0; ; j++) {
      if (j == needle.length()) return i;
      if (i + j == haystack.length()) return -1;
      if (needle.charAt(j) != haystack.charAt(i + j)) break;
    }
  }
}
```
* If we can use Java built-in methods:
```
public int strStr(String haystack, String needle) {
    int l1 = haystack.length(), l2 = needle.length();
    if (l1 < l2) {
        return -1;
    } else if (l2 == 0) {
        return 0;
    }
    int threshold = l1 - l2;
    for (int i = 0; i <= threshold; ++i) {
        if (haystack.substring(i, i+l2).equals(needle)) {
            return i;
        }
    }
    return -1;
}
```
### Approach 2: KMP ???
* Knuth-Morris-Pratt (KMP) algorithm
> The Knuth–Morris–Pratt string-searching algorithm (or KMP algorithm) searches for occurrences of a "word" W within a main "text string" S by employing the observation that when a mismatch occurs, the word itself embodies sufficient information to determine where the next match could begin, thus bypassing re-examination of previously matched characters.
> A string-matching algorithm wants to find the starting index m in string S[] that matches the search word W[].

* String S of length k, search for Word W of length n, straightforward method results in O(k*n) time.
* KMP: makes use of previous match info that the straightforward algorithm does not.
[KMP] (https://en.wikipedia.org/wiki/Knuth–Morris–Pratt_algorithm "KMP algorithm")
* Assumes the existence of a "partial match" table T.
>The entries of T are constructed so that if we have a match starting at S[m] that fails when comparing S[m + i] to W[i], then the next possible match will start at index m + i - T[i] in S (that is, T[i] is the amount of "backtracking" we need to do after a mismatch).
> This has two implications: first, T[0] = -1, which indicates that if W[0] is a mismatch, we cannot backtrack and must simply check the next character; and second, although the next possible match will begin at index m + i - T[i], as in the example above, we need not actually check any of the T[i] characters after that, so that we continue searching from W[T[i]].

* Assuming the prior existence of table ```T```, KMPL O(n) time.
