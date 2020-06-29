# 1071. Greatest Common Divisor of Strings
6/1/19
*Easy*

For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.



Example 1:
```
Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
```
Example 2:
```
Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
```
Example 3:
```
Input: str1 = "LEET", str2 = "CODE"
Output: ""
```

Note:

1 <= str1.length <= 1000
1 <= str2.length <= 1000
str1[i] and str2[i] are English uppercase letters.

## Solutions
### 1. 暴力按照定义
- `possible`从0开始一次check string中的char，while相同则加入possible。
- GCD
  - 总长度是gcd的倍数
  - gcd乘以倍数==string
- gcd长度l, gcd乘以倍数也有复杂度。。。
```Java
public String gcdOfStrings(String str1, String str2) {
    StringBuilder possible = new StringBuilder();
    String ans = "";
    String longs = str1.length() > str2.length() ? str1 : str2;
    String shorts = str1.length() <= str2.length() ? str1 : str2;
    int l1 = longs.length();
    int l2 = shorts.length();
    int i = 0;
    while (i < shorts.length() && longs.charAt(i) == shorts.charAt(i)) {
        possible.append(longs.charAt(i));
        int l = possible.length();
        if (l1 % l == 0 && l2 % l == 0) {
            int n1 = l1 / l;
            int n2 = l2 / l;
            StringBuilder p1 = new StringBuilder();
            StringBuilder p2 = new StringBuilder();
            for (int j = 0; j < n1; j++) {
                p1.append(possible);
            }
            for (int j = 0; j < n2; j++) {
                p2.append(possible);
            }
            if (p1.toString().equals(longs) && p2.toString().equals(shorts)) {
                ans = possible.toString();
            }
        }
        i++;
    }
    return ans;
}
```

### 2.
将较短string平均分成若干份，i = 1, 2, ...
每次先check长度可以被较长string整除，若可以，则检查substring是否分别是两个string的divisor
```Java
public String gcdOfStrings(String str1, String str2) {
    int l1 = str1.length();
    int l2 = str2.length();
    String longs = l1 > l2 ? str1 : str2;
    String shorts = l1 <= l2 ? str1 : str2;
    int longl = longs.length();
    int shortl = shorts.length();
    int i = 1; // divide short string into i pieces
    while (i <= shortl) {
        if (shortl % i != 0 || longl % (shortl / i) != 0) {
            i++;
            continue;
        }
        int len = shortl / i; // length of gcd
        int j = longl / len; // num of divides in long string
        String l = "";
        String s = "";
        for (int k = 0; k < i; k++) {
            s += shorts.substring(0, len);
        }
        for (int k = 0; k < j; k++) {
            l += shorts.substring(0, len);
        }
        if (s.equals(shorts) && l.equals(longs)) {
            return shorts.substring(0, len);
        }
        i++;
    }
    return "";
}
```
