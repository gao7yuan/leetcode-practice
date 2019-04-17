# Corner case处理技巧

## Missing Ranges

Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:
```
Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]
```
- 思路
  - 简单的模拟题
    - 两端点和一头一尾形成的区间 + for循环扫描中间形成的区间
    - 利用函数让自己的代码更简洁
  - 特殊输入
    - int范围
    - 去掉的点为空
  - O(n) time

  - 解决的问题
    1. long
    2. 空数组

```Java
public List<String> findMissingRanges(int[] nums, int lower, int upper) {
  List<String> ans = new ArrayList<>();

  if (nums == null || nums.length == 0) {
    addRange(ans, lower, upper);
    return ans;
  }

  addRange(ans, lower, (long)nums[0] - 1);

  for (int i = 1; i < nums.length; i++) {
    addRange(ans, (long)nums[i - 1] + 1, (long)nums[i] - 1);
  }
  addRange(ans, (long)nums[nums.length - 1] + 1, upper);
  return ans;
}
void addRange(List<String> ans, long st, long ed) {
  if (st > ed) {
    return;
  }
  if (st == ed) {
    ans.add(st + "");
    return;
  }
  ans.add(st + "->" + ed);
}
```

## Valid Number

Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.
