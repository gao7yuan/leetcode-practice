# 138. Copy List with Random Pointer
*Medium*
10/29/18

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

## Solutions
* Use a hash table to match old node to new node
  - 注意null的情况: next or random是null的话map中get不到
  - O(n) time, O(n) space for map
```
public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) {
        return null;
    }
    Map<RandomListNode, RandomListNode> map = new HashMap<>();
    RandomListNode cur = head;
    while (cur != null) {
        map.put(cur, new RandomListNode(cur.label));
        cur = cur.next;
    }
    cur = head;
    while (cur != null) {
        map.get(cur).next = cur.next == null ? null : map.get(cur.next);
        map.get(cur).random = cur.random == null ? null : map.get(cur.random);
        cur = cur.next;
    }
    return map.get(head);
}
```
* 复制旧node连成新list
  - O(n) time, O(1) space
```
public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) {
        return null;
    }
    RandomListNode oldNode = head;
    RandomListNode newNode;

    while (oldNode != null) {
        newNode = new RandomListNode(oldNode.label);
        newNode.next = oldNode.next;
        oldNode.next = newNode;
        oldNode = newNode.next;
    }

    oldNode = head;
    newNode = head.next;
    while (oldNode != null) {
        newNode.random = oldNode.random == null ? null : oldNode.random.next;
        oldNode = newNode.next;
        newNode = oldNode == null ? null : oldNode.next;
    }

    RandomListNode newhead = head.next;
    oldNode = head;
    newNode = head.next;
    while (oldNode != null) {
        oldNode.next = newNode.next;
        oldNode = oldNode.next;
        newNode.next = oldNode == null ? null : oldNode.next;
        newNode = newNode.next;
    }

    return newhead;
}
```
