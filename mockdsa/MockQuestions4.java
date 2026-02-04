package mockdsa;

import java.util.HashSet;
import java.util.Set;

public class MockQuestions4 {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    private static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int left =0;
        Set<Character> seen = new HashSet<>();

        for(int right=0;right<s.length();right++)
        {
            while (seen.contains(s.charAt(right)))
            {
                seen.remove(s.charAt(left));
                left++;
            }
            max= Math.max(max,right-left+1);
            seen.add(s.charAt(right));
        }
        return max;
    }
}
