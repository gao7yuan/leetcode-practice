# 42. Trapping Rain Water
*Hard*
11/6/18

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

Example:
```
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
```

## Solutions
### 1. Brute force
* 对于每一个bar，找出它左边和右边最高的bar，取两者中的最小值，减去bar本身的高度
* 对于每一个bar都要遍历一次，总共O(n^2) time, O(1) space
```
public int trap(int[] height) {
    int maxleft, maxright;
    int rain = 0;
    for (int i = 1; i < height.length - 1; i++) {
        maxleft = 0;
        maxright = 0;
        for (int j = i - 1; j >= 0; j--) {
            if (height[j] > maxleft) {
                maxleft = height[j];
            }
        }
        for (int j = i + 1; j < height.length; j++) {
            if (height[j] > maxright) {
                maxright = height[j];
            }
        }
        if (maxleft > height[i] && maxright > height[i]) {
            rain += Math.min(maxleft, maxright) - height[i];
        }
    }
    return rain;
}
```
### 2. DP
* 用一个maxleft[]和一个maxright[]来记录左边和右边的最大值
* O(n) time, O(n) space
```
public int trap(int[] height) {
    int rain = 0;
    int[] maxleft = new int[height.length];
    int[] maxright = new int[height.length];

    for (int i = 0; i < height.length; i++) {
        if (i == 0) {
            maxleft[i] = height[i];
        } else {
            maxleft[i] = Math.max(maxleft[i - 1], height[i]);
        }
    }
    for (int i = height.length - 1; i >= 0; i--) {
        if (i == height.length - 1) {
            maxright[i] = height[i];
        } else {
            maxright[i] = Math.max(maxright[i + 1], height[i]);
        }
    }
    for (int i = 1; i < height.length - 1; i++) {
        rain += Math.min(maxleft[i], maxright[i]) - height[i];
    }
    return rain;
}
```
### 3. Stack
* 用一个stack记录index。如果对应的高度比stack.peek()对应的高度低，说明这个bar一定被stack.peek()包围。如是，讲这个index加进stack。
* 如果对应的高度比stack.peek()对应的高度高，说明stack.peek()被这个bar和前一个bar包围。此时pop出top，算出前后bar的距离，求出积累的雨水。
* O(n) time, O(n) space
```
public int trap(int[] height) {
    if (height.length == 0) {
        return 0;
    }
    int rain = 0;
    int cur = 0;
    Stack<Integer> stack = new Stack<>();
    while (cur < height.length) {
        // when current height larger than top of stack, find rain water
        while (!stack.isEmpty() && height[cur] > height[stack.peek()]) {
            int middleInd = stack.pop();
            if (stack.isEmpty()) {
                break;
            }
            int leftInd = stack.peek();
            int dist = cur - leftInd - 1;
            rain += (Math.min(height[cur], height[leftInd]) - height[middleInd]) * dist;
        }
        stack.push(cur++);
    }
    return rain;
}
```
### 4. Two pointers
* 错误答案：
  - 错误原因。。。
```
public int trap(int[] height) {
    int left = 0; // left index
    int right = 1; // right index
    int rain = 0;
    int cum = 0;
    while (right < height.length) {
        if (height[right] < height[left]) {
            cum += height[right++];
        } else {
            rain += Math.min(height[left], height[right]) * (right - left - 1) - cum;
            left = right++;
            cum = 0;
        }
    }
    return rain;
}
```
* 正确方法
  - left = 0, right = len - 1
  - 如果一边的bar比另一边大，比如right > left，那么储水量是从左到右决定的
  - if height[left] < height[right]
    - if height[left] >= leftmax, update leftmax
    - else add leftmax - height[left] to res
  - 右边类似
  - O(n) time, O(1) space
```
public int trap(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int rain = 0;
    int leftmax = 0;
    int rightmax = 0;
    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] >= leftmax) {
                leftmax = height[left++];
            } else {
                rain += leftmax - height[left++];
            }
        } else {
            if (height[right] >= rightmax) {
                rightmax = height[right--];
            } else {
                rain += rightmax - height[right--];
            }
        }
    }
    return rain;
}
```  
