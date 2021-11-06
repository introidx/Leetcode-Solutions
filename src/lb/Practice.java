package lb;

import java.util.*;

public class Practice {

    public static String[] arr = {
            "2", "22", "222", //abc
            "3", "33", "333", //def
            "4", "44", "444", //ghi
            "5", "55", "555", //jkl
            "6", "66", "666", // mno
            "7", "77", "777", "7777", //pqrs
            "8", "88", "888", //tuv
            "9", "99", "999", "9999" //wxyz

    };

    public static void main(String[] args) {
        String s = "Prakash";
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        System.out.println(sb.reverse().toString());
        System.out.println(sb.toString());


    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum , map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }


    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, helper1(grid, i, j));
                }
            }
        }
        return max;
    }

    private int helper1(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int count = 1;
        count += helper1(grid, i + 1, j);
        count += helper1(grid, i - 1, j);
        count += helper1(grid, i, j + 1);
        count += helper1(grid, i, j - 1);

        return count;
    }

    public static int minOps(String A, String B) {
        if (A.length() != B.length()) return -1;
        int[] count = new int[256];

        for (int i = 0; i < A.length(); i++) {
            count[A.charAt(i)]++;
            count[B.charAt(i)]--;
        }

        for (int i = 0; i < 256; i++) {
            if (count[i] != 0) return -1;
        }
        int i = A.length() - 1;
        int j = B.length() - 1;
        int res = 0;

        while (i >= 0) {
            if (A.charAt(i) != B.charAt(j)) res++;
            else j--;
            i--;
        }

        return res;
    }

    public String reverseParentheses(String s) {
        // "(u(love)i)" -> "iloveu"
        // "(abcd)" "dcba"
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

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

    public int twoEggDrop(int floors) {
        int eggs = 2;
        int[][] dp = new int[floors + 1][eggs + 1];
        return helper(floors, eggs, dp);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        path.add(0);
        dfsSearch(graph, 0, res, path);

        return res;
    }

    private void dfsSearch(
            int[][] graph,
            int node,
            List<List<Integer>> res,
            List<Integer> path
    ) {
        if (node == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int nextNode : graph[node]) {
            path.add(nextNode);
            dfsSearch(graph, nextNode, res, path);
            path.remove(path.size() - 1);
        }
    }

    public int helper(int f, int e, int[][] dp) {
        if (e == 1 || f <= 1) return f;
        if (dp[f][e] > 0) return dp[f][e];

        int min = Integer.MIN_VALUE;
        for (int i = 1; i <= f; i++) {
            min = Math.min(min, 1 + Math.max(helper(f - 1, e - 1, dp), helper(f - i, e, dp)));
        }
        dp[f][e] = min;
        return min;
    }

    static int getMinCharToAddedToMakeStringPalin(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);

        String rev = sb.reverse().toString();
        sb.reverse().append("#").append(rev);

        int[] lps = computeLPSArray(sb.toString());
        return str.length() - lps[sb.length() - 1];
    }

    public static int[] computeLPSArray(String str) {
        int n = str.length();
        int i = 1;
        int len = 0;
        int[] lps = new int[n];

        while (i < n) {
            if (str.charAt(i) == str.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    static String printSequence(String input) {
        int n = input.length();
        String res = "";
        for (int i = 0; i < n; i++) {
            if (input.charAt(i) == ' ') {
                res += "0";
            } else {
                int pos = input.charAt(i) - 'A';
                res += arr[pos];
            }
        }

        return res;

    }

    static int countPS(String str) {
        int N = str.length();

        // create a 2D array to store the count
        // of palindromic subsequence
        int[][] cps = new int[N][N];

        // palindromic subsequence of length 1
        for (int i = 0; i < N; i++)
            cps[i][i] = 1;

        // check subsequence of length L is
        // palindrome or not
        for (int L = 2; L <= N; L++) {
            for (int i = 0; i <= N - L; i++) {
                int k = L + i - 1;
                if (str.charAt(i) == str.charAt(k)) {
                    cps[i][k] = cps[i][k - 1] + cps[i + 1][k] + 1;
                } else {
                    cps[i][k] = cps[i][k - 1] + cps[i + 1][k] - cps[i + 1][k - 1];
                }
            }
        }

        // return total palindromic subsequence
        return cps[0][N - 1];
    }


    // This function returns
    // value of a Roman symbol
    int value(char r) {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }

    // Finds decimal value of a
    // given romal numeral
    int romanToDecimal(String str) {
        // Initialize result
        int res = 0;

        for (int i = 0; i < str.length(); i++) {
            // Getting value of symbol s[i]
            int s1 = value(str.charAt(i));

            // Getting value of symbol s[i+1]
            if (i + 1 < str.length()) {
                int s2 = value(str.charAt(i + 1));

                // Comparing both values
                if (s1 >= s2) {
                    // Value of current symbol
                    // is greater or equalto
                    // the next symbol
                    res = res + s1;
                } else {
                    // Value of current symbol is
                    // less than the next symbol
                    res = res + s2 - s1;
                    i++;
                }
            } else {
                res = res + s1;
            }
        }

        return res;
    }

    static String secFrequent(String arr[], int N) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());

        Map.Entry<String, Integer> first = pq.poll();
        Map.Entry<String, Integer> second = pq.poll();
        String res = second.getKey();

        System.out.println("First " + second.getKey());

        return res;
    }

    //Longest Common Subsequence
    static int lcs(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0) return 0;
        if (X[m - 1] == Y[n - 1]) return 1 + lcs(X, Y, m - 1, n - 1);
        else return Math.max(lcs(X, Y, m - 1, n), lcs(X, Y, m, n - 1));
    }
}
