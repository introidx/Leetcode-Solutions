package math;

public class MathsProblems {
    public static void main(String[] args) {
        int a = findThePositionOFLift(5, 'u', 4);
        System.out.println(a);

    }
    /* int currPos, char d/u, int steps
    *  if currPos < 1 || currPos > 20 return -1
    *
    *
    * */

    public static int findThePositionOFLift(int currPos, char c, int steps){
        if(c == 'd'){
            currPos = currPos - steps;
        }else {
            currPos = currPos + steps;
        }
        return currPos >= 1 && currPos <=20  ? currPos : -1;
    }
}
