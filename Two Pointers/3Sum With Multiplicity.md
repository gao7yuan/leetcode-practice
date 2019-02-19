# 923. 3Sum With Multiplicity
2/15/19
*Medium*

Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 10^9 + 7.



Example 1:
```
Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation:
Enumerating by the values (A[i], A[j], A[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
```
Example 2:
```
Input: A = [1,1,2,2,2,2], target = 5
Output: 12
Explanation:
A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.
```


Note:

3 <= A.length <= 3000
0 <= A[i] <= 100
0 <= target <= 300

## Attempts
- Wrong answer: did not consider the case where (2, 2, 4) works for 8
```Java
private final static double MOD = Math.pow(10, 9) + 7;
public int threeSumMulti(int[] A, int target) {
    Arrays.sort(A);
    ArrayList<Pair<Integer, Integer>> arr = new ArrayList<>();
    int index = 0;
    while (index < A.length) {
        int count = 1;
        while (index < A.length - 1 && A[index] == A[index + 1]) {
            count++;
            index++;
        }
        arr.add(new Pair(A[index], count));
        index++;
    }


    int i = 0;
    int sum = 0;
    while (i < arr.size() - 3) {
        int j = i + 1, k = arr.size() - 1;
        int toFind = target - arr.get(i).getKey();
        while (j < k) {
            int add = arr.get(j).getKey() + arr.get(k).getKey();
            if (add == toFind) {
                sum += ((arr.get(i).getValue() % MOD)
                        * (arr.get(j).getValue() % MOD)
                        * (arr.get(k).getValue() % MOD)) % MOD;
                j++;
                k--;
            } else if (add < toFind) {
                j++;
            } else {
                k--;
            }
        }
        i++;
    }
    return sum;
}
```

## Solutions
### 1. Three pointers
- three sum -> two sum
  - i from 0 to A.length - 3
  - j, k - two sum with multiplicity
  - note how to calculate repetitions
  - note how to handle case like [1, 2, 2]
- O(n^2) time, O(1) time
- NOTE: count += xxx; -> count %= xxx; if do count += xxx * xxx % xxx; produces wrong answer  
- my code
```Java
public int threeSumMulti(int[] A, int target) {
    Arrays.sort(A);
    int mod = 1000000007;
    int i = 0;
    int count = 0;
    while (i < A.length - 2) {
        int tofind = target - A[i];
        int j = i + 1, k = A.length - 1;
        while (j < k) {
            int sum = A[j] + A[k];
            if (sum == tofind) {
                int numj = 1, numk = 1;
                while (j < A.length - 1 && A[j] == A[j + 1]) {
                    j++;
                    numj++;
                }
                while (k > 0 && A[k] == A[k - 1]) {
                    k--;
                    numk++;
                }
                if (A[j] == A[k]) {
                    count += numj * (numj - 1) / 2;
                    count %= mod;
                } else {
                    count += numj * numk;
                    count %= mod;
                }
                j++;
                k--;
            } else if (sum < tofind) {
                j++;
            } else {
                k--;
            }
        }
        i++;
    }
    return count;
}
```
- solution code
```Java
class Solution {
    public int threeSumMulti(int[] A, int target) {
        int MOD = 1_000_000_007;
        long ans = 0;
        Arrays.sort(A);

        for (int i = 0; i < A.length; ++i) {
            // We'll try to find the number of i < j < k
            // with A[j] + A[k] == T, where T = target - A[i].

            // The below is a "two sum with multiplicity".
            int T = target - A[i];
            int j = i+1, k = A.length - 1;

            while (j < k) {
                // These steps proceed as in a typical two-sum.
                if (A[j] + A[k] < T)
                    j++;
                else if (A[j] + A[k] > T)
                    k--;
                else if (A[j] != A[k]) {  // We have A[j] + A[k] == T.
                    // Let's count "left": the number of A[j] == A[j+1] == A[j+2] == ...
                    // And similarly for "right".
                    int left = 1, right = 1;
                    while (j+1 < k && A[j] == A[j+1]) {
                        left++;
                        j++;
                    }
                    while (k-1 > j && A[k] == A[k-1]) {
                        right++;
                        k--;
                    }

                    ans += left * right;
                    ans %= MOD;
                    j++;
                    k--;
                } else {
                    // M = k - j + 1
                    // We contributed M * (M-1) / 2 pairs.
                    ans += (k-j+1) * (k-j) / 2;
                    ans %= MOD;
                    break;
                }
            }
        }

        return (int) ans;
    }
}
```

### 2. Counting with cases
- count[x] = number of occurrences of x in A. if x + y + z == target, we have
  1. x != y != z
    - count[x] * count[y] * count[z]
  2. x == y != z
    - (choose 2 from count[x]) * count[z]
  3. x != y == z
    - count[x] * (choose 2 from count[y])
  4. x == y == z
    - choose 3 from count[x]
