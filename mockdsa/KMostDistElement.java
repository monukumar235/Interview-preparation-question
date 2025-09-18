package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class KMostDistElement {
    public static void main(String[] args) {
        String str ="eceba";
        int k=2;
        System.out.println(KthMost(str,k));
    }

    private static int KthMost(String str, int k) {
        int start=0;
        int maxLen=0;
        Map<Character,Integer> freq = new HashMap<>();
        for(int end=0;end<str.length();end++){
            freq.put(str.charAt(end),freq.getOrDefault(str.charAt(end),0)+1);
            if(freq.size()>k){
                char leftChar = str.charAt(start);
                freq.put(leftChar,freq.getOrDefault(leftChar,0)-1);
                if(freq.get(leftChar)==0){
                    freq.remove(leftChar);
                }
                start++;
            }
            maxLen=Math.max(maxLen,end-start+1);
        }
        return maxLen;
    }
}
