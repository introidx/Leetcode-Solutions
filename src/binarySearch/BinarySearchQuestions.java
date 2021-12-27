package binarySearch;

import java.util.*;

public class BinarySearchQuestions {

    public static void main(String[] args) {
        int[] arr= {1,2,4,8,9};
        int cows = 3;
        System.out.println(aggressiveCows(arr,cows));

    }

    public static int aggressiveCows(int[] arr, int cows) {
        Arrays.sort(arr);
        int low = 1;
        int high = arr[arr.length - 1] - arr[0];

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if(isPossible(arr,cows,mid,arr.length)){
                low = mid +1;
            }else{
                high = mid -1;
            }

        }
        return high;

    }

    public static boolean isPossible(int[] arr, int cows, int minDist, int n) {
        int countCows = 1;
        int lastPlacedCow = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] - lastPlacedCow >= minDist){
                countCows++;
                lastPlacedCow= arr[i];
            }
        }
        if(countCows >= cows) return true;
        return false;
    }
}
