# 599.  Minimum Index Sum of Two Lists
1/9/19
*Easy*

Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

Example 1:
```
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".
```
Example 2:
```
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
```
Note:
The length of both lists will be in the range of [1, 1000].
The length of strings in both lists will be in the range of [1, 30].
The index is starting from 0 to the list length minus 1.
No duplicates in both lists.

## Attempts
- hashmap to store restaurant - index pairs of list1
- iterate list2, find index sum of the result, and the counts of the result
- iterate list2 again, add results to result array
- O(n) time, O(n) space

```Java
public String[] findRestaurant(String[] list1, String[] list2) {
    Map<String, Integer> map = new HashMap<>(); // restaurant - index pairs of list1
    for (int i = 0; i < list1.length; i++) {
        map.put(list1[i], i);
    }
    int sum = Integer.MAX_VALUE; // index sum
    int count = 0; // number of matching restaurants
    for (int i = 0; i < list2.length; i++) {
        // if two lists match
        if (map.containsKey(list2[i])) {
            // if found a restaurant that has a smaller index sum
            int indexSum = map.get(list2[i]) + i;
            if (sum > indexSum) {
                sum = indexSum;
                count = 1;
            } else if (sum == indexSum) {
                count++;
            }
        }
    }
    String[] res = new String[count];
    int index = 0;
    for (int i = 0; i < list2.length; i++) {
        if (map.containsKey(list2[i]) && (map.get(list2[i]) + i == sum)) {
            res[index++] = list2[i];
        }
    }
    return res;
}
```
