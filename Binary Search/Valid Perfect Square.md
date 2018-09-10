# 367. Valid Perfect Square
*Easy*
08/28/18

Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:
```
Input: 16
Output: true
```
Example 2:
```
Input: 14
Output: false
```

## Attempts
* Binary search, similar as **Sqrt(x)**.
* Note overflow - should use ```long``` instead of ```int```. Also note that should not use ```m == num / m``` because division between integers would truncate the decimals.
* One thing to note is that we have to use long for mid to avoid ```mid*mid``` from overflow. Also, you can use long type for low and high to avoid type casting for mid from long to int.
```
public boolean isPerfectSquare(int num) {
    long i = 1;
    long j = (long) num;
    while (i <= j) {
        long m = i + (j - i) / 2;
        if (m * m < num) i = m + 1;
        else if (m * m > num) j = m - 1;
        else return true;
    }
    return false;
}
```

## Solutions
* A square number is 1+3+5+7+...
  - O(sqrt(n)) time.
```
public boolean isPerfectSquare(int num) {
     int i = 1;
     while (num > 0) {
         num -= i;
         i += 2;
     }
     return num == 0;
 }
```
* Binary search
  - O(logn) time.
* Note:
   - ```>>>``` operator is the **unsigned right bit-shift operator** in Java. It effectively divides the operand by 2 to the power of the right operand.
```
public boolean isPerfectSquare(int num) {
        int low = 1, high = num;
        while (low <= high) {
            long mid = (low + high) >>> 1;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        return false;
    }
```
* Newton's method
```
public boolean isPerfectSquare(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }
```      
