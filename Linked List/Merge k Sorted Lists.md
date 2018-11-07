# 23. Merge k Sorted Lists
*Hard*
11/6/18

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:
```
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
```

## Attempts
### 1. Brute force
* lists里的值存入array，sort，然后转换成list
  - O(nlogn) time, O(n) space. n为总node数
```
public ListNode mergeKLists(ListNode[] lists) {
       int len = 0;
       for (ListNode list : lists) {
           ListNode cur = list;
           while (cur != null) {
               len++;
               cur = cur.next;
           }
       }
       int[] arr = new int[len];
       int i = 0;
       for (ListNode list : lists) {
           ListNode cur = list;
           while (cur != null) {
               arr[i++] = cur.val;
               cur = cur.next;
           }
       }
       Arrays.sort(arr);
       ListNode dummy = new ListNode(0);
       ListNode cur = dummy;
       for (int num : arr) {
           cur.next = new ListNode(num);
           cur = cur.next;
       }
       return dummy.next;
   }
```
### 2. 一个一个node比较
* O(kn) time, O(n) space create a new list, or O(1) space using in place construction.
```
public ListNode mergeKLists(ListNode[] lists) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    // lists still contains values
    while (!empty(lists)) {
        int min = Integer.MAX_VALUE;
        int minInd = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null && lists[i].val < min) {
                min = lists[i].val;
                minInd = i;
            }
        }
        lists[minInd] = lists[minInd].next;
        cur.next = new ListNode(min);
        cur = cur.next;
    }
    return dummy.next;
}

private boolean empty(ListNode[] lists) {
    for (ListNode list : lists) {
        if (list != null) {
            return false;
        }
    }
    return true;
}
```
### 3. Priority queue
* O(nlogk) time, O(k) space for heap, O(n) space for linked list. O(1) for linked list if in place.
```
public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
        return null;
    }
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    PriorityQueue<ListNode> q = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
        public int compare(ListNode a, ListNode b) {
            return a.val - b.val;
        }
    });
    for (ListNode list : lists) {
        if (list != null) {
            q.add(list);
        }
    }
    // lists still contains values
    while (!q.isEmpty()) {
        ListNode minNode = q.remove();
        cur.next = new ListNode(minNode.val);
        cur = cur.next;
        minNode = minNode.next;
        if (minNode != null) {
            q.add(minNode);
        }
    }
    return dummy.next;
}
```
### 4. Divide and conquer
* O(nlogk) time, 这里O(n)存放lists了，但是不需要，可以用s,e来partition
```
public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
        return null;
    }
    if (lists.length == 1) {
        return lists[0];
    }
    if (lists.length == 2) {
        return merge2Lists(lists[0], lists[1]);
    }
    int len = lists.length;
    ListNode[] list1 = new ListNode[len / 2];
    ListNode[] list2 = new ListNode[len - len / 2];
    for (int i = 0; i < len / 2; i++) {
        list1[i] = lists[i];
    }
    for (int i = 0; i < len - len / 2; i++) {
        list2[i] = lists[len / 2 + i];
    }
    return merge2Lists(mergeKLists(list1), mergeKLists(list2));
}



private ListNode merge2Lists(ListNode list1, ListNode list2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while (list1 != null && list2 != null) {
        if (list1.val < list2.val) {
            cur.next = new ListNode(list1.val);
            list1 = list1.next;
        } else {
            cur.next = new ListNode(list2.val);
            list2 = list2.next;
        }
        cur = cur.next;
    }
    if (list1 != null) {
        cur.next = list1;
    }
    if (list2 != null) {
        cur.next = list2;
    }
    return dummy.next;
}
```
or better:
* O(nlgk) time, O(1) space
```
public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
        return null;
    }
    int interval = 1;
    while (interval < lists.length) {
        for (int i = 0; i + interval < lists.length; i += interval * 2) {
            lists[i] = merge2Lists(lists[i], lists[i + interval]);
        }
        interval *= 2;
    }
    return lists[0];
}

private ListNode merge2Lists(ListNode list1, ListNode list2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while (list1 != null && list2 != null) {
        if (list1.val < list2.val) {
            cur.next = new ListNode(list1.val);
            list1 = list1.next;
        } else {
            cur.next = new ListNode(list2.val);
            list2 = list2.next;
        }
        cur = cur.next;
    }
    if (list1 != null) {
        cur.next = list1;
    }
    if (list2 != null) {
        cur.next = list2;
    }
    return dummy.next;
}
```
