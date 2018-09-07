# 637. Average of Levels in Binary Tree
*Easy* *二刷*
7/30/18

Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
**Example 1:**
```
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
```
**Note:**
The range of node's value is in the range of 32-bit signed integer.

## Attempts
* BFS. Similar as *Binary tree level order traversal II*.
* Notes:
  - ```add()``` (enqueue), ```remove()``` (dequeue), ```peek()``` for ```Queue``` in Java
  - Range of val is 32-bit signed integer, cannot use int as the type of sum, because it might exceed the range. Use double instead.
  - Cannot use avg instead of sum as accumulator, because doing division in each iteration would result in a lot of rounding. e.g.: 0 0 0 0 0 0 0 ... -> avg = -2e-5.
  - **double divided by int returns double.**
```
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0.0;
            for (int i=0; i<size; i++) {
                if (queue.peek().left != null) queue.add(queue.peek().left);
                if (queue.peek().right != null) queue.add(queue.peek().right);
                sum += queue.remove().val;
            }
            list.add(sum / size);
        }
        return list;    
    }
}
```

## 二刷：
* DFS:
  - 用两个list:count&sum分别记录每一层node数量和node值的总和。
  - 用一个helper method ```average(t,i,sum,count)```来update sum & count在每一层的总和和node数。起始条件为```average(root,0,sum,count)```。
  - 每次访问一个node的时候，给sum在i层的数值加上node的数值，并且给count在i层的数值加一。
  - 然后访问node的左右孩子。

  - O(n) time - only traverse once.
  - O(h) space - h is height of tree. sum and count has size h. Recursive tree can only reach h too.

  - double/int -> double
```
public List<Double> averageOfLevels(TreeNode root) {
    List<Double> res = new ArrayList<>();
    List<Integer> count = new ArrayList<>();
    average(root, 0, res, count);
    for (int i = 0; i < res.size(); i++) {
        res.set(i, res.get(i) / count.get(i));
    }
    return res;
}
```
  - 注意区分i index是否存在 - 是set还是add
```
private void average(TreeNode t, int i, List<Double> sum, List<Integer> count) {
    if (t == null) return;
    if (i < sum.size()) {
        sum.set(i, sum.get(i) + t.val);
        count.set(i, count.get(i) + 1);
    } else {
        sum.add(1.0 * t.val);
        count.add(1);
    }
    average(t.left, i + 1, sum, count);
    average(t.right, i + 1, sum, count);
}
```

* BFS
  - node加入到queue
  - dequeue 并且将node的信息update到sum和count
  - 同时enqueue node的children到一个temp queue
  - 重复步骤直到queue为空，将temp的内容添加到queue，重复步骤直到queue和temp都为空

  - O(n) time
  - O(m) space. m is maximum number of nodes at any level.
```
public List<Double> averageOfLevels(TreeNode root) {
    List<Double> res = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    if (root == null) return res;
    queue.add(root);
    while (!queue.isEmpty()) {
        double sum = 0.0;
        int count = 0;
        Queue<TreeNode> temp = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            sum += node.val;
            count++;
            if (node.left != null) temp.add(node.left);
            if (node.right != null) temp.add(node.right);
        }
        queue = temp;
        res.add(sum / count);
    }
    return res;
}
```
