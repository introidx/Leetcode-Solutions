package array_list;

import java.util.*;

public class ArrayListQuestions {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        System.out.println(stack.pop());

    }

    public int minimumTotal(List<List<Integer>> list) {
        int n = list.size();
        List<Integer> li = new ArrayList<>(list.get(n-1));

        for (int i = n-2; i >= 0 ; i--){
            for (int j =0; j <= i ; j++){
                li.set(j, list.get(i).get(j) + Math.min(li.get(j), li.get(j+1)));
            }
        }
        return li.get(0);
    }



    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i =0; i < nums.length ; i++){
            int remaining = target - nums[i];

            if(map.containsKey(remaining)){
                return new int[]{map.get(remaining) , i};
            }

            map.put(nums[i] , i);
        }
        return new int[2];

    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int max = Integer.MIN_VALUE;
        int n = dist.length;

        int l = 1, r = 10000000, ans = Integer.MAX_VALUE;

        while (l <= r) {
            int s = l + (r - l) / 2;

            double sum = 0;
            for (int i = 0; i < n - 1; i++) {
                double h = Math.ceil(((double) dist[i]) / s);
                sum += h;
            }
            sum += ((double) dist[n - 1]) / s;
            if (sum <= hour) {
                ans = Math.min(s, ans);
                r = s - 1;
            } else
                l = s + 1;
        }
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    public int minSpeedOnTime1(int[] dist, double hour) {
        int low = 1;
        int high = 1000000009;

        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValidTime(dist, mid, hour)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean isValidTime(int[] dist, int speed, double hour) {
        int time = 0;
        for (int i = 0; i < dist.length; i++) {
            time = (int) Math.ceil(time);
            time += (double) dist[i] / speed;
            if (time > hour) return false;
        }
        return true;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        int givenSum = 0;
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            int sum = givenSum - nums[i];

            while (low < high) {
                if (nums[low] + nums[high] == sum) {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));

                    // remove duplicates
                    while (low < high && nums[low] == nums[low + 1]) low++;
                    while (low < high && nums[high] == nums[high - 1]) high--;

                    low++;
                    high--;
                } else if (nums[low] + nums[high] > sum) high--;
                else low++;
            }
        }

        return res;

    }


}
