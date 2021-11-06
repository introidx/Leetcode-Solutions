package sorting_algos;

public class QuickSort {

    public static void main(String[] args) {

    }

    private static void quickSort(int[] nums, int start, int end){
        if(start >= end) return;
        int pivot = nums[(start + end) /2 ];
        int left = start, right = end;

        while (left <= right){
            while(left <= right && pivot > nums[left]){
                left++;
            }
            while(left <= right && pivot < nums[right]){
                right--;
            }

            if(left <= right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left ++;
                right--;
            }
        }
        quickSort(nums, start, right);
        quickSort(nums, left, end);

    }
}
