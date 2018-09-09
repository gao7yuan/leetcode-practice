# 350. Intersection of Two Arrays II
*Easy*
08/27/18

Given two arrays, write a function to compute their intersection.

Example 1:
```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
```
Example 2:
```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
```
**Note:**

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.

**Follow up:**

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

## Attempts
```
public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int num : nums1) {
            if (map1.containsKey(num)) {
                map1.put(num, map1.get(num) + 1);
            } else {
                map1.put(num, 1);
            }
        }
        for (int num : nums2) {
            if (map2.containsKey(num)) {
                map2.put(num, map2.get(num) + 1);
            } else {
                map2.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            if (map2.containsKey(entry.getKey())) {
                int min = Math.min(map2.get(entry.getKey()), entry.getValue());
                for (int i = 0; i < min; i++) {
                    result.add(entry.getKey());
                }
            }
        }
        int len = result.size();
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = result.get(i);
        }
        return res;
    }
```

## Solutions
* 类似的想法，用一个map。
```
public int[] intersect(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    ArrayList<Integer> result = new ArrayList<Integer>();
    for(int i = 0; i < nums1.length; i++)
    {
        if(map.containsKey(nums1[i])) map.put(nums1[i], map.get(nums1[i])+1);
        else map.put(nums1[i], 1);
    }

    for(int i = 0; i < nums2.length; i++)
    {
        if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0)
        {
            result.add(nums2[i]);
            map.put(nums2[i], map.get(nums2[i])-1);
        }
    }

   int[] r = new int[result.size()];
   for(int i = 0; i < result.size(); i++)
   {
       r[i] = result.get(i);
   }

   return r;
}
```
