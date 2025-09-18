package multithreading;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Question2 {
    public static void main(String[] args) {
        int [] arr ={7,3,7,3,1,3,4,1};

        System.out.println(find(arr));
    }

    private static int find(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int n = arr.length;
        for(int i=0;i<n;i++){
            set.add(arr[i]);
        }
//        {1,3,4,7}
        int k = set.size();
//        k=4

        int start=0;

        Map<Integer,Integer> frq = new HashMap<>();
//        {}

        int min=Integer.MAX_VALUE;

        for(int end=0;end<n;end++){
            frq.put(arr[end],frq.getOrDefault(arr[end],0)+1);
//            [7:1,]
            while(frq.size()==k){
                min = Math.min(min,end-start+1);
                frq.put(arr[start],frq.getOrDefault(arr[start],0)-1);
                if(frq.get(arr[start])==0){
                    frq.remove(arr[start]);
                }
                start++;
            }
        }
        return min==Integer.MAX_VALUE ? 0 :min;
    }

}
