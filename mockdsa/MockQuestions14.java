package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class MockQuestions14 {
    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(findIndexofFirstNonRepeatingChar(s));
    }

    private static int findIndexofFirstNonRepeatingChar(String s) {
        Map<Character,Integer> freq = new HashMap<>();
        for (int i=0;i<s.length();i++)
        {
            freq.put(s.charAt(i),freq.getOrDefault(s.charAt(i),0)+1);
        }
        for(int i=0;i<s.length();i++)
        {
            if(freq.get(s.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }
}
