## Tree

* BFS:
  - Use a Queue
  - Enqueue the root to the queue.
  - Dequeue the nodes in the queue.
  - For every node removed from the queue, add all its children to the back of queue
  - End when queue is empty.
* BFS分层方法
  1. 每次操作之前先获取queue的size，for loop操作size次，每次都dequeue一个node并且enqueue它的children。这个size就是这一层的node数。
  2. 用一个temp queue暂时存取被dequeue的node的children，直到queue为空，将temp中的node加入queue，如此分层。

* 求一个BT的depth：recursion - 左右孩子的depth的最大值加一。

* [110. Balanced Binary Tree](/Tree/Balanced&#32Binary&#32Tree.md)
  - Top-down: 左右孩子的depth差小于一，并且左右孩子都是balanced (slow)
  - Bottom-up: 累积计算node的height，如果左右孩子height差大于一返回-1，否则返回该node的height。检测root的height是否为-1 (fast)
* [107. Binary Tree Level Order Traversal II](/Tree/Binary Tree Level Order Traversal II.md)
BFS: 分层加入list的head
* [108. Convert Sorted Array to Binary Search Tree](/Tree/Convert Sorted Array to Binary Search Tree.md)
Recursion & divide and conquer. 取中间值给node，对左后孩子分别在array的左右半边取中间值循环操作。注意边界条件。helper method处理index
* [226. Invert binary tree](/Tree/Invert binary tree.md)
Recursion, DFS, 先交换左右node，再在左右上recursively call.
* [257: Binary Tree Paths](/Tree/Binary Tree Paths.md)
DFS: 用helper method储存已经遍历过的path和已经形成的list
* [538. Convert BST to Greater Tree](/Tree/Convert BST to Greater Tree.md)
Reverse in-order traversal, use a sum as accumulator.
* [606. Construct String From Binary Tree](/Tree/Construct String From Binary Tree.md)
  - Recursion: DFS
    - 左为空并且右不为空的时候加```()```
    - 有左：val->(->left->)
    - 有右：val->(->right->)
    - 无左有右：val->()->...
* [637. Average of Levels in Binary Tree](/Tree/Average of Levels in Binary Tree.md)
  - DFS: 两个list sum和count分别记录每一层，```average(node, i, sum, count)```更新两个list
  - BFS: 用temp queue或者```queue.size()```分层记录```sum/count```
* [872. Leaf Similar Trees](/Tree/Leaf Similar Trees.md)
  - 先用DFS建立list of leaves再比较两个list，O(n) time and space(?)
  - Stack: ```int stack(Stack<TreeNode> s)```返回每个leaf的值，一个一个比较leaf，一旦不一样就返回false。节省空间。O(logn) space (why?)
