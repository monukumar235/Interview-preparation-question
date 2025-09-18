package mockdsa;

import java.util.Arrays;

public class question5 {
    public static void main(String[] args) {
        int[] arr ={1,3,15,11,2};
        int[] arr1={23,127,235,19,8};
        System.out.println(findPair(arr,arr1));
    }

    private static int findPair(int[] arr, int[] arr1) {
        int min = Integer.MAX_VALUE;
        Arrays.sort(arr);
        Arrays.sort(arr1);
        int n = arr.length;
        int m = arr1.length;
        int i=0;
        int j=0;
        while (i<n && j<m){
            min = Math.min(min,Math.abs(arr[i]-arr1[j]));
            if (arr[i]<arr1[j]){
                i++;
            }
            else {
                j++;
            }
        }
        return min;
    }
}
