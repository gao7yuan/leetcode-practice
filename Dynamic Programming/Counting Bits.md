# 338. Counting Bits
*Medium*
09/30/18

Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:
```
Input: 2
Output: [0,1,1]
```
Example 2:
```
Input: 5
Output: [0,1,1,2,1,2]
```
Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like ```__builtin_popcount``` in c++ or in any other language.

## Attempts
* Straightforward: for each number between 0 and num, follow the procedure to calculate its binary, count number of 1's in its binary
  - time: O(n*sizeof(integer)). n iteration in for loop, maximum sizeof(integer) iteration in while loop
```
public int[] countBits(int num) {
    int[] arr = new int[num + 1]; // the array to store number of 1's
    int count; // count 1's
    int number; // the number to convert to binary
    for (int i = 0; i <= num; i++) {
        count = 0; // initialize count to 0
        number = i;
        while (number != 0) {
            if (number % 2 == 1) count++;
            number /= 2;
        }
        arr[i] = count;
    }
    return arr;
}
```

## Solutions
>> Pop count, most significant bit, least significant bit, last set bit, dynamic programming

### 1. Pop count
* see as the followup of ```Problem 191 The number of 1 bits```, counts the bits of an unsigned integer. The number is often callled pop count.
  - This is similar to my solution, but counting 1's is implemented in a different way.
  - O(nk) time, for each integer x, we need O(k) operations where k is the number of bits in x.
  - O(n) space to store the result.
```
public int[] countBits(int num) {
    int[] ans = new int[num + 1];
    for (int i = 0; i <= num; ++i)
        ans[i] = popcount(i);
    return ans;
}
```
```
private int popcount(int x) {
    int count;
    for (count = 0; x != 0; ++count)
      x &= x - 1; //zeroing out the least significant nonzero bit
    return count;
}
```
### 2. DP + Most significant bit
* p(x) - popcount. p(x+b) = p(x) + 1, b = 2^m > x
  - O(n) time, O(n) space
```
public int[] countBits(int num) {
    int[] ans = new int[num + 1];
    int i = 0, b = 1;
    // [0, b) is calculated
    while (b <= num) {
        // generate [b, 2b) or [b, num) from [0, b)
        while(i < b && i + b <= num){
            ans[i + b] = ans[i] + 1;
            ++i;
        }
        i = 0;   // reset i
        b <<= 1; // b = 2b
    }
    return ans;
}
```
### 3. DP + Least significant bit
* p(x) = p(x/2) + (x mod 2)
```
public int[] countBits(int num) {
    int[] ans = new int[num + 1]; // default value of ans[0] is 0
    for (int i = 1; i <= num; ++i)
      ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
    return ans;
}
```
### 4. DP + last set bit
* p(x) = p(x & (x-1)) + 1
```
public int[] countBits(int num) {
    int[] ans = new int[num + 1];
    for (int i = 1; i <= num; ++i)
      ans[i] = ans[i & (i - 1)] + 1;
    return ans;
}
```
