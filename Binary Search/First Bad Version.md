# 278. First Bad Version
08/28/18

You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:
```
Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version.
```

## Attempts
* Binary search, similar as **Sqrt(x)**.
* Notes:
  - Be aware of base case such as n = 1 or the first one is bad version.
  - **Unless using Python, ```m = (i + j) / 2``` can cause overflow. Use ```m = i + (j - i) / 2``` instead.**
  - **One quick way to test terminating condition is to test if size = 2 will terminate.**
```
public int firstBadVersion(int n) {
    if (n == 1) {
        if (isBadVersion(1)) {
            return 1;
        } else {
            return 0;
        }
    }
    if (isBadVersion(1)) {
        return 1;
    }
    int i = 1;
    int j = n;
    int m = i + (j - i) / 2;
    while (true) {
        m = i + (j - i) / 2;
        if (isBadVersion(m)) {
            if (!isBadVersion(m - 1)) {
                return m;
            }
            j = m;
        } else {
            i = m + 1;
        }
    }  
}
```

## Solutions
* Linear scan: O(n) time, O(1) space - time limit exceeded
* Binary search
  - left = 1, right = n
  - if mid is bad, search [left, mid]
  - if mid is good, search [mid + 1, right]
  - Terminating condition: left and right meet. Test input size of 2.
```
public int firstBadVersion(int n) {
    int left = 1;
    int right = n;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (isBadVersion(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```  
