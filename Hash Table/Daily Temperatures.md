# 739. Daily Temperatures
1/30/19
*Medium*

Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures `T = [73, 74, 75, 71, 69, 72, 76, 73]`, your output should be `[1, 1, 4, 2, 1, 1, 0, 0]`.

Note: The length of temperatures will be in the range `[1, 30000]`. Each temperature will be an integer in the range `[30, 100]`.

## Attempts
- Brute force
  - O(n^2) time, O(n) space (result)
```Java
public int[] dailyTemperatures(int[] T) {
    int n = T.length;
    int[] res = new int[n];
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (T[j] > T[i]) {
                res[i] = j - i;
                break;
            }
        }
    }
    return res;
}
```

## Solutions
### 1. Next Array
- if Ti[i] = 50, only need to check the next 51, 52, ..., 100, find the earliest
- find min of next[T[i] + 1], next[T[i] + 2], ... next[100]
- O(n) time, O(n) space
```Java
public int[] dailyTemperatures(int[] T) {
    int n = T.length;
    int[] res = new int[n];
    int[] next = new int[101]; // temperature 30 - 100 degrees
    Arrays.fill(next, Integer.MAX_VALUE); // since we find min value, we fill it with max first
    for (int i = n - 1; i >= 0; i--) {
        next[T[i]] = i; // update next
        int index = Integer.MAX_VALUE;
        // find min index of next
        for (int t = T[i] + 1; t <= 100; t++) {
            if (index > next[t]) {
                index = next[t];
            }
        }
        if (index < Integer.MAX_VALUE) {
            res[i] = index - i;
        }
    }
    return res;
}
```

### 2. Stack
- start from end
- keep decreasing sequence in stack
- O(n) time, O(n) space
```Java
public int[] dailyTemperatures(int[] T) {
    int n = T.length;
    int[] res = new int[n];
    Stack<Integer> stack = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {
        while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
            stack.pop();
        }
        res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
        stack.push(i);
    }
    return res;
}
```
