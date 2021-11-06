package strings;

import java.util.*;

public class StringQuestions {
    public static void main(String[] args) {

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
