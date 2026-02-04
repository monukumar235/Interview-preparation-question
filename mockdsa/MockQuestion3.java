package mockdsa;

import java.util.HashSet;
import java.util.Set;

public class MockQuestion3 {
    public static void main(String[] args) {
        String str ="abcabcbb";
        System.out.println(longestSubstring(str));
    }

    private static int longestSubstring(String str) {
        int max=0;
        int left=0;
        Set<Character> seen = new HashSet<>();
        for(int right=0;right<str.length();right++){
            while (seen.contains(str.charAt(right))){
                seen.remove(str.charAt(left));
                left++;
            }
            seen.add(str.charAt(right));
            max=Math.max(max,right-left+1);
        }
        return max;
    }
}
