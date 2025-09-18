package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class NonReapeatingchar {
    public static void main(String[] args) {
        String s="monu";
        System.out.println(firstNonRepeatingChar(s));
    }

    private static char firstNonRepeatingChar(String s) {
        Map<Character,Integer> freqMap = new HashMap<>();
        for(int i=0;i<s.length();i++){
            freqMap.put(s.charAt(i),freqMap.getOrDefault(s.charAt(i),0)+1);
        }
        for(int i=0;i<s.length();i++){
            if (freqMap.get(s.charAt(i))==1){
                return s.charAt(i);
            }
        }
        return '0';
    }
}
