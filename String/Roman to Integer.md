# 13. Roman to Integer
*Easy*
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
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:
```
Input: "III"
Output: 3
```
Example 2:
```
Input: "IV"
Output: 4
```
Example 3:
```
Input: "IX"
Output: 9
```
Example 4:
```
Input: "LVIII"
Output: 58
Explanation: C = 100, L = 50, XXX = 30 and III = 3.
```
Example 5:
```
Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```

## Attempts
* 用map存放罗马数字和阿拉伯数字的对应关系。逆向处理。如果后一个char比前一个char对应的数字小，则做减法，否则做加法。
```
public int romanToInt(String s) {
    String reverse = new StringBuffer(s).reverse().toString();
    Map<Character, Integer> map = new HashMap<>();
    int[] nums = {1000, 500, 100, 50, 10, 5, 1};
    char[] romans = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    int num = 0;
    boolean add = true;
    for (int i = 0; i < 7; i++) {
        map.put(romans[i], nums[i]);
    }
    for (int i = 0; i < reverse.length(); i++) {
        num = add ?
            num + map.get(reverse.charAt(i))
            : num - map.get(reverse.charAt(i));
        if (i < reverse.length() - 1
            && map.get(reverse.charAt(i))
            > map.get(reverse.charAt(i + 1))) {
            add = false;
        } else {
            add = true;
        }
    }
    return num;
}
```

## Solutions
* 将所有的char加起来，如果有4或9则减2，以此类推。
  - Note:
    - ```indexOf()```
    - ```toCharArray()```
```
public int romanToInt(String s) {
     int sum=0;
    if(s.indexOf("IV")!=-1){sum-=2;}
    if(s.indexOf("IX")!=-1){sum-=2;}
    if(s.indexOf("XL")!=-1){sum-=20;}
    if(s.indexOf("XC")!=-1){sum-=20;}
    if(s.indexOf("CD")!=-1){sum-=200;}
    if(s.indexOf("CM")!=-1){sum-=200;}

    char c[]=s.toCharArray();
    int count=0;

   for(;count<=s.length()-1;count++){
       if(c[count]=='M') sum+=1000;
       if(c[count]=='D') sum+=500;
       if(c[count]=='C') sum+=100;
       if(c[count]=='L') sum+=50;
       if(c[count]=='X') sum+=10;
       if(c[count]=='V') sum+=5;
       if(c[count]=='I') sum+=1;

   }

   return sum;

}
```
* 和我的方法思路一样
  - 先将罗马数字用```switch statement```转换成阿拉伯数字存放在array里面
  - array里面如果后一个数比前一个数大则减，否则加。**由此想到我的方法不需要逆序，正序的话前一个字母对应的数字如果比后一个小则减，否则加。但是要先判定。**
```
public int romanToInt(String s) {
    int nums[]=new int[s.length()];
    for(int i=0;i<s.length();i++){
        switch (s.charAt(i)){
            case 'M':
                nums[i]=1000;
                break;
            case 'D':
                nums[i]=500;
                break;
            case 'C':
                nums[i]=100;
                break;
            case 'L':
                nums[i]=50;
                break;
            case 'X' :
                nums[i]=10;
                break;
            case 'V':
                nums[i]=5;
                break;
            case 'I':
                nums[i]=1;
                break;
        }
    }
    int sum=0;
    for(int i=0;i<nums.length-1;i++){
        if(nums[i]<nums[i+1])
            sum-=nums[i];
        else
            sum+=nums[i];
    }
    return sum+nums[nums.length-1];
}
```
* 改进后的我的方法
```
public int romanToInt(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int[] nums = {1000, 500, 100, 50, 10, 5, 1};
    char[] romans = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    int num = 0;
    boolean add = true;
    for (int i = 0; i < 7; i++) {
        map.put(romans[i], nums[i]);
    }
    for (int i = 0; i < s.length(); i++) {
        if (i < s.length() - 1
            && map.get(s.charAt(i))
            < map.get(s.charAt(i + 1))) {
            add = false;
        } else {
            add = true;
        }
        num = add ?
            num + map.get(s.charAt(i))
            : num - map.get(s.charAt(i));

    }
    return num;
}
```
