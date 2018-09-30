# 179. Largest Number
*Medium*
09/29/18

Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:
```
Input: [10,2]
Output: "210"
```
Example 2:
```
Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
```

## Attempts
* 用comparator规定string大小，排序 （错了，没有debug出来）
```
public String largestNumber(int[] nums) {
    if (nums.length == 0) return "";
    String cur;
    int curNum;
    StringBuilder str = new StringBuilder();
    StringCompare comp = new StringCompare();
    for (int i = 1; i < nums.length; i++) {
        curNum = nums[i];
        cur = Integer.toString(nums[i]);
        int j = i - 1;
        while (j >= 0
               && comp.compare(cur, Integer.toString(nums[j])) == 1) {
            nums[j + 1] = nums[j];
            j--;
        }
        nums[j + 1] = curNum;
    }

    for (int i = 0; i < nums.length; i++) {
        str.append(nums[i]);
    }
    return str.toString();
}

class StringCompare implements Comparator<String> {

    public int compare(String str1, String str2) {
        if (str1.equals("")) return -1;
        else if (str2.equals("")) return 1;
        int num1, num2;
        for (int i = 0; i < Math.max(str1.length(), str2.length()); i++) {
            num1 = i < str1.length() ?
                Character.valueOf(str1.charAt(i)) : str1.charAt(0);
            num2 = i < str2.length() ?
                Character.valueOf(str2.charAt(i)) : str2.charAt(0);
            if (num1 > num2) return 1;
            else if (num1 < num2) return -1;
        }
        return 0;
    }
}
```

## Solutions
* 一样的思路
  - O(nlgn) time (sort), O(n) space to store strings.

  - 注意当最大数是0的时候return "0"
```
public String largestNumber(int[] nums) {
    if (nums.length == 0) return "0";
    StringBuilder str = new StringBuilder();
    StringCompare comp = new StringCompare();
    String[] strings = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
        strings[i] = Integer.toString(nums[i]);
    }
    Arrays.sort(strings, comp);
    if (Integer.parseInt(strings[0]) == 0) return "0";
    for (int i = 0; i < nums.length; i++) {
        str.append(strings[i]);
    }
    return str.toString();
}
```
  - Comparator简便写法。注意str1比str2大的时候return -1
```
class StringCompare implements Comparator<String> {

    public int compare(String str1, String str2) {
        String order1 = str1 + str2;
        String order2 = str2 + str1;
        return order2.compareTo(order1);
    }
}
```
