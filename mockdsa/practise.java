package mockdsa;

//Given a string s, return the length of the longest substring without repeating characters.
//
//
//        s = "abcabcbb" → 3 ("abc")
//        s = "bbbbb" → 1 ("b")
//        s = "pwwkew" → 3 ("wke")

import java.util.HashSet;

public class practise {
    public static void main(String[] args) {
        String s= "bbbbb";
        System.out.println(longestSubstring(s));
    }

    private static int longestSubstring(String s) {
        int max =0;
        HashSet<Character> seen = new HashSet<>();
        int start=0;
        for(int end =0;end<s.length();end++)
        {
            while (seen.contains(s.charAt(end))){
                seen.remove(s.charAt(start));
                start++;
            }
            seen.add(s.charAt(end));
            max = Math.max(max,end-start+1);
        }
        for(char c :seen){
            System.out.print(c);
        }
        return max;
    }
}
