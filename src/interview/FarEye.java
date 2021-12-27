package interview;

public class FarEye {
    public static void main(String[] args) {

        // A- Z -> 1 -26
        // AA - AZ - 27 - 52
        // AC- 29
        // C -> res = res * 26
        // 3
        // BCD //
        // res= 0;
        // B - 2
        // res =res * 26 + 2 -> res = 0 * 26 + 2 = 2
        // BC
        // res = res * 26 + 3; -> res = 2 * 26 + 3
        // BCD
        // res = res * 26 + 4; -> res = (2 * 26 + 3) * 26 + 4 = 2 * 26 * 26 + 3 * 26 + 4



        int n = 26;
        System.out.println(helper(n));


    }

    public static String helper(int n){
        char[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z'
        };

        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            int temp = n % 26;
            if(temp == 0){
                ans.append("Z");
                n = n / 26 -1;
            }
            else{
                ans.append(c[temp - 1]);
                n = n / 26;

            }
        }
        return ans.reverse().toString();
    }
}


