package mockdsa;

import java.util.ArrayList;
import java.util.List;

public class leader {
    public static void main(String[] args) {
        int []arr={16, 17, 4, 3, 5, 2};
//        System.out.println(leaderEl(arr));
        int[] ints = leaderEl(arr);
        for(int n : ints){
            System.out.print(n + " ");
        }
    }

    private static int[] leaderEl(int[] arr) {
        List<Integer> result=new ArrayList<>();
        result.add(arr[arr.length-1]);
        int max=arr[arr.length-1];
        for (int i=arr.length-2;i>=0;i--){
            if(arr[i]>max){
                max=arr[i];
                result.add(arr[i]);
            }
        }
        int []res=new int[result.size()];
        for(int i=0;i<result.size();i++){
            res[i]=result.get(i);
        }
        return res;
    }
}
