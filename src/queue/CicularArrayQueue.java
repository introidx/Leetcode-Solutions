package queue;

public class CicularArrayQueue {
    int[] arr;
    int N;
    int front;
    int rear;
    // O(1) for enque and deque
    public CicularArrayQueue(int n) {
        N = n;
        arr = new int[N];
        front = -1;
        rear = -1;
    }

    public void enque(int val){
        if((rear + 1) % N == front) return; // when front and rear are same means arr
        // arr is full
        if(front == -1) front =0;
        // incre rear and put val
        rear = (rear + 1) % N;
        arr[rear] = val;
    }

    public int deque() throws Exception {
        if(front == -1) throw new Exception();
        int temp = arr[front];

        if(front == rear) front = rear =-1;
        else {
            front = (front + 1) % N;
        }
        return temp;
    }
}
