package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class MinWinSubString {
    public static void main(String[] args) {
        String s ="OUZODYXAZV";
        String t ="XYZ";
        System.out.println(minWindow(s,t));
    }

    private static String minWindow(String s, String t) {
        Map<Character,Integer>  need = new HashMap<>();
        int start=0;
        int left=0;
        int valid=0;
        int minVal = Integer.MAX_VALUE;
        for (int i=0;i<t.length();i++){
            need.put(t.charAt(i),need.getOrDefault(t.charAt(i),0)+1);
        }
        Map<Character,Integer> window = new HashMap<>();
        for(int right=0;right<s.length();right++){
            char rightChar = s.charAt(right);
            if (need.containsKey(rightChar)){
                window.put(s.charAt(right),window.getOrDefault(s.charAt(right),0)+1);
                if(need.get(rightChar).intValue()==window.get(rightChar).intValue()){
                    valid++;
                }
            }
            while (need.size()==valid)
            {
                if(right-left+1<minVal){
                    minVal=right-left+1;
                    start=left;
                }
                char leftChar = s.charAt(left);
                if(need.containsKey(leftChar)){
                    if(need.get(leftChar).intValue()==window.get(leftChar).intValue()){
                        valid--;
                    }
                    window.put(s.charAt(left),window.getOrDefault(s.charAt(left),0)-1);
                }
                left++;
            }
        }
        return minVal==Integer.MAX_VALUE?" ": s.substring(start,minVal+start);
    }
}
