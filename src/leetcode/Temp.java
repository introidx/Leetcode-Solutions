package leetcode;

import java.util.*;

public class Temp {

    public static void main(String[] args) {
//        System.out.println(backspaceCompare("a##c" , "#a#c"));
//        int[] nums = {1,1,1,2};
//        System.out.println(removeDuplicates(nums));

        System.out.println(test("alphaadida"));


    }


    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = new ListNode(0);
            ListNode p = result;

            int carry =0;

            while (l1 != null || l2 != null || carry != 0){
                if(l1 != null){
                    carry += l1.val;
                    l1 = l1.next;
                }
                if(l2 != null){
                    carry += l2.val;
                    l2 = l2.next;
                }

                p.next = new ListNode (carry %10);
                carry = carry / 10;
                p = p.next;
            }

            return result.next;
        }
    }


    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static int test(String s){
        Map<Character , Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()){
            map.put(c , map.getOrDefault(c, 0) +1);
        }
        int res =0;
        for (Character c : map.keySet()){
            if(map.get(c) > 0){
                res++;
            }
        }

        return s.length() - res;

    }

    private static int specialString(String s){
        int min = Integer.MAX_VALUE;

        int n = s.length();
        for (int i =1; i < n; i++){
            String s1 = s.substring(0,i);
            String s2 = s.substring(i);

            min= Math.min(min, minDistance(s1 , s2));

        }

        return min;
    }

    private static int minDistance(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] arr = new int[n+1][m+1];

        for (int i =0; i <= n ; i++){
            arr[i][0] = i;
        }

        for (int j =0; j <= m ; j++){
            arr[0][j] = j;
        }

        for (int i = 1; i <= n; i++){
            for (int j =1; j <= m ; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    arr[i][j] = arr[i-1][j-1];
                }else {
                    arr[i][j] = 1 + Math.min(arr[i-1][j-1] , Math.min(arr[i-1][j] , arr[i][j-1]));
                }
            }
        }

        return arr[n][m];
    }




    static void insertionSort(int[] arr){
        int n  =arr.length;
        for (int i =1; i < n ; i++){
            int temp = arr[i];
            int j  =i -1;
            while (j >= 0 && arr[j] > temp){
                arr[j +1] = arr[j];
                j--;
            }

            arr[j+1] = temp;
        }
    }



    static void sort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
            System.out.println(Arrays.toString(arr));
        }
    }

    public static boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node: nodes) {
            if (--diff < 0) {
                return false;
            }
            System.out.println("diff" + diff);
            if (!node.equals("#")) diff += 2;
        }
        return diff == 0;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String , List<String>> map = new HashMap<>();
        for (String s : strs){
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String temp = new String(c);
            if(!map.containsKey(temp)){
                map.put(temp, new ArrayList<>());
            }
            map.get(temp).add(s);

        }

        res.addAll(map.values());

        return res;


    }

    public int[] sortArray(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i =0 ; i< nums.length ; i++){
            pq.add(nums[i]);
        }
        Iterator i = pq.iterator();
        int j =0;
        int[] res = new int[nums.length];
        while(i.hasNext()){
            res[j++] = (int) i.next();
        }
        return res;
    }

    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int res = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != nums[i - 1]){
                nums[res] = nums[i];
                System.out.println(nums.toString());
                res++;
            }
        }
        return res;
    }


    public static int countGoodNumbers(long n) {

        ArrayList<Integer> primeList = new ArrayList<>();

        primeList.add(2);
        primeList.add(3);
        primeList.add(5);
        primeList.add(7);

        if(n == 0) return 0;
        if(n == 1) return 5;

        String first ="1";
        for (int i =0; i < n-1 ; i++){
            first += "0";
        }

        String second = "9";
        for (int i =0; i < n-1 ; i++){
            second += "9";
        }

        long firstNum = Integer.parseInt(first);
        long secondNum = Integer.parseInt(second);

        System.out.println("firstNum " + firstNum);
        System.out.println("secondNum " + secondNum);

        int res=0;
        for (long i = firstNum ; i <= secondNum ; i++){
            String temp = "" + i;
            int tempLength = temp.length();
            int count =0;
            for (int j =0; j < tempLength ; j = j+2){
                int c = Character.getNumericValue(temp.charAt(j));
                if (c % 2 == 0){
                    count++;
                }
            }
            for (int j = 1; j < tempLength ; j = j +2){
                int c = Character.getNumericValue(temp.charAt(j));
                if (primeList.contains(c)){
                    count++;
                }
            }

            if (count == tempLength-1){
                res++;
                System.out.println("temp" + temp);
            }
        }

        return res;
    }



    public static boolean isInterleave(String s1, String s2, String s3) {
        String s4 = s1 + s2;
        Arrays.sort(s3.toCharArray());
        Arrays.sort(s4.toCharArray());
        System.out.println(s3);
        System.out.println(s4);
        return s3.equals(s4);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        List<Integer> res = new ArrayList();
        for (Map.Entry<Integer, Integer> e : map.entrySet()){
            if(e.getValue() >= k){
                res.add((int)e.getKey());
            }
        }
        int[] a = new int[res.size()];
        for (int i =0; i < res.size(); i++){
            a[i] = res.get(i);
        }

        return a;

    }

    public static String decodeAtIndex(String s, int k) {
        String res ="";
        for (char c : s.toCharArray()){
            if(Character.isDigit(c)){
                int n = c -'0';
                System.out.println("n =" +n);
                String temp = res;
                for (int i =0 ; i < n -1; i++){
                    res += temp;
                    System.out.println(res);
                }
            }else {
                res += c;
                System.out.println(res);
            }
        }
        System.out.println(res);
        return String.valueOf(res.charAt(k-1));
    }


    public static String reverseVowels(String s) {
        String vowels = "aeiouAEIOU";
        char[] c = s.toCharArray();
        int left=0, right = s.length()-1;
        while (left <= right){
            if (vowels.contains(c[left]+"")){
               if (vowels.contains(c[right]+"")){
                   char temp = c[left];
                   c[left] = c[right];
                   c[right] = temp;
                   left++;
                   right--;
               }else {
                   right--;
               }
            }else {
                left++;
            }

        }
        String res ="";
        for (int i =0 ; i < c.length ; i++){

            res += c[i];
        }

        return res;
    }

    public static boolean backspaceCompare(String s, String t) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> t1 = new Stack<>();

        for (int i =0 ; i < s.length() ; i++){
            if(s.charAt(i) == '#'){
                if (!s1.isEmpty()){
                    s1.pop();
                }else {
//                    continue;
                }
            }else {
                s1.push(s.charAt(i));
            }
        }

        for (int i =0 ; i < t.length() ; i++){
            if(t.charAt(i) == '#'){
                if (!t1.isEmpty()){
                    t1.pop();
                }else{
//                    continue;
                }
            }else {
                t1.push(t.charAt(i));

            }
        }

        String s2 ="";
        while(!s1.isEmpty()){
            s2 += s1.pop();
        }

        String t2 ="";
        while(!t1.isEmpty()){
            t2 += t1.pop();
        }

        System.out.println("s2 =" + s2);
        System.out.println("t2 =" + t2);
        if(s2.equals(t2)) return true;
        return false;
    }

    public static int threeSumClosest(int[] arr, int target){
        Arrays.sort(arr);
        int n = arr.length;
        int result = arr[0] + arr[1] + arr[arr.length-1];

        for (int i =0 ; i< n-2 ; i++){
            int low = i + 1;
            int end = n -1;

            while(low < end){
                int sum = arr[i] + arr[low] + arr[end];
                if(Math.abs(sum - target) < Math.abs(target - result)){
                    result = sum;
                }
                if(sum > target) end--;
                else low++;
            }
        }

        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int total_Sum = 0;
        for (int i = 0 ; i< nums.length ; i++){
            int req_sum = total_Sum - nums[i];
            int low = i+1;
            int high = nums.length -1;

            while (low < high){
                int temp_sum = nums[low] + nums[high];
                if(temp_sum == req_sum){
                    res.add(Arrays.asList(nums[i] , nums[low] , nums[high]));

                    while (low < high && nums[low] == nums[low+1]) low++;
                    while (low < high && nums[high] == nums[high -1]) high--;
                    low++;
                    high--;
                }else if(temp_sum > req_sum) high--;
                else low++;
            }
        }

        return res;

    }

    //Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
    //Output: [[1,6],[8,10],[15,18]]
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1){
            return intervals;
        }

        Arrays.sort(intervals, (arr1,arr2) -> Integer.compare(arr1[0] , arr2[0]));
        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);

        for (int[] interval : intervals){
            if(interval[0] < newInterval[1]){
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }else {
                newInterval = interval;
                res.add(newInterval);
            }
        }

        return res.toArray(new int[res.size()][]);

    }
}
