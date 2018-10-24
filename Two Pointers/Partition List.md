# 86. Partition List
*Medium*
10/23/18

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:
```
Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
```

## Attempts (after revision)
* 建立两个list分别存储较小部分和较大部分
* 要点在于较大部分的尾巴要指向null，否则出现cycle
* 修改了合并两个list的code使其更加简洁易懂！！！！！！！！！！
  - O(n) time, one pass, O(1) space
```
public ListNode partition(ListNode head, int x) {
    ListNode small = new ListNode(0);
    ListNode notsmall = new ListNode(0);
    ListNode smallcur = small;
    ListNode notsmallcur = notsmall;
    ListNode cur = head;
    while (cur != null) {
        if (cur.val < x) {
            smallcur.next = cur;
            smallcur = cur;
        } else {
            notsmallcur.next = cur;
            notsmallcur = cur;
        }
        cur = cur.next;
    }
    notsmallcur.next = null;
    smallcur.next = notsmall.next;
    return small.next;
}
```
