# Hash字符 字符统计类问题

## Valid Anagram

## Find All Anagrams in a String
- 暴力解法
  - 找出s所有的子串并且找出p的anagram
  - 假设p长度为l，s长度为n。枚举所有s中长度为l的子串，并用hash统计他们元素出现的个数
  - 时间复杂度
    - n个子串
      - 每次统计子串中元素出现的个数O(l)
      - 每次和p对比次数O(256)
    - 总体O(nl)
- 优化
  - 相邻两个子串的差别
