## Hash Table

* [136. Single Number](/Hash&#32;Table/Single&#32;Number.md)
set - 如果含有这个数字则删除，如果不含有则加入，最后剩下的是唯一的数。
* [202. Happy Number](/Hash&#32;Table/Happy&#32;Number.md)
用set记录已经访问过的number
* [204. Count Primes](/Hash&#32;Table/Count&#32;Primes.md)
用array of booleans代表是否是prime，如果某index的值是prime，count加一。同时更新这个index所有的multiples都不是prime。
* [205. Isomorphic Strings](/Hash Table/205. Isomorphic Strings.md)
use arrays as hashtables记录char第一次出现的位置。如果对应的index值不一样就return false。
* [217. Contains Duplicate](/Hash&#32;Table/Contains&#32;Duplicate.md)
  - sort (eg. heapsort) O(nlogn) time O(1) space
  - hashset O(n) time O(n) space
* [219. Contains Duplicate II](/Hash&#32;Table/Contains&#32;Duplicate&#32;II.md)
k范围内的数加入set，k范围外的删除，如果add不成功return true。
* [242. Valid Anagram](/Hash&#32;Table/Valid&#32;Anagram.md)
用array记录每个字母出现的次数
* [290. Word Pattern](/Hash&#32;Table/Word&#32;Pattern.md)
用array和map记录每个letter和string对应的位置
* [349. Intersection of Two Arrays](/Hash&#32;Table/Intersection&#32;of&#32;Two&#32;Arrays.md)
  - 省时间：用两个set O(n) time O(n) space
  - 省空间：先sort，再用two pointers或者binary search O(nlogn) time O(1) space
* [350. Intersection of Two Arrays II](/Hash&#32;Table/Intersection&#32;of&#32;Two&#32;Arrays&#32;II.md)
用map记录每个数出现的次数。
* [387. First Unique Character in a String](/Hash&#32;Table/First&#32;Unique&#32;Character&#32;in&#32;a&#32;String.md)
用array记录freq
* [389. Find the Difference](/Hash&#32;Table/Find&#32;the&#32;Difference.md)
  - Array记录每个char的freq
  - 比较两个string ASCII code相加之后的差值
* [771. Jewels and Stones](/Hash&#32;Table/Jewels&#32;and&#32;Stones.md)
set记录Jewels, update count.

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

* [100. Same Tree](/Tree/Same&#32;Tree.md)
DFS
* [101. Symmetric Tree](/Tree/Symmetric&#32;Tree.md)
左右孩子为镜像 - node值相等并且左-左为右-右的镜像，左-右为右-左的镜像。
* [104. Maximum Depth of Binary Tree](/Tree/Maximum&#32;Depth&#32;of&#32;Binary&#32;Tree.md)
Recursion, 左右孩子depth最大值加一。
* [107. Binary Tree Level Order Traversal II](/Tree/Binary&#32;Tree&#32;Level&#32;Order&#32;Traversal&#32;II.md)
BFS: 分层加入list的head
* [108. Convert Sorted Array to Binary Search Tree](/Tree/Convert&#32;Sorted&#32;Array&#32;to&#32;Binary&#32;Search&#32;Tree.md)
Recursion & divide and conquer. 取中间值给node，对左后孩子分别在array的左右半边取中间值循环操作。注意边界条件。helper method处理index
* [110. Balanced Binary Tree](/Tree/Balanced&#32;Binary&#32;Tree.md)
  - Top-down: 左右孩子的depth差小于一，并且左右孩子都是balanced (slow)
  - Bottom-up: 累积计算node的height，如果左右孩子height差大于一返回-1，否则返回该node的height。检测root的height是否为-1 (fast)
* [111. Minimum Depth of a Binary Tree](/Tree/Minimum&#32;Depth&#32;of&#32;a&#32;Binary&#32;Tree.md)
类似104，Recursion，注意要考虑node只有一个child的情况。
* [112. Path Sum](/Tree/Path&#32;Sum.md)
DFS Recursion. 左右孩子循环call method，sum每次减去node的值。leaf值若等于sum则return true.
* [226. Invert Binary Tree](/Tree/Invert&#32;Binary&#32;Tree.md)
Recursion, DFS, 先交换左右node，再在左右上recursively call.
* [235. Lowest Common Ancestor of a Binary Search Tree](/Tree/Lowest&#32;Common&#32;Ancestor&#32;of&#32;a&#32;Binary&#32;Search&#32;Tree.md)
返回在p和q之间的值的node，闭区间。
* [257: Binary Tree Paths](/Tree/Binary&#32;Tree&#32;Paths.md)
DFS: 用helper method储存已经遍历过的path和已经形成的list
* [404. Sum of Left Leaves](/Tree/Sum&#32;of&#32;Left&#32;Leaves.md)
如果左孩子没有左右孩子，将左孩子的值加上。如果有，recursion。对于右孩子，recursion。
* [538. Convert BST to Greater Tree](/Tree/Convert&#32;BST&#32;to&#32;Greater&#32;Tree.md)
Reverse in-order traversal, use a sum as accumulator.
* [559. Maximum Depth of N-ary Tree](/Tree/Maximum&#32;Depth&#32;of&#32;N-ary&#32;Tree.md)
同104，Recursion，用for loop找children中的最大depth。
* [590. N-ary Tree Postorder Traversal](/Tree/N-ary&#32;Tree&#32;Postorder&#32;Traversal.md)
Recursion.
* [606. Construct String From Binary Tree](/Tree/Construct&#32;String&#32;From&#32;Binary&#32;Tree.md)
  - Recursion: DFS
    - 左为空并且右不为空的时候加```()```
    - 有左：val->(->left->)
    - 有右：val->(->right->)
    - 无左有右：val->()->...
* [617. Merge Two Binary Trees](/Tree/Merge&#32;Two&#32;Binary&#32;Trees.md)
Recursion, DFS.
* [637. Average of Levels in Binary Tree](/Tree/Average&#32;of&#32;Levels&#32;in&#32;Binary&#32;Tree.md)
  - DFS: 两个list sum和count分别记录每一层，```average(node, i, sum, count)```更新两个list
  - BFS: 用temp queue或者```queue.size()```分层记录```sum/count```
* [653. Two-sum IV - Input is a BST](/Tree/Two-sum&#32;IV&#32;-&#32;Input&#32;is&#32;a&#32;BST.md)
  - 变成list之后分别从头尾iterate
  - 一边存入hashset一边检查hashset里面有没有k-val
* [669. Trim a Binary Search Tree](/Tree/Trim&#32;a&#32;Binary&#32;Search&#32;Tree.md)
DFS & Binary search.node值在反而内则在左右孩子上recursion，如果不在范围内就左移或者右移。
* [700. Search in a Binary Search Tree](/Tree/Search&#32;in&#32;a&#32;Binary&#32;Search&#32;Tree.md)
Binary search.
* [872. Leaf Similar Trees](/Tree/Leaf&#32;Similar&#32;Trees.md)
  - 先用DFS建立list of leaves再比较两个list，O(n) time and space(?)
  - Stack: ```int stack(Stack<TreeNode> s)```返回每个leaf的值，一个一个比较leaf，一旦不一样就返回false。节省空间。O(logn) space (why?)
