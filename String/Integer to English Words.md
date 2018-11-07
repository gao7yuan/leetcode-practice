# 273. Integer to English Words
*Hard*
10/30/18

Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:
```
Input: 123
Output: "One Hundred Twenty Three"
```
Example 2:
```
Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
```
Example 3:
```
Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
```
Example 4:
```
Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
```

## Solutions
* 每三位数分一组(num %= 1000)，用helper转换成string. num < 1000
  - 0: ""
  - <20: 1-19 的数都可以直接用word表示因为无规律可循
  - <100: e.g. fifty one. TENS[num / 10] + recursively call this function(num % 10)
  - <1000: e.g. five hundred twenty two. LESS_THAN_20[num / 100] + " hundred " + recursively call(num % 100)注意空格
* 用i记录几个1000。注意THOUSANDS Array里面要有""
* 每个千位 call helper(num % 1000)当做千位单位的前缀。加上THOUSANDS[i].再加上前一次处理千位时的结果
* 处理下一个千位之前num /= 1000

```
private final String[] LESS_THAN_20 = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
private final String[] TENS = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
private final String[] THOUSANDS = new String[]{"", "Thousand", "Million", "Billion"};

public String numberToWords(int num) {
    if (num == 0) {
        return "Zero";
    }
    int i = 0;
    String word = "";
    while (num > 0) {
        if (num % 1000 != 0) {
            word = helper(num % 1000) + THOUSANDS[i] + " " + word;
        }
        i++;
        num /= 1000;
    }
    return word.trim();
}

private String helper(int num) {
    if (num == 0) {
        return "";
    } else if (num < 20) {
        return LESS_THAN_20[num] + " ";
    } else if (num < 100) {
        return TENS[num / 10] + " " + helper(num % 10);
    } else {
        return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
    }
}
```
