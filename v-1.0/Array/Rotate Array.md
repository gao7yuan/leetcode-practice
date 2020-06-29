# 189. Rotate Array
1/8/19
*Easy*

Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:
```
Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
```
Example 2:
```
Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
```
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?

## Attempts
* Rotate one, then repeate k times. O(kn) time, O(1) space - Brute Force
```Java
public void rotate(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
        return;
    }
    for (int i = 0; i < k; i++) {
        rotateOne(nums);
    }
}

private void rotateOne(int[] nums) {
    int temp = nums[nums.length - 1];
    for (int i = nums.length - 1; i > 0; i--) {
        nums[i] = nums[i - 1];
    }
    nums[0] = temp;
}
```

* Use an array to store the new array. O(n) time, O(n) space
```Java
public void rotate(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
        return;
    }
    int n = nums.length;
    int[] res = new int[n];
    for (int i = 0; i < n; i++) {
        res[(i + k) % n] = nums[i];
    }
    for (int i = 0; i < n; i++) {
        nums[i] = res[i];
    }
}
```

* Cyclic replacements
  - place an element in current + k index, until it comes back to the original index
  - index++
  - O(n) time, O(1) space
```Java
public void rotate(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
        return;
    }
    int n = nums.length;
    k = k % n;
    int count = 0;
    for (int start = 0; count < n; start++) {
        int current = start; // current index to start with
        int prevNum = nums[current];
        do {
            int next = (current + k) % n; // next index to place num in
            int temp = nums[next];
            nums[next] = prevNum;
            prevNum = temp;
            current = next;
            count++;
        } while (start != current);
    }
}
```

* Reverse the whole array first, then reverse the first k nums, followed by reverse the last n - k nums
  - O(n) time, O(1) space
```Java
public void rotate(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
        return;
    }
    int n = nums.length;
    k = k % n;
    reverse(nums, 0, n - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, n - 1);

}

private void reverse(int[] nums, int i, int j) {
    while (i < j) {
        int temp = nums[i];
        nums[i++] = nums[j];
        nums[j--] = temp;
    }
}
```
