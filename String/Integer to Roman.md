# 12. Integer to Roman
*Medium*
09/24/18

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
```
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

Example 1:
```
Input: 3
Output: "III"
```
Example 2:
```
Input: 4
Output: "IV"
```
Example 3:
```
Input: 9
Output: "IX"
```
Example 4:
```
Input: 58
Output: "LVIII"
Explanation: C = 100, L = 50, XXX = 30 and III = 3.
```
Example 5:
```
Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```

## Attempts
* 分情况：
1. digit为0, 1-3, 4, 5, 6-8, 9分情况
2. 位数为1, 2, 3, 4分情况 （用helper)
3. 遍历每一位数，从尾到头较方便，尾数为num % 10，除去尾数剩余的数为temp = num / 10。所有位数都cover到之后temp为0，即终止运算。
* 注意:
1. 9的处理方法
2. 0 必须考虑进去（什么都不做）
```
public String intToRoman(int num) {
    StringBuilder str = new StringBuilder();
    int temp = num;
    int tail;
    int curDigit = 1;
    while (temp > 0) {
        tail = temp % 10;
        temp /= 10;
        if (tail == 4) {
            str.append(fiveToRoman(curDigit))
                .append(oneToRoman(curDigit));
        } else if (tail == 9) {
            str.append(oneToRoman(curDigit + 1))
                .append(oneToRoman(curDigit));
        } else if (tail == 5) {
            str.append(fiveToRoman(curDigit));
        } else if (tail >=1 && tail <= 3) {
            str.append(smallNumToRoman(tail, curDigit));
        } else if (tail >=6 && tail <= 8) {
            str.append(smallNumToRoman(tail - 5, curDigit))
                .append(fiveToRoman(curDigit));
        }
        curDigit++;
    }
    String res = new StringBuffer(str.toString()).reverse().toString();
    return res;
}
```
  - 不同位数上堆叠“1”
```
String smallNumToRoman(int num, int curDigit) {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < num; i++) {
        str.append(oneToRoman(curDigit));
    }
    return str.toString();
}
```
  - 不同位数的1 -> Roman
```
String oneToRoman(int curDigit) {
    switch(curDigit) {
        case 1: return "I";
        case 2: return "X";
        case 3: return "C";
        case 4: return "M";
        default: return "";
    }
}
```
  - 不同位数的5 -> Roman
```
String fiveToRoman(int curDigit) {
    switch(curDigit) {
        case 1: return "V";
        case 2: return "L";
        case 3: return "D";
        default: return "";
    }
}
```

## Solutions
### Approach 1
* 换个思路，从位数入手。
* switch statement可以用String[]来代替，每一位的所有case都用String[]表示出来
```
public static String intToRoman(int num) {
    String M[] = {"", "M", "MM", "MMM"};
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
}
```
### Approach 2
* 从大到小做减法
```
public String intToRoman(int num) {

    int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    StringBuilder sb = new StringBuilder();

    for(int i=0;i<values.length;i++) {
        while(num >= values[i]) {
            num -= values[i];
            sb.append(strs[i]);
        }
    }
    return sb.toString();
}
```
