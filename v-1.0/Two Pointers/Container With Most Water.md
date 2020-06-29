# 11. Container With Most Water
*Medium*
10/21/18

Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

Example:
```
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
```

## Attempts
* Brute-force
Basically we want to find index a and b such that min(height(a), height(b)) * (b - a) (assume b > a) gives its maximum value.
We can visit all combinations, which takes O(n^2) time.
```
public int maxArea(int[] height) {
    int max = 0;
    for (int i = 0; i < height.length - 1; i++) {
        for (int j = i + 1; j < height.length; j++) {
            int area = Math.min(height[i], height[j]) * (j - i);
            max = max >= area ? max : area;
        }
    }
    return max;
}
```
* Two pointers
面积由较短板和index之间距离决定，用两个pointer分别指向头尾，每次将短板的pointer向另一边逼近，更新max
O(n) time, O(1) space
```
public int maxArea(int[] height) {
    int max = 0;
    for (int i = 0, j = height.length - 1; i < j;) {
        int area = Math.min(height[i], height[j]) * (j - i);
        max = max >= area ? max : area;
        if (height[i] < height[j]) {
            i++;
        } else {
            j--;
        }
    }
    return max;
}
```
