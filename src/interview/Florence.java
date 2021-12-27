package interview;

public class Florence {
    public static void main(String[] args) {
        int[] nums= {0,1,1,0,0,1,0,1};
        sortColors(nums);
        for(int n : nums){
            System.out.print(n + " ");
        }

    }

    public static void sortColors(int[] nums) {
        int start =0;
        int end = nums.length -1;
        int index = 0;

        while (index <= end && start < end){
            if(nums[index] == 0){
                nums[index] = nums[start];
                nums[start] = 0;
                start++;
                index++;
            }else if(nums[index] == 1){
                nums[index] = nums[end];
                nums[end] = 1;
                end--;
            }
        }
    }


}
