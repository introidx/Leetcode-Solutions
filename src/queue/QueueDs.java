package queue;

public class QueueDs {

    int[] arr;
    int capacity;
    int rear;

    public void QueueDs(int n){
        arr = new int[n];
        capacity = n;
        rear = -1;
    }

    public int deque() throws Exception {
        if(rear == -1){
            // empty
            throw new Exception();
        }

        int temp =arr[0];
        for (int i =0;i < rear ; i++){
            arr[i] = arr[i+1];
        }
        rear--;
        return temp;
    }

    public void enque(int val) throws Exception {
        if(rear == capacity -1){
            // overflow
            throw new Exception();
        }

        arr[rear] = val;
    }

    public int peek() throws Exception {
        if(rear == -1){
            // empty
            throw new Exception();
        }
        return arr[0];
    }

}
