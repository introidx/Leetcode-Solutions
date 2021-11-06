package map;

import java.nio.charset.CharsetEncoder;
import java.util.*;

public class MapQuestions {
    public static void main(String[] args) {

        String s = "ceabaacb";
        minDeletions(s);


    }

    public static int minDeletions(String s) {
        int count =0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i =0; i < s.length() ; i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c,0) + 1);
        }
        //for s "ceabaacb"
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (Map.Entry<Character, Integer> entry : map.entrySet()){
            pq.add(entry.getValue());
        }
        // queue will look like
        // 3
        // 2
        // 2
        // 1

        while (pq.size() > 0){
            int current = pq.remove();
            if(pq.isEmpty()){
                return count;
            }

            int next = pq.peek();
            if(current == next){
                if (current > 1){
                    pq.add(current -1);
                }
                count++;
            }

        }
        return count;
    }
}
