# 1078. Occurrences After Bigram
6/10/19
*Easy*

Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.

For each such occurrence, add "third" to the answer, and return the answer.



Example 1:
```
Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
Output: ["girl","student"]
```
Example 2:
```
Input: text = "we will we will rock you", first = "we", second = "will"
Output: ["we","rock"]
```

Note:

1 <= text.length <= 1000
text consists of space separated words, where each word consists of lowercase English letters.
1 <= first.length, second.length <= 10
first and second consist of lowercase English letters.

## Attempts
- String method: `indexOf(first, 3)` - find the starting index of the first occurrence of first after or equal to index 3
- `ind` the index after or equal to which we are searching `first` and `second`
- while we did not reach the end of `text` and we can still find `first`
  - find index of `first` after `ind` -> `f`
  - find index of `second` after `f` -> `s`
  - if they meet the requirement, `f - s == first.length() + 1`
    - build up `third`
    - note edge case when we reach the end of `text`
    - `ind = s + second.length()` we move on to find the next
  - otherwise, they are not a pair, `ind = f + first.length()`
- O(n) time
```Java
public String[] findOcurrences(String text, String first, String second) {
    List<String> res = new ArrayList<>();
    int f = 0;
    int s = 0;
    int ind = 0;
    while (ind < text.length() && text.indexOf(first, ind) >= 0) {
        f = text.indexOf(first, ind);
        s = text.indexOf(second, f);
        if (s - f == first.length() + 1) {
            // found a <first second>: find third, and update ind
            StringBuilder third = new StringBuilder();
            int c = s + second.length() + 1;
            while (c < text.length() && text.charAt(c) != ' ') {
                third.append(text.charAt(c));
                c++;
            }
            if (third.length() != 0) {
                res.add(third.toString());
            }  
            ind = s + second.length();
        } else {
            // did not find
            ind = f + first.length();
        }
    }
    int size = res.size();
    String[] ret = new String[size];
    for (int i = 0; i < size; i++) {
        ret[i] = res.get(i);
    }
    return ret;
}
```

## Solutions
```Java
public String[] findOcurrences(String text, String first, String second) {
    String[] t = text.split(" ");
    int size = t.length;
    List<String> res = new ArrayList<>();
    for (int i = 2; i < size; i++) {
        if (t[i - 2].equals(first) && t[i - 1].equals(second)) {
            res.add(t[i]);
        }
    }
    return res.toArray(new String[0]);
}
```
- There is another brute force method on leetcode.
