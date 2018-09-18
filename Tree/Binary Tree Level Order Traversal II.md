# 107. Binary Tree Level Order Traversal II
*Easy* *二刷* *DFS看不懂* *已整理*
07/20/18

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
```
    3
   / \
  9  20
    /  \
   15   7
   ```
return its bottom-up level order traversal as:
```
[
  [15,7],
  [9,20],
  [3]
]
```

## Attempts
* Traverse level by level - BFS - using Queue data structure.
* How to separate each level - using a separator flag in the Queue.
  - Every time the flag is the first element of the queue, dequeue then enqueue the flag, which labels that one level has finished.
* Bottom-up level order: addFirst() to a linked list
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
      TreeNode flag = new TreeNode(1); // flag to label one level
      List<Integer> listLevel = new LinkedList<>(); // list to store one level
      LinkedList<List<Integer>> listAll = new LinkedList<>();
      TreeNode cur;
      Queue<TreeNode> queue = new LinkedList<>();
      if (root == null) {
        return listAll;
      }
      queue.add(root);
      queue.add(flag);
      while (!queue.isEmpty()) {
        if (queue.peek() == flag) {
          queue.add(queue.remove());
          listAll.addFirst(listLevel);
          listLevel = new LinkedList<>();
          if (queue.size() <= 1) {
              break;
          }
        }
        cur = queue.remove();
        listLevel.add(cur.val);
        if (cur.left != null) {
          queue.add(cur.left);
        }
        if (cur.right != null) {
          queue.add(cur.right);
        }
      }
      return listAll;
    }
}
```
* Java notes:
  - Interface ```Queue``` implemented by ```LinkedList``` class. enqueue: ```add()```. dequeue: ```remove()```.
  - LinkedList supports ```addFirst()```.
  - Pay attention to the signature of the constructor of TreeNode.
  - Need to take care of case when root == null, otherwise ```NullPointerException```.
  - Cannot use ```queue.size() > 1``` as condition in while loop, otherwise will miss adding the lowest level.

## Solution
* BFS solution:
```
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if(root == null) return wrapList;

        queue.offer(root); // offer() is similar as add() in queue
        while(!queue.isEmpty()){
            int levelNum = queue.size(); // number of elements in this level
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val); // poll() is similar as remove() in queue
            }
            wrapList.add(0, subList); // Interface List: add(int index, E element)
        }
        return wrapList;
    }
}
```
* DFS solution: ???
```
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        levelMaker(wrapList, root, 0);
        return wrapList;
    }

    public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if(root == null) return;
        if(level >= list.size()) {
            list.add(0, new LinkedList<Integer>());
        }
        levelMaker(list, root.left, level+1);
        levelMaker(list, root.right, level+1);
        list.get(list.size()-level-1).add(root.val);
    }
}
```
