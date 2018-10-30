# 146. LRU Cache
*Hard*
10/29/18

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:
```
LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
```

## Solutions
* double linked list + hashmap
  - 用head和tail来避免对null的讨论。
  - 如果key已存在则替换
  - map中存在的value是linked list中间的node，要用shallow copy
```
class LRUCache {

    private class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        Node() {
            this(0, 0);
        }
    }

    private int count, capacity; // to maintain the size of cache
    private Map<Integer, Node> map; // to match key to node
    private Node head, tail;  // dummy head and tails to avoid discussion of null pointers

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            count--;
            map.remove(key);
            removeNode(node);
        }
        if (count < capacity) {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNode(newNode);
            count++;
        } else {
            Node newNode = new Node(key, value);
            map.remove(tail.prev.key);
            map.put(key, newNode);
            removeNode(tail.prev);
            addNode(newNode);
        }
    }

    // add new node after head
    private void addNode(Node node) {
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next.prev = node;
        this.head.next = node;
    }

    // remove a random node
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // move a random node to the position after head
    private void moveNode(Node node) {
        removeNode(node);
        addNode(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 ```
 * 本质上是LinkedHashMap. map with predictable order
