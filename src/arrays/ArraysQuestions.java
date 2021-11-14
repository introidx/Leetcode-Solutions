package arrays;

import java.util.*;
import java.util.Collections;
import java.util.Comparator;

public class ArraysQuestions {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, -4};
        System.out.println(findK(input));
    }

    public int longestConsecutive(int[] nums) {
    //  Input: nums = [100,4,200,1,3,2]
    //  Output: 4
        Set<Integer> set = new HashSet<>();
        for (int i : nums)
            set.add(i);

        int maxStreak =0;
        for (int i : set){
            if(!set.contains(i -1)){
                int current = i;
                int currentStreak = 1;

                while(set.contains(current + 1)){
                    current = current + 1;
                    currentStreak++;
                }

                maxStreak = Math.max(maxStreak, currentStreak);
            }
        }
        return maxStreak;
    }

    public static boolean canReach(int nums[], int start){
        if(nums[start] == 0){
            return true;
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        while(queue.size()>0){
            int curr = queue.poll();
            if(nums[curr] == 0){
                return true;
            }
            int index = curr - nums[curr];
            if(index >= 0 && !visited.contains(index)){
                visited.add(index);
                queue.offer(index);
            }
            index = curr + nums[curr];
            if(index < nums.length && !visited.contains(index)){
                visited.add(index);
                queue.offer(index);
            }
        }
        return false;
    }

    // [3, 2, -2, 5, -3]
    public static int findK(int[] arr){
        Arrays.sort(arr);
        // -3,-2, 2, 3,5
        int i =0;
        int j = arr.length-1;

        while (i <= j){
            if(arr[i] == - arr[j]){
                return arr[j];
            }
            if(arr[j] > - arr[i]){
                j--;
            }else {
                i++;
            }

        }
        return 0;


    }

    public static int step(Integer[] input){
        if(input == null || input.length == 0) return 0;
        int steps =0;
        Arrays.sort(input , Collections.reverseOrder());

        for (int i =1; i < input.length ; i++){
            if (input[i] != input[i-1]){
                steps += i;
            }
        }

        return steps;

    }

    public void sortColors(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return;

        int start = 0;
        int end = nums.length - 1;
        int index = 0;

        while (index <= end && start < end) {
            if (nums[index] == 0) {
                nums[index] = nums[start];
                nums[start] = 0;
                start++;
                index++;
            } else if (nums[index] == 2) {
                nums[index] = nums[end];
                nums[end] =2;
                end--;

            } else {
                index++;
            }
        }
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0]+ nums[1] + nums[nums.length -1];

        for (int i = 0; i < nums.length ; i++){
            int start =i +1;
            int end = nums.length -1;

            while(start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if(sum > target) end--;
                else start++;

                if(Math.abs(sum - target) < Math.abs(target - result))
                    result = sum;
            }
        }

        return result;
    }

    public static int maxTwoEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingLong(arr -> arr[0]));
        int max = 0;
        if (events.length == 1) return events[0][2];
        if (events.length == 2) {
//            System.out.println("i am here");
            int[] event1 = events[0];
            int[] event2 = events[1];

            boolean isNotOver =
                    isNotOverlapping(event1[0] , event1[1],
                            event2[0] , event2[1]);

            if (isNotOver){
                int maxEither = Math.max(event1[2] , event1[2]);
                int sum = event1[2] + event2[2];
                max = Math.max(maxEither , sum);
                return max;
            }else {
                return Math.max(event1[2] , event2[2]);
            }
        }


        for (int i = 0 ; i < events.length -1; i++){
            int[] event1 = events[i];
            int currMax = event1[2];
            max = Math.max(max, currMax);

            for (int j = i+1; j < events.length ; j++){
                int[] event2 = events[j];

                boolean isNotOver =
                        isNotOverlapping(event1[0] , event1[1],
                                event2[0] , event2[1]);

                if(isNotOver){
//                    System.out.println(isNotOver);
                    currMax = event1[2] + event2[2];
//                    System.out.println(currMax);
                    max = Math.max(max, currMax);
                }
            }
        }

        return max;

    }

    public static boolean isNotOverlapping(long start1, long end1, long start2, long end2){
        if(start2 > start1  && end2 > end1 && start2 > end1 && end2 > start1 && end1 < start2){
//            System.out.println("start1 = " + start1 + " end1= "+end1 + " start2 = " + start2 + " end2 = " + end2);
            return true;
        }
        return false;
    }


}
