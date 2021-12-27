package sorting_algos;

public class MergeSort {


    private void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;

            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);

            merge(arr, start, mid, end);
        }
    }


    void merge(int arr[], int start, int mid, int right) {
        /** Find sizes of two subarrays to be merged */
        int n1 = mid - start + 1;
        int n2 = right - mid;

        /** Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /** Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[start + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        /** Merge the temp arrays */

        /** Initial indexes of first and second subarrays */
        int i = 0, j = 0;

        /** Initial index of merged subarry array */
        int k = start;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /** Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /** Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
