# 65. Valid Number
6/16/19
*Hard*

Validate if a given string can be interpreted as a decimal number.

Some examples:
```
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false
```

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.

## Solutions (九章)
- 思路
  - 一个数怎么构成：
    - 符号 + 浮点数 + e + 符号 + 整数
    - 浮点数必须: e.g. 3.56
    - ( )(+-)3.56(e(+-)5)( )
  - O(n) time

```Java
public boolean isNumber(String s) {
    int i = 0;
    s = s.trim() + " "; // get rid of leading and ending white space. " " used to limit i within length
    char[] sc = s.toCharArray();
    int len = sc.length - 1;

    if (sc[i] == '+' || sc[i] == '-') {
        i++;
    }

    int nDigit = 0, nPoint = 0;
    while (Character.isDigit(sc[i]) ||sc[i] == '.') {
        if (Character.isDigit(sc[i])) {
            nDigit++;
        }
        if (sc[i] == '.') {
            nPoint++;
        }
        i++;
    }
    if (nDigit <= 0 || nPoint > 1) {
        return false;
    }

    if (sc[i] == 'e') {
        i++;
        if (sc[i] == '+' || sc[i] == '-') {
            i++;
        }
        // if after e, there is no number, false
        if (i == len) {
            return false;
        }
        for (; i < len; i++) {
            if (!Character.isDigit(sc[i])) {
                return false;
            }
        }
    }
    return i == len;
}
```
