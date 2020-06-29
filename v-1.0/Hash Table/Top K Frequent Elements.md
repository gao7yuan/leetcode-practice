# 347. Top K Frequent Elements
12/4/18
*Medium*
**当需要最大or最小的几个元素但是不需要全部排序时，考虑heap**

## 注意点：
* map.entrySet()
* map.keySet()
* heap: max or min

Given a non-empty array of integers, return the k most frequent elements.

Example 1:
```
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
```
Example 2:
```
Input: nums = [1], k = 1
Output: [1]
```
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

## Solutions
* Intuition:如果k=1，则只需要用一个hashmap记录num-freq,同时一直更新最大freq对应的数字即可。
* k>1时，可以用max heap (priority queue)存放每个number，排序的依据是它们在map中对应的frequency
* 建立hashmap:遍历一边array，O(n)time,n是array的长度
* 建立heap:对于一个元素，extract min O(log(x)),x是heap中的元素数量，也就是array中的不同数字的个数，x<=n。因为一共有k个不同元素，所以总共O(xlogx)time
* O(xlogx) time, O(n) space for hashmap
```Java
public List<Integer> topKFrequent(int[] nums, int k) {
    // build a hashmap to collect frequency of each num
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
        if (map.containsKey(num)) {
            map.put(num, map.get(num) + 1);
        } else {
            map.put(num, 1);
        }
    }

    // use a max heap to store nums, the sorting order is based on the frequency of each num
    PriorityQueue<Integer> q = new PriorityQueue<>(nums.length,
                                                   new Comparator<Integer>(){
                                                       public int compare(Integer a, Integer b) {
                                                           return map.get(b) - map.get(a);
                                                       }
                                                   });
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        q.add(entry.getKey());
    }
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < k; i++) {
        res.add(q.remove());
    }
    return res;
}
```
* 优化一下：
  - 用min heap，但是只存最大的k个元素，如果heap的size大于k则remove最小的元素。这样extract min最差是O(klogk) time
  - map.keySet()
```Java
public List<Integer> topKFrequent(int[] nums, int k) {
    // build a hashmap to collect frequency of each num
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
        if (map.containsKey(num)) {
            map.put(num, map.get(num) + 1);
        } else {
            map.put(num, 1);
        }
    }

    // use a min heap to store nums, the sorting order is based on the frequency of each num
    PriorityQueue<Integer> q = new PriorityQueue<>(nums.length,
                                                   new Comparator<Integer>(){
                                                       public int compare(Integer a, Integer b) {
                                                           return map.get(a) - map.get(b);
                                                       }
                                                   });
    // keep top k elements in heap
    for (int freq : map.keySet()) {
        q.add(freq);
        if (q.size() > k) {
            q.remove();
        }
    }
    LinkedList<Integer> res = new LinkedList<>();
    for (int i = 0; i < k; i++) {
        res.addFirst(q.remove());
    }
    return res;
}
```
