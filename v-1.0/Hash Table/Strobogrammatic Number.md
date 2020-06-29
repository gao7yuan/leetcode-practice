# 246. Strobogrammatic Number
7/8/19
*Easy*

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:
```
Input:  "69"
Output: true
```
Example 2:
```
Input:  "88"
Output: true
```
Example 3:
```
Input:  "962"
Output: false
```

## Attempts
- convert num to char array and reverse.
- reversed: if not 0, 1, 8, 6, 9, or first digit is 0, return false
- reversed: 6->9, 9->6
- compared resulted.
```Java
public boolean isStrobogrammatic(String num) {
    if (num == null || num.length() == 0) {
        return true;
    }
    char[] stro = num.toCharArray();
    swap(stro, 0, stro.length - 1);
    for (int i = 0; i < stro.length; i++) {
        if ((stro[i] != '1' && stro[i] != '8' && stro[i] != '9' && stro[i] != '6' && stro[i] != '0')
            || (i == 0 && stro[i] == '0')) {
            return false;
        }
        if (stro[i] == '9') {
            stro[i] = '6';
        } else if (stro[i] == '6') {
            stro[i] = '9';
        }
    }
    char[] ori = num.toCharArray();
    for (int i = 0; i < ori.length; i++) {
        if (ori[i] != stro[i]) {
            return false;
        }
    }
    return true;
}

void swap(char[] arr, int i, int j) {
    while (i < j) {
        char temp = arr[i];
        arr[i++] = arr[j];
        arr[j--] = temp;
    }
}
```

## Solutions
- hashtable
```Java
public boolean isStrobogrammatic(String num) {
    Map<Character, Character> map = new HashMap<Character, Character>();
    map.put('6', '9');
    map.put('9', '6');
    map.put('0', '0');
    map.put('1', '1');
    map.put('8', '8');

    int l = 0, r = num.length() - 1;
    while (l <= r) {
        if (!map.containsKey(num.charAt(l))) return false;
        if (map.get(num.charAt(l)) != num.charAt(r))
            return false;
        l++;
        r--;
    }

    return true;
}
```
