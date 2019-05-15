# 843. Guess the Word
5/14/19
*Hard*

This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

Example 1:
```
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
```

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Note:  Any solutions that attempt to circumvent the judge will result in disqualification.

## Solutions
- my code easier to follow
```Java
// to determine how many letters match between two words
int match(String a, String b) {
    int n = 0;
    for (int i = 0; i < 6; i++) {
        if (a.charAt(i) == b.charAt(i)) {
            n++;
        }
    }
    return n;
}

// this does not work, because temp has local scope?

// void shrink(List<String> words, String guess, int matches) {
//     List<String> temp = new ArrayList<>();
//     for (String word : words) {
//         int n = match(word, guess);
//         if (!word.equals(guess) && n == matches) {
//             temp.add(word);
//         }
//     }
//     words = temp;
// }

public void findSecretWord(String[] wordlist, Master master) {
    // turning array into list
    List<String> words = new ArrayList<>();
    for (String word : wordlist) {
        words.add(word);
    }
    int times = 10;

    while (times > 0) {
        // how to pick guess?
        // random does not guarantee an optimal pick.
        // for each word, we count how many other words have 0 matches with this word.
        // each time we want to pick the word with smallest number of zero matches.
        // map: word -> # of 0 matches
        Map<String, Integer> zerocount = new HashMap<>();
        for (String w1 : words) {
            for (String w2 : words) {
                if (match(w1, w2) == 0) {
                    if (zerocount.containsKey(w1)) {
                        zerocount.put(w1, zerocount.get(w1) + 1);
                    } else {
                        zerocount.put(w1, 1);
                    }
                }
            }
        }
        // start with a worst case
        String guess = "";
        int count = 1000;
        for (String word : words) {
            // default: no 0 matches
            int c = 0;
            if (zerocount.containsKey(word)) {
                c = zerocount.get(word);
            }
            // update candidate if this word has smaller number of 0 matches
            if (c < count) {
                guess = word;
                count = c;
            }
        }
        // guess
        int matches = master.guess(guess);
        // update the word list
        List<String> temp = new ArrayList<>();
        for (String word : words) {
            int n = match(word, guess);
            if (!word.equals(guess) && n == matches) {
                temp.add(word);
            }
        }
        words = temp;
        times--;
    }
}
```

```Java
class Solution {
    int[][] H;
    public void findSecretWord(String[] wordlist, Master master) {
        int N = wordlist.length;
        H = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = i; j < N; ++j) {
                int match = 0;
                for (int k = 0; k < 6; ++k)
                    if (wordlist[i].charAt(k) == wordlist[j].charAt(k))
                        match++;
                H[i][j] = H[j][i] = match;
            }

        List<Integer> possible = new ArrayList();
        List<Integer> path = new ArrayList();
        for (int i = 0; i < N; ++i) possible.add(i);

        while (!possible.isEmpty()) {
            int guess = solve(possible, path);
            int matches = master.guess(wordlist[guess]);
            if (matches == wordlist[0].length()) return;
            List<Integer> possible2 = new ArrayList();
            for (Integer j: possible) if (H[guess][j] == matches) possible2.add(j);
            possible = possible2;
            path.add(guess);
        }

    }

    public int solve(List<Integer> possible, List<Integer> path) {
        if (possible.size() <= 2) return possible.get(0);
        List<Integer> ansgrp = possible;
        int ansguess = -1;

        for (int guess = 0; guess < H.length; ++guess) {
            if (!path.contains(guess)) {
                ArrayList<Integer>[] groups = new ArrayList[7];
                for (int i = 0; i < 7; ++i) groups[i] = new ArrayList<Integer>();
                for (Integer j: possible) if (j != guess) {
                    groups[H[guess][j]].add(j);
                }

                ArrayList<Integer> maxgroup = groups[0];
                for (int i = 0; i < 7; ++i)
                    if (groups[i].size() > maxgroup.size())
                        maxgroup = groups[i];

                if (maxgroup.size() < ansgrp.size()) {
                    ansgrp = maxgroup;
                    ansguess = guess;
                }
            }
        }

        return ansguess;
    }
}
```
