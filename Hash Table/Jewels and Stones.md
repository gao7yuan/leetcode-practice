# 771. Jewels and Stones
*Easy*
08/24/18

You're given strings ```J``` representing the types of stones that are jewels, and ```S``` representing the stones you have.  Each character in ```S``` is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in ```J``` are guaranteed distinct, and all characters in ```J``` and ```S``` are letters. Letters are case sensitive, so ```"a"``` is considered a different type of stone from ```"A"```.

**Example 1:**
```
Input: J = "aA", S = "aAAbbbb"
Output: 3
```
**Example 2:**
```
Input: J = "z", S = "ZZ"
Output: 0
```
**Note:**

* ```S``` and ```J``` will consist of letters and have length at most 50.
* The characters in ```J``` are distinct.

## Attempts
* use a set to keep track of all jewels, and check the number of occurrences of each jewel in stone.
```
public int numJewelsInStones(String J, String S) {
    int num = 0;
    Set<Character> jewelSet = new HashSet<>();
    for (int i = 0; i < J.length(); i++) {
        jewelSet.add(J.charAt(i));
    }
    for (int i = 0; i < S.length(); i++) {
        if (jewelSet.contains(S.charAt(i))) {
            num++;
        }
    }
    return num;     
}
```

## Solutions
* 这个有点吊。。。
  - 将所有非J里面的字符换成空字符，求新字符串长度=。=
```
public int numJewelsInStones(String J, String S) {
    return S.replaceAll("[^" + J + "]", "").length();
}
```
* Typical的方法和我一样，note:
```char j : J.toCharArray()```
