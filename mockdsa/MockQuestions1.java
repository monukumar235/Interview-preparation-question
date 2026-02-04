package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class MockQuestions1 {
    public static void main(String[] args) {
        String input ="aabb";
        System.out.println(firstUnique(input));
    }

    private static int firstUnique(String input) {
        Map<Character,Integer> freq = new HashMap<>();
        for (int i=0;i<input.length();i++)
        {
            freq.put(input.charAt(i),freq.getOrDefault(input.charAt(i),0)+1);
        }
        for (int i=0;i<input.length();i++)
        {
            if(freq.get(input.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }
}
