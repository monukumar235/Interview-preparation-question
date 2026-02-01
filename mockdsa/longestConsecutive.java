package mockdsa;

import java.util.HashSet;
import java.util.Set;

public class longestConsecutive {
    public static void main(String[] args) {
        int[] arr={2,20,4,10,3,4,5};
        System.out.println(findLongestConsecutive(arr));
    }

    private static int findLongestConsecutive(int[] arr) {
        int longest =0;
        Set<Integer> set = new HashSet<>();
        for(int n : arr){
            set.add(n);
        }
        for (int a : set){
            if(!set.contains(a-1))
            {
                int curr=a;
                int count =1;
                while (set.contains(curr+1)){
                    curr++;
                    count++;
                }
                longest = Math.max(longest,count);
            }
        }
        return longest;
    }
}
