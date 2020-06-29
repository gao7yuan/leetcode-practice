# 38. Count and Say
*Easy*
09/25/18

The count-and-say sequence is the sequence of integers with the first five terms as following:
```
1.     1
2.     11
3.     21
4.     1211
5.     111221
```
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.



Example 1:
```
Input: 1
Output: "1"
```
Example 2:
```
Input: 4
Output: "1211"
```

## Attempts after 看答案
* 开始尝试用an array of strings来储存，但是赋值给array中已存在的string的时候遇到了string index out of bound的问题
* 看了答案发现不需要array，只要用variable记录上一次的string就可以了。。。
  - ```curr```当下的string，每个iteration重新赋值
  - ```prev```上一次的string
  - ```say```追踪目前在寻找的重复的数字，每个iteration从第一个char开始
  - ```count```目前寻找的数字的重复个数
```
public String countAndSay(int n) {
    StringBuilder curr = new StringBuilder("1");
    StringBuilder prev;
    char say; // current char in the prev string we are comparing
    // do this till nth line
    for (int i = 1; i < n; i++) {
        int count = 1;
        prev = curr;
        say = prev.charAt(0);
        curr = new StringBuilder();
        // iterate all the chars in prev string
        for (int j = 1; j < prev.length(); j++) {
            if (prev.charAt(j) == say) {
                count++;
            } else {
                curr.append(count).append(say);
                say = prev.charAt(j);
                count = 1;
            }
        }
        curr.append(count).append(say);
    }
    return curr.toString();
}
```
