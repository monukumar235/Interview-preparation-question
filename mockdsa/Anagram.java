package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public static void main(String[] args) {
        String s1="listen";
        String s2="silent";
        System.out.println(validAnagram(s1,s2));
    }

    private static boolean validAnagram(String s1, String s2) {
        Map<Character,Integer> s1Map=new HashMap<>();
        Map<Character,Integer> s2Map=new HashMap<>();
        for(int i=0;i<s1.length();i++){
            s1Map.put(s1.charAt(i), s1Map.getOrDefault(s1.charAt(i),0)+1);
            s2Map.put(s2.charAt(i),s2Map.getOrDefault(s2.charAt(i),0)+1);
        }
        return s1Map.equals(s2Map);
    }
}
