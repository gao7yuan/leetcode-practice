# 981. Time Based Key-Value Store
5/22/19
*Medium* *Binary Search* *TreeMap* *HashMap*

Create a timebased key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)

Stores the key and value, along with the given timestamp.
2. get(string key, int timestamp)

Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the one with the largest timestamp_prev.
If there are no values, it returns the empty string ("").


Example 1:
```
Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
Output: [null,null,"bar","bar",null,"bar2","bar2"]
Explanation:   
TimeMap kv;   
kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
kv.get("foo", 1);  // output "bar"   
kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
kv.set("foo", "bar2", 4);   
kv.get("foo", 4); // output "bar2"   
kv.get("foo", 5); //output "bar2"   
```
Example 2:
```
Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
Output: [null,null,null,"","high","high","low","low"]
```

Note:

All key/value strings are lowercase.
All key/value strings have length in the range [1, 100]
The timestamps for all TimeMap.set operations are strictly increasing.
1 <= timestamp <= 10^7
TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.

## 思路
- 用map,key -> a list of <timestamp -> value> pairs
- set: 对于给定key,加一个pair在value set里。O(1) time
- get: 对于给定key,得到一组<timestamp -> value> pairs，用二分法找到<= 给定timestamp那对pair。O(lgn) time
  - 此处binary search要注意边界情况！！！容易进入死循环
- option:Map<String, TreeMap<Integer, String>>
  - key -> TreeMap<timestamp -> value>
  - TreeMap
    - `K ceilingKey(K key)`: Returns the least key greater than or equal to the given key, or null if there is no such key.
    - `K floorKey(K key)`: Returns the greatest key less than or equal to the given key, or null if there is no such key.
    - `V put(K key, V value)`
  - set: O(1) time. (put into treemap)
  - get: O(lgn) time


## Solutions
1. hashmap + binary search, 但是要用pair
```Java
class TimeMap {

    public class Pair {
        Integer timestamp;
        String value;

        public Pair(Integer timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    Map<String, List<Pair>> map; // key -> {timestamp -> value}

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(new Pair(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        List<Pair> list = map.get(key);
        if (list.size() == 0) {
            return "";
        }
        return bs(list, timestamp);
    }

    // find the largest index with pair.timestamp <= timestamp
    String bs(List<Pair> list, int timestamp) {
        int size = list.size();
        int lo = 0;
        int hi = size - 1;
        int mid;

        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            int cur = list.get(mid).timestamp;
            if (cur == timestamp) {
                return list.get(mid).value;
            }
            if (cur > timestamp) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if (list.get(lo).timestamp <= timestamp) {
            return list.get(lo).value;
        } else if (lo > 0 && list.get(lo - 1).timestamp <= timestamp) {
            return list.get(lo - 1).value;
        } else {
            return "";
        }

    }
}


```
2. hashmap + treemap
  - O(1) for set, O(lgn) for get
```Java
Map<String, TreeMap<Integer, String>> map;

/** Initialize your data structure here. */
public TimeMap() {
    map = new HashMap<>();
}

public void set(String key, String value, int timestamp) {
    if (!map.containsKey(key)) {
        map.put(key, new TreeMap<>());
    }
    map.get(key).put(timestamp, value);
}

public String get(String key, int timestamp) {
    if (!map.containsKey(key)) {
        return "";
    }
    TreeMap<Integer, String> res = map.get(key);
    Integer k = res.floorKey(timestamp);
    return k == null ? "" : res.get(k);
}
```
