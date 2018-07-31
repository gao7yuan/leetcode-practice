# 637. Average of levels in binary tree
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
  - add() (enqueue), remove() (dequeue), peek() for Queue in Java
  - Range of val is 32-bit signed integer, cannot use int as the type of sum, because it might exceed the range. Use double instead.
  - Cannot use avg instead of sum as accumulator, because doing division in each iteration would result in a lot of rounding. e.g.: 0 0 0 0 0 0 0 ... -> avg = -2e-5.
  - double divided by int returns double.
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
