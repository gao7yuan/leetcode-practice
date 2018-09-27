# 165. Compare Version Numbers
*Medium*
09/26/18

Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Example 1:
```
Input: version1 = "0.1", version2 = "1.1"
Output: -1
```
Example 2:
```
Input: version1 = "1.0.1", version2 = "1"
Output: 1
```
Example 3:
```
Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1
```

## Attempts
* 注意的问题：
  - ```version1.split()```
  - regex: ```"\\."``` for dot (.)
```
public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");
    int num1, num2;
    int i = 0;
    for (; i < Math.min(v1.length, v2.length); i++) {
        num1 = Integer.parseInt(v1[i]);
        num2 = Integer.parseInt(v2[i]);
        if (num1 > num2) return 1;
        else if (num1 < num2) return -1;
        else continue;
    }
    if (v1.length > v2.length) {
        for (; i < v1.length; i++) {
            if (Integer.parseInt(v1[i]) != 0) return 1;
        }
        return 0;
    }
    else if (v1.length < v2.length) {
        for (; i < v2.length; i++) {
            if (Integer.parseInt(v2[i]) != 0) return -1;
        }
        return 0;
    }
    else return 0;
}
```

## Solutions
* 类似思路
  - 不同的是for loop取最大长度为loop次数，当任意一个array超出长度的时候对应的值用0代替
  - 用Java的```compareTo```来比较两个数的大小
  - Interface Comparable<T>: ```compareTo``` return 1, -1 or 0
```
public int compareVersion(String version1, String version2) {
    String[] levels1 = version1.split("\\.");
    String[] levels2 = version2.split("\\.");

    int length = Math.max(levels1.length, levels2.length);
    for (int i=0; i<length; i++) {
    	Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
    	Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
    	int compare = v1.compareTo(v2);
    	if (compare != 0) {
    		return compare;
    	}
    }

    return 0;
}
```