- O(n + w^2) time. n = size of A, to fill count[] need O(n) time. w = number of distinct numbers in A.
- O(n) space.
- my code
```Java
public int threeSumMulti(int[] A, int target) {
    int MOD = 1000000007;
    long[] count = new long[101]; // since range of A[i] is [0, 100]
    // fill count[] with count of each integer
    for (int i = 0; i < A.length; i++) {
        count[A[i]]++;
    }
    long res = 0;

    // all different
    for (int x = 0; x <= 100; x++) {
        for (int y = x + 1; y <= 100; y++) {
            int z = target - x - y;
            if (z > y && z <= 100) {
                res += count[x] * count[y] * count[z];
                res %= MOD;
            }
        }
    }

    // x == y != z
    for (int x = 0; x <= 100; x++) {
        int z = target - x * 2;
        if (z > x && z <= 100) {
            res += count[x] * (count[x] - 1) * count[z] / 2;
            res %= MOD;
        }
    }

    // x != y == z
    for (int x = 0; x <= 100; x++) {
        if (target % 2 == x % 2) {
            int y = (target - x) / 2;
            if (y > x && y <= 100) {
                res += count[x] * count[y] * (count[y] - 1) / 2;
                res %= MOD;
            }
        }
    }

    // x == y == z
    if (target % 3 == 0) {
        int x = target / 3;
        res += count[x] * (count[x] - 1) * (count[x] - 2) / 6;
        res %= MOD;
    }

    return (int) res;
}
```
- solution code
```Java
class Solution {
    public int threeSumMulti(int[] A, int target) {
        int MOD = 1_000_000_007;
        long[] count = new long[101];
        for (int x: A)
            count[x]++;

        long ans = 0;

        // All different
        for (int x = 0; x <= 100; ++x)
            for (int y = x+1; y <= 100; ++y) {
                int z = target - x - y;
                if (y < z && z <= 100) {
                    ans += count[x] * count[y] * count[z];
                    ans %= MOD;
                }
            }

        // x == y != z
        for (int x = 0; x <= 100; ++x) {
            int z = target - 2*x;
            if (x < z && z <= 100) {
                ans += count[x] * (count[x] - 1) / 2 * count[z];
                ans %= MOD;
            }
        }

        // x != y == z
        for (int x = 0; x <= 100; ++x) {
            if (target % 2 == x % 2) {
                int y = (target - x) / 2;
                if (x < y && y <= 100) {
                    ans += count[x] * count[y] * (count[y] - 1) / 2;
                    ans %= MOD;
                }
            }
        }

        // x == y == z
        if (target % 3 == 0) {
            int x = target / 3;
            if (0 <= x && x <= 100) {
                ans += count[x] * (count[x] - 1) * (count[x] - 2) / 6;
                ans %= MOD;
            }
        }

        return (int) ans;
    }
}
```

### 3. Adapt from three sum
- O(n^2) time, O(n) space
```Java
class Solution {
    public int threeSumMulti(int[] A, int target) {
        int MOD = 1_000_000_007;

        // Initializing as long saves us the trouble of
        // managing count[x] * count[y] * count[z] overflowing later.
        long[] count = new long[101];
        int uniq = 0;
        for (int x: A) {
            count[x]++;
            if (count[x] == 1)
                uniq++;
        }

        int[] keys = new int[uniq];
        int t = 0;
        for (int i = 0; i <= 100; ++i)
            if (count[i] > 0)
                keys[t++] = i;

        long ans = 0;
        // Now, let's do a 3sum on "keys", for i <= j <= k.
        // We will use count to add the correct contribution to ans.

        for (int i = 0; i < keys.length; ++i) {
            int x = keys[i];
            int T = target - x;
            int j = i, k = keys.length - 1;
            while (j <= k) {
                int y = keys[j], z = keys[k];
                if (y + z < T) {
                    j++;
                } else if (y + z > T) {
                    k--;
                } else {  // # x+y+z == T, now calc the size of the contribution
                    if (i < j && j < k) {
                        ans += count[x] * count[y] * count[z];
                    } else if (i == j && j < k) {
                        ans += count[x] * (count[x] - 1) / 2 * count[z];
                    } else if (i < j && j == k) {
                        ans += count[x] * count[y] * (count[y] - 1) / 2;
                    } else {  // i == j == k
                        ans += count[x] * (count[x] - 1) * (count[x] - 2) / 6;
                    }

                    ans %= MOD;
                    j++;
                    k--;
                }
            }
        }

        return (int) ans;
    }
}
```
