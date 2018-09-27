# 67. Add Binary
*Easy*
09/25/18

Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:
```
Input: a = "11", b = "1"
Output: "100"
```
Example 2:
```
Input: a = "1010", b = "1011"
Output: "10101"
```

## Attempts
* 注意点：
  - 如果sum <= 1 carry = 0
  - 补首位
```
public String addBinary(String a, String b) {
    StringBuilder res = new StringBuilder();
    int carry = 0;
    int digitA, digitB, sum;
    int i = 0;
    for (; i < Math.min(a.length(), b.length()); i++) {
        digitA = Character.getNumericValue(a.charAt(a.length() - i - 1));
        digitB = Character.getNumericValue(b.charAt(b.length() - i - 1));
        sum = digitA + digitB + carry;
        if (sum > 1) carry = 1;
        else carry = 0;
        sum = sum % 2;
        res.append(sum);
    }
    if (a.length() > b.length()) {
        for (; i < a.length(); i++) {
            digitA =
                Character.getNumericValue(a.charAt(a.length() - i - 1));
            sum = digitA + carry;
            if (sum > 1) carry = 1;
            else carry = 0;
            sum = sum % 2;
            res.append(sum);
        }
    } else {
        for (; i < b.length(); i++) {
            digitB =
                Character.getNumericValue(b.charAt(b.length() - i - 1));
            sum = digitB + carry;
            if (sum > 1) carry = 1;
            else carry = 0;
            sum = sum % 2;
            res.append(sum);
        }
    }
    res.append(carry == 1 ? "1" : "");

    return new StringBuffer(res.toString()).reverse().toString();
}
```

## Solutions
* much more elegant code
  - **note**: ```sb.reverse()```
```
public String addBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int i = a.length() - 1, j = b.length() -1, carry = 0;
    while (i >= 0 || j >= 0) {
        int sum = carry;
        if (j >= 0) sum += b.charAt(j--) - '0';
        if (i >= 0) sum += a.charAt(i--) - '0';
        sb.append(sum % 2);
        carry = sum / 2;
    }
    if (carry != 0) sb.append(carry);
    return sb.reverse().toString();
}
```
