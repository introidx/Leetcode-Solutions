package cp;

import java.lang.reflect.Array;
import java.util.*;

public class Importent {
    public static void main(String[] args) {
//        boolean[] arr = sieveOfEratosthenes(12);
//        for (int i =0; i <= 12 ; i++){
//            System.out.println(i + " " + arr[i]);
//        }


    }

    // Function to print the catalan number
    static void catalan(int n) {
        int cat = 1;

        // For the first number
        System.out.print(cat + " "); // C(0)

        // Iterate till N
        for (int i = 1; i < n; i++) {
            // Calculate the number
            // and print it
            cat *= (4 * i - 2);
            cat /= (i + 1);
            System.out.print(cat + " ");
        }
    }

    public static long fastPower(long a, long b, int n) {
        // power of two numbers with modulo n
        /* Modulo Property
        (a + b) % n = (a % n + b % n) % n
        (a * b) % n = (a % n * b % n) % n;

        here n = 1000000007
         */
        long res = 1;
        while (b > 0) {
            // check if b is odd
            if ((b & 1) != 0) { // means b is odd
                res = (res * a % n) % n;
            }

            a = (a % n * a % n) % n;
            b = b >> 1; // b = b /2

        }
        return res;
    }

    // power of two numbers
    public static long fastPower1(long a, long b) {
        long res = 1;
        while (b > 0) {
            // check if b is odd
            if ((b & 1) != 0) { // means b is odd
                res = res * a;
            }

            a = a * a;
            b = b >> 1; // b = b /2

        }
        return res;
    }

    // HCF of two numbers
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }


    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            for (int j = 2 * i; j <= n; j = j + i) {
                isPrime[j] = false;
            }
        }
        return isPrime;
    }
}
