package mockdsa;

import java.util.HashMap;
import java.util.Map;

public class MockQuestions8 {
    public static void main(String[] args) {

        int []arr ={2,7,11,15};
        int target = 9;
        int[] res = getIndex(arr, target);
        for (int i=0;i<res.length;i++)
        {
            System.out.print(res[i]+" ");
        }
    }

    private static int[] getIndex(int[] arr, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int n = arr.length;
        int[]res = new int[2];
        for(int i=0;i<n;i++){
            if(map.containsKey(target-arr[i])){
                res[0]=map.get(target-arr[i]);
                res[1]=i;
                return res;
            }
            map.put(arr[i],i);
        }
        return new int[]{0,1};
    }
}
