package mockdsa;

import java.util.HashSet;
import java.util.Set;

public class MockQuestion17 {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(longestSubstring(s));
    }

    private static int longestSubstring(String s) {
        int longest=0;
        Set<Character> seen = new HashSet<>();
        int start=0;
        for(int end =0;end<s.length();end++)
        {
            while (seen.contains(s.charAt(end))){
                seen.remove(s.charAt(start));
                start++;
            }
            longest = Math.max(longest,end-start+1);
            seen.add(s.charAt(end));
        }
        return longest;
    }
}
