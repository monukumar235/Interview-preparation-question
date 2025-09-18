package mockdsa;

import java.util.HashMap;

public class question7 {
    public static void main(String[] args) {
        int arr[]={1,2,1,3,4};
        int k=3;
        System.out.println(getCount(arr,k));
    }

    private static int getCount(int[] arr, int k) {
        int count=0;
        int start=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int end=0;end<arr.length;end++){
            map.put(arr[end],map.getOrDefault(arr[end],0)+1);
            while (end-start+1==k)
            {
                if(map.size()==k){
                    count++;
                }
                map.put(arr[start],map.getOrDefault(arr[start],0)-1);
                if(map.get(arr[start])==0){
                    map.remove(arr[start]);
                }
                start++;
            }
        }
        return count;
    }
}
