package mockdsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestPair {
    public static void main(String[] args) {
        int []arr = {6,2,4,10};
        int n = arr.length;

        Arrays.sort(arr);
        List<int[]> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            int min=Integer.MAX_VALUE;
            for (int j=i+1;j<n;j++){
                int diff = Math.abs(arr[i]-arr[j]);
                if(diff<min){
                    min=diff;
                    list.add(new int[]{arr[i],arr[j]});
                }
            }
        }
        for(int[] l : list)
        {
            System.out.println(l[0] + " "+ l[1]);
        }
    }
}
