package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class mockQuestion1 {
    public static void main(String[] args) {
        String str = "leedcode";
        System.out.println(findIndex(str));
    }

    private static int findIndex(String str) {
        Map<Character,Integer> freq = new HashMap<>();
        for (int i=0;i<str.length();i++){
            freq.put(str.charAt(i),freq.getOrDefault(str.charAt(i),0)+1);
        }
        for(int i=0;i<str.length();i++){
            if(freq.get(str.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }
}
