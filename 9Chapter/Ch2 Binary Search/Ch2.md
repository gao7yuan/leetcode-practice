# Ch2. Binary Search

## Classical binary search
Given an sorted integer array - nums, and an integer - target. Find the any/first/last position of target in nums, return -1 if target doesn’t exist.

## Recursion or while loop?
* While loop better
  - Why?
    - Avoid recursion: stack overflow
* 如果while loop太复杂，recursion ok
  - Ask the interviewer!

## Binary search template
* Key points:
1. start + 1 < end （不容易死循环）
  - 不满足：
    - start + 1 = end 相邻
    - start + 1 > end 相交
  - 通用写法，有时候需要得到start和end之间的缝
  - 如果找first, 结束之后先检查start，反之先检查end
2. mid = start + (end - start) / 2
  - 针对非常大的start和end，防止int溢出
3. A[mid] ==, >, <
4. A[start] A[end] ? target

## 用二分法做的问题
* first/last
* O(n) -> O(lgn)

## reverse O(1) space O(n) time
template:
```
for (int start = 0, int end = n - 1; start < end; start++, end--) {
  swap(A[start], A[end]);
}
```
