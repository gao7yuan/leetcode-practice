# 191. Number of 1 Bits
*Easy*
09/30/18

Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).

Example 1:
```
Input: 11
Output: 3
Explanation: Integer 11 has binary representation 00000000000000000000000000001011
```
Example 2:
```
Input: 128
Output: 1
Explanation: Integer 128 has binary representation 00000000000000000000000010000000
```

## Attempts
* Wrong answer
  - in Java, integer is 32 bits. max of unsigned integer is 2147483647. integer type in Java is signed, so 2147483648 is represented in Java as -2147483648 (Integer.MAX_VALUE+1==Integer.MIN_VALUE)
```
// you need to treat n as an unsigned value
public int hammingWeight(long n) {
    int count = 0;
    long number = (long) n;
    while (number != 0) {
        if (number % 2 == 1) count++;
        number /= 2;
    }
    return count;
}
```

## Solutions
### Approach 1
* bitwise ```AND``` operation to see if the most insignificant digit is 1.
* use unsiged shift ```>>>``` to shift bits to the right for one bit.
* use ```n!= 0``` instead of ```n > 0``` because 2147483648 is represented as -2147483648.
```
public static int hammingWeight(int n) {
	int ones = 0;
    	while(n!=0) {
    		ones = ones + (n & 1);
    		n = n>>>1;
    	}
    	return ones;
}
```
### Approach 2: Loop and flip
* use a mask to check each bit, left shift the mask till the 32 bit
  - O(k) time, k = bits, k = 32 so O(1) time. O(1) space
```
public int hammingWeight(int n) {
    int bits = 0;
    int mask = 1;
    for (int i = 0; i < 32; i++) {
        if ((n & mask) != 0) {
            bits++;
        }
        mask <<= 1;
    }
    return bits;
}
```
### Approach 3: Bit manipulation (flip least significant 1 to 0)
* ```n & n - 1``` flips the least significant 1 of n to 0
  - O(1) time, O(1) space
```
public int hammingWeight(int n) {
    int sum = 0;
    while (n != 0) {
        sum++;
        n &= (n - 1);
    }
    return sum;
}
```
