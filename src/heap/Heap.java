package heap;

public class Heap {

    public static void main(String[] args) {

    }

    void insert(int[] arr, int n , int value){
        n = n +1;
        arr[n] = value;
        int i= n;
        while (i > 1){
            int parent = i / 2;
            if(arr[parent] < arr[i]){
                int temp = arr[parent];
                arr[parent] = arr[i];
                arr[i] = temp;
                i = parent;
            }else{
                return;
            }
        }
    }

    void delete(int[] arr,int n){
        arr[1] = arr[n];
        n = n-1;
        int i = 1;
        while(i < n){
            int left = arr[2 *i];
            int right = arr[2 * i + 1];

            int large = left > right ? 2 * i : 2 * i + 1;
            if(arr[i] < arr[large]){
                int temp = arr[i];
                arr[i] = arr[large];
                arr[large] = temp;

                i = large;
            }else {
                return;
            }

        }
    }
}
