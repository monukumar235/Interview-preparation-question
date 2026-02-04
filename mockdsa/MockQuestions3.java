package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class MockQuestions3 {
    public static void main(String[] args) {
        int [] arr = {1,2,3};
        int k = 3;
        System.out.println(SubArraySum(arr,k));
    }

    private static int SubArraySum(int[] arr, int k) {
        int count=0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int prefixSum=0;

        for (int num:arr)
        {
            prefixSum+=num;
            if(map.containsKey(prefixSum-k)){
                count+=map.get(prefixSum-k);
            }
            map.put(prefixSum,map.getOrDefault(prefixSum,0)+1);
        }
        return count;
    }
}
