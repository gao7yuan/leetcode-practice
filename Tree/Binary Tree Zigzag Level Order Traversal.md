# 103. Binary Tree Zigzag Level Order Traversal
4/17/19
*Medium*

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
```
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
```

## Attempts
- level order traversal, use a flag to determine whether to reverse that level or not
- O(n) time visiting each TreeNode plus O(k) time reversing a level (k = number of elements on one level). O(n) space.
```Java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
        return res;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean reverse = false;
    while (!queue.isEmpty()) {
        List<Integer> level = new ArrayList<>();
        int count = queue.size();
        TreeNode cur;
        for (int i = 0; i < count; i++) {
            cur = queue.remove();
            level.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        if (reverse) {
            Collections.reverse(level);
        }
        reverse = !reverse;
        res.add(level);
    }
    return res;
}
```
or
- use a flag to determine how to construct the list of one level: `add` or `addFirst` to save time
- O(n) time O(n) space
```Java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
        return res;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean reverse = false;
    while (!queue.isEmpty()) {
        LinkedList<Integer> level = new LinkedList<>();
        int count = queue.size();
        TreeNode cur;
        for (int i = 0; i < count; i++) {
            cur = queue.remove();
            if (reverse) {
                level.addFirst(cur.val);
            } else {
                level.add(cur.val);
            }
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        reverse = !reverse;
        res.add(level);
    }
    return res;
}
```

## Solutions
- can use `int level` to note which level and use `level % 2` to determine whether to reverse or not. (Does it save time? `mod` may take longer than boolean)
