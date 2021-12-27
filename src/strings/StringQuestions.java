package strings;

import java.util.*;

public class StringQuestions {


    public static void main(String[] args) {


    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        if (m < n) {
            return lcsBottomUp(text2, text1, n, m);
        }
        return lcsBottomUp(text1, text2, m, n);
    }

    public int lcsBottomUp(String s1, String s2, int n, int m) {
        if (n == 0 || m == 0) {
            return 0;
        }

        int[][] arr = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    arr[i][j] = 1 + arr[i - 1][j - 1];
                } else {
                    arr[i][j] = Math.max(arr[i][j - 1], arr[i - 1][j]);
                }

            }
        }

        return arr[n][m];
    }

    public String reverseParentheses(String s) {
        //Input: s = "(u(love)i)"
        // Output: "iloveu"
        StringBuilder sb = new StringBuilder();
        Stack<StringBuilder> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(sb);
                sb = new StringBuilder();
            } else if (s.charAt(i) == ')') {
                String rev = sb.reverse().toString();
                sb = stack.pop();
                sb.append(rev);
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    int max1 = 0;

    public int maxLength(List<String> arr) {
        helper5(arr, "", 0);
        return max1;
    }

    private void helper5(List<String> arr, String current, int index) {
        if (isUnique(current)) {
            max1 = Math.max(max1, current.length());
        } else if (!isUnique(current) || index >= arr.size()) return;

        for (int i = 0; i < arr.size(); i++) {
            helper5(arr, current + arr.get(i), index + i);
        }
    }

    private boolean isUnique(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] > 1) return false;
        }
        return true;

    }

    public static int minDeletions(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(entry.getValue());
        }
        int count = 0;
        while (!pq.isEmpty()) {
            int current = pq.poll();
            if (pq.isEmpty()) {
                return count;
            }

            int next = pq.peek();
            if (current == next) {
                if (current > 0) {
                    current = current - 1;
                    pq.add(current);
                }
                count++;
            }

        }
        return count;

    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            int indexS = mapS.getOrDefault(s.charAt(i), -1);
            int indexT = mapT.getOrDefault(t.charAt(i), -1);

            if (indexS != indexT) return false;

            mapS.put(s.charAt(i), i);
            mapT.put(t.charAt(i), i);
        }
        return true;
    }

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        dfs(s, 0, new ArrayList<>());
        return res;

    }

    void dfs(String s, int index, List<String> list) {
        if (index == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                list.add(s.substring(index, i + 1));

                dfs(s, index + 1, list);
                list.remove(list.size() - 1);
            }

        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean wordBreak(String s, List<String> wordList) {
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }

        boolean[] b = new boolean[s.length() + 1];
        b[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (b[j] && set.contains(s.substring(j, i))) {
                    b[i] = true;
                    break;
                }

            }
            System.out.println(i + "= " + b[i]);

        }

        return b[s.length()];
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        int[] ans = new int[num1.length() + num2.length() - 1];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                ans[i + j] = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }

        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i - 1] += ans[i] / 10;
            ans[i] = ans[i] % 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : ans) {
            sb.append(i);
        }

        return sb.toString();

    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, "", 0, 0, res);
        return res;
    }

    public void helper(int max, String current, int open, int close, List<String> res) {
        if (current.length() == 2 * max) {
            res.add(current);
            return;
        }
        if (open < max) {
            helper(max, current + "(", open + 1, close, res);
        }
        if (close < open) {
            helper(max, current + ")", open, close + 1, res);
        }
    }

    // s = "anagram", t = "nagaram"

    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int sLength = s.length();
        int tLength = t.length();

        if (sLength != tLength) return false;

        for (int i = 0; i < sLength; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        for (Character c : map.keySet()) {
            if (map.get(c) != 0) return false;
        }

        return true;
    }

    //  pattern = "abba", s = "dog cat cat dog"
    public boolean wordPattern(String pattern, String str) {
        char[] patternChars = pattern.toCharArray();
        String[] strArray = str.split(" ");

        int patternLength = patternChars.length;
        int strArrayLength = strArray.length;

        if (patternLength != strArrayLength) return false;

        Map<Character, String> map = new HashMap<>();

        for (int i = 0; i < patternLength; i++) {
            if (map.containsKey(patternChars[i])) {
                if (!map.get(patternChars[i]).equals(strArray[i])) {
                    return false;
                }
            } else if (map.containsValue(strArray[i])) {
                return false;
            }
            map.put(patternChars[i], strArray[i]);
        }
        return true;
    }

    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    public String kthDistinct(String[] arr, int k) {

        int dist = 0;
        for (int i = 0; i < arr.length; i++) {
            int j;
            for (j = 0; j < arr.length; j++) {
                if (i != j && arr[i].equals(arr[j])) {
                    break;
                }
            }
            if (j == arr.length) {
                dist++;
            }
            if (dist == k) {
                return arr[i];
            }
        }
        return "";
    }

    // min adjacent swaps to make a string palindrome
    /// start
    private static int getNoOfSwaps(String str) {
        if (str == null || str.length() == 0) return -1;
        int swaps = 0;

        if (!isPalindromeAnagram(str)) {
            return -1;
        }

        char[] chars = str.toCharArray();

        int l = 0;
        int h = chars.length - 1;

        while (l < h) {
            if (chars[l] != chars[h]) {
                int k = h;

                while (k > l && chars[k] != chars[l]) {
                    k--;
                }

                if (k == l) {
                    swap(chars, l, l + 1);
                    swaps++;
                } else {
                    while (k < h) {
                        swap(chars, k, k + 1);
                        swaps++;
                        k++;
                    }
                    l++;
                    h--;
                }
            } else {
                l++;
                h--;
            }
        }

        return swaps;
    }


    private static void swap(char[] chars, int k, int i) {
        char temp = chars[k];
        chars[k] = chars[i];
        chars[i] = temp;
    }

    private static boolean isPalindromeAnagram(String s) {
        int[] count = new int[26];
        int oddCount = 0;

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
            }
        }

        if (oddCount > 1) {
            return false;
        }
        return true;

    }
    /// end


}
