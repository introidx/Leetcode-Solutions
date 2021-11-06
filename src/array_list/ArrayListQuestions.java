package array_list;

import java.util.*;

public class ArrayListQuestions {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        System.out.println(stack.pop());

    }

    public List<List<Integer>> threeSum(int[] nums) {
        int givenSum = 0;
        List<List<Integer>> res = new ArrayList<>();

        for (int i =0; i < nums.length ; i++){
            int low = i+1;
            int high = nums.length -1;
            int sum = givenSum - nums[i];

            while (low < high){
                if(nums[low] + nums[high] == sum){
                    res.add(Arrays.asList(nums[i] , nums[low], nums[high]));

                    // remove duplicates
                    while(low < high && nums[low] == nums[low + 1]) low++;
                    while (low < high && nums[high] == nums[high -1]) high--;

                    low++;
                    high--;
                }else if(nums[low] + nums[high] > sum) high--;
                else low++;
            }
        }

        return res;

    }


}
