package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class firstQuestion {
    public static void main(String[] args) {
        String  s ="abccbaacz";
        System.out.println(getDuplicate(s));
    }

    private static char getDuplicate(String s) {
        Map<Character,Integer> freq =new HashMap<>();
        for (int i=0;i<s.length();i++){
            freq.put(s.charAt(i),freq.getOrDefault(s.charAt(i),0)+1);
            if(freq.get(s.charAt(i))==2){
                return s.charAt(i);
            }
        }
        return ' ';
    }
}
