package mockdsa;

import java.util.Arrays;

public class question1 {
    public static void main(String[] args) {
        int [] arr={1,5,3,3,1};
        System.out.println(noOfSwapToSortOpt(arr));
    }

    private static boolean noOfSwapToSortOpt(int[] arr) {
        int n = arr.length;
        int i=0;
        int j=n-1;
        while (i<n-1 && arr[i]<=arr[i+1]){
            i++;
        }
        if(i==n-1) return true;
        while (j>0 && arr[j]>=arr[j-1]){
            j++;
        }
        swap(arr,i,j);

        boolean isSorted = isSorted(arr);

        swap(arr,i,j);

        return isSorted;
    }

    private static boolean isSorted(int[] arr) {
        for(int i=1;i<arr.length;i++){
            if(arr[i]<arr[i-1]){
                return false;
            }
        }
        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp =arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    private static boolean noOfSwapToSort(int[] arr) {
        int [] temp = arr.clone();
        Arrays.sort(temp);
        int count=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=temp[i]){
                count++;
            }
        }
        return count==2 || count==0;
    }
}
