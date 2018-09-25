# 15. 3Sum
*Medium*
09/24/18

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:
```
Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

## Attempts
* 先sort，然后转化成2sum
  - 注意点：
    1. 数组不能重复，所以要检查每次increment或者decrement之后的数是否和之前一个一样。
    2. 注意区分```continue```和```break```。```continue```进入下一个iteration，```break```结束所有iteration。
    3. 注意边界index
      - i到nums.length - 2就可以停止了（但是没停止也不hurt，会自动因为j >= k终止）
      - 找到符合条件的数组之后记得```j++```和```k--```
      - 增减j,k之后要确保不和之前的元素相同
  - Complexity
    - sort: O(nlgn) time. for loop: O(n^2) (因为要找到所有数组，所以即使找到一个之后其他数也要遍历，所以for loop里面是O(n))
    - 总：O(n^2)
```
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums); //nlgn time
    for (int i = 0; i < nums.length - 2; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        int j = i + 1;
        int k = nums.length - 1;
        while (j < k) {
            if (nums[j] + nums[k] < nums[i] * (-1)) {
                j++;
            } else if (nums[j] + nums[k] > nums[i] * (-1)) {
                k--;
            } else {
                List<Integer> triplet = new ArrayList<>();
                triplet.add(nums[i]);
                triplet.add(nums[j]);
                triplet.add(nums[k]);
                res.add(triplet);
                j++;
                k--;
            }
            while (j > i + 1 && j < k && nums[j] == nums[j - 1]) {
                j++;
            }
            while (k < nums.length - 1
                   && j < k && nums[k] == nums[k + 1]) {
                k--;
            }
        }
    }
    return res;
}
```
## Solutions
* 思路一样
  - 避免用```continue```，while loop的条件用我的方法中continue的条件取反。
  - 负数用```0-x```
  - ```Arrays.asList(x, y, z)```make a list with elements x, y, z
  - 注意```lo``` ```hi```的处理方法
```
public List<List<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    List<List<Integer>> res = new LinkedList<>();
    for (int i = 0; i < num.length-2; i++) {
        if (i == 0 || (i > 0 && num[i] != num[i-1])) {
            int lo = i+1, hi = num.length-1, sum = 0 - num[i];
            while (lo < hi) {
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    while (lo < hi && num[lo] == num[lo+1]) lo++;
                    while (lo < hi && num[hi] == num[hi-1]) hi--;
                    lo++; hi--;
                } else if (num[lo] + num[hi] < sum) lo++;
                else hi--;
           }
        }
    }
    return res;
}
```
* 另一种写法，检查j,k increment和decrement之后是否和前后值相同的步骤可以只在找到数组的情况下进行
```
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i + 2 < nums.length; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
            continue;
        }
        int j = i + 1, k = nums.length - 1;  
        int target = -nums[i];
        while (j < k) {
            if (nums[j] + nums[k] == target) {
                res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                j++;
                k--;
                while (j < k && nums[j] == nums[j - 1]) j++;  // skip same result
                while (j < k && nums[k] == nums[k + 1]) k--;  // skip same result
            } else if (nums[j] + nums[k] > target) {
                k--;
            } else {
                j++;
            }
        }
    }
    return res;
}
```
