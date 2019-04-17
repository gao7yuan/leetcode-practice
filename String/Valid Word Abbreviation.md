# Valid Word Abbreviation
4/4/19
*Easy*

Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:
```
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
```
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
```
Given s = "internationalization", abbr = "i12iz4n":

Return true.
```
Example 2:
```
Given s = "apple", abbr = "a2e":

Return false.
```

## Attempts
```Java
public boolean validWordAbbreviation(String word, String abbr) {
    // if word is empty, already taken care of in general case
    // if (word.length() == 0) {
    //     // abbr has to be empty
    //     if (abbr.equals("")) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }
    // if word is not empty, abbr cannot be empty. this is included in general code already.
    // if (abbr.equals("")) {
    //     return false;
    // }
    char[] cword = word.toCharArray();
    char[] cabbr = abbr.toCharArray();
    int i = 0, j = 0;
    while (i < cword.length && j < cabbr.length) {
        // if chars are different
        if (cword[i] != cabbr[j]) {
            // if abbr is not a number, false
            if (!Character.isDigit(cabbr[j])) {
                return false;
            } else {
                // else, figure out what the number is
                int cnt = Character.getNumericValue(cabbr[j]);
                if (cnt == 0) {
                    return false;
                }
                j++;
                while (j < cabbr.length && Character.isDigit(cabbr[j])) {
                    cnt = cnt * 10 + Character.getNumericValue(cabbr[j]);
                    j++;
                }
                // skip cnt number of chars in word
                i += cnt;
            }
        } else {
            // if chars are the same, continue
            i++;
            j++;
        }
    }
    return i == cword.length && j == cabbr.length;
}
```

## Solution
```Java
public boolean validWordAbbreviation(String word, String abbr) {
    int i = 0, j = 0;
    char[] s = word.toCharArray();
    char[] t = abbr.toCharArray();

    while (i < s.length && j < t.length) {
        if (Character.isDigit(t[j])) {
            if (t[j] == '0') {
                return false;
            }
            int val = 0;
            while (j < t.length && Character.isDigit(t[j])) {
                val = val * 10 + t[j] - '0';
                // 如果val溢出
                if (val < 0) {
                  return false;
                }
                j++;
            }
            i += val;
        } else {
            if (s[i++] != t[j++]) {
                return false;
            }
        }
    }
    return i == s.length && j == t.length;
}
```
