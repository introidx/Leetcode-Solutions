package bit_manipulation;

public class Bit {
    public static void main(String[] args) {
        System.out.println(addNumbers(5 , 3));

    }

    public static int addNumbers(int x , int y){
        if(y == 0) return x;
        return addNumbers(x ^ y , (x & y) << 1);
    }

    public int minFlips(int a, int b, int c) {
        int count = 0;
        while (a != 0 || b != 0 || c != 0) {
            int A = a & 1;
            int B = b & 1;
            int C = c & 1;

            if (C == 1) {
                if (A == 0 && B == 0) {
                    count++;
                }
            } else {
                if (A == 1 && B == 1) {
                    count += 2;
                } else if (A == 1 || B == 1) {
                    count++;
                }
            }
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }
        return count;
    }
}
