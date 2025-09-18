package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class MiniWindowSizeSubstring {
    public static void main(String[] args) {
        String s ="ADOBECODEBANC";
        String t="ABC";
        System.out.println(minWindow(s,t));
    }

    private static String minWindow(String s, String t) {
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();

        int start=0;
        int valid=0;
        int left=0;
        int minLen=Integer.MAX_VALUE;
        for (int i=0;i<t.length();i++){
            need.put(t.charAt(i),need.getOrDefault(t.charAt(i),0)+1);
        }

        for(int right=0;right<s.length();right++){
            char rigthChar =s.charAt(right);

            if(need.containsKey(rigthChar)){
                window.put(rigthChar,window.getOrDefault(rigthChar,0)+1);
                if(window.get(rigthChar).intValue() == need.get(rigthChar).intValue()){
                    valid++;
                }

                while (valid == need.size()){

                    if(right-left+1<minLen){
                        minLen=right-left+1;
                        start=left;
                    }

                    char d = s.charAt(left);
                    if(need.containsKey(d)){
                        if(window.get(d).intValue() == need.get(d).intValue()){
                            valid--;
                        }
                        window.put(d,window.getOrDefault(d,0)-1);
                    }
                    left++;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? " " : s.substring(start,start+minLen);
    }
}
