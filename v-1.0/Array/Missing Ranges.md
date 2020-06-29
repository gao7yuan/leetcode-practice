# 163. Missing Ranges
4/4/19
*Medium*

Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:
```
Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]
```

## Attempts
- range is empty, return an empty list
- array is empty, return the whole range
- general case:
  - first, find the first nums[i] >= lower, and fill the gap between lower and nums[i]
  - then, find all the gaps inside nums, before reaching upper. 有漏洞，不知道怎么修。test case不完整。
- 后来发现题意没有理解到位。实际上nums里面所有元素的大小都在[lower, upper]里面。

```Java
public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    List<String> res = new ArrayList<>();
    StringBuilder str = new StringBuilder();
    // range is empty
    if (lower > upper) {
        return res;
    }
    // empty array || no overlap -> add whole range
    if (nums.length == 0 || nums[0] > upper || nums[nums.length - 1] < lower) {
        if (lower == upper) {
            res.add(str.append(lower).toString());
        } else {
            res.add(str.append(lower).append("->").append(upper).toString());
        }
        return res;
    }

    // find the first i for nums[i] >= lower
    int i = 0;
    while (i < nums.length && nums[i] < lower) {
        i++;
    }
    // now nums[0] >= lower, fill the gap between lower -> nums[i]
    if (i < nums.length && lower < nums[i]) {
        str = new StringBuilder();
        if (lower < Integer.MAX_VALUE && nums[i] > lower + 1) {
            res.add(str.append(lower).append("->").append(nums[i] - 1).toString());
        } else {
            res.add(str.append(lower).toString());
        }
    }

    while (i < nums.length - 1 && nums[i + 1] <= upper) {
        str = new StringBuilder();
        if (nums[i + 1] == nums[i] + 2) {
            res.add(str.append(nums[i] + 1).toString());
        } else if (nums[i] < Integer.MAX_VALUE && nums[i + 1] > nums[i] + 2) {
            res.add(str.append(nums[i] + 1).append("->").append(nums[i + 1] - 1).toString());
        }

        i++;
    }
    // exit condition:
    // 1. nums[i + 1] >= upper -> do not need to do anything
    // or
    // 2. i = nums.length - 1 ->
    if (i < nums.length && upper > nums[i]) {
        str = new StringBuilder();
        if (nums[i] < Integer.MAX_VALUE && upper > nums[i] + 1) {
            res.add(str.append(nums[i] + 1).append("->").append(upper).toString());
        } else {
            res.add(str.append(upper).toString());
        }
    }
    return res;
}
```

## Solution
- add corner case for Integer.MAX_VALUE to discussion answer
```Java
public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    List<String> res = new ArrayList<>();
    // edge case: nums is empty
    if (nums.length == 0) {
        res.add(getRange(lower, upper));
        return res;
    }
    int next = lower;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] < next) {
            continue;
        }
        if (nums[i] == next) {
            if (next < Integer.MAX_VALUE) {
                next++;
                continue;
            } else {
                break;
            }
        }
        res.add(getRange(next, nums[i] - 1));
        if (nums[i] < Integer.MAX_VALUE) {
            next = nums[i] + 1;
        } else {
            break;
        }         
    }
    if (next <= upper && nums[nums.length - 1] < upper) {
        res.add(getRange(next, upper));
    }
    return res;
}

private String getRange(int lo, int hi) {
    return lo == hi ? String.valueOf(lo) : String.format("%d->%d", lo, hi);
}
```
