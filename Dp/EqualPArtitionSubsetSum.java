package Dp;


public class EqualPArtitionSubsetSum {
    public static Boolean[][]t;
    public static void main(String[] args) {
        int arr[]={1,2,3,5};
        System.out.println(canpartition(arr));
    }

    private static boolean canpartition(int[] arr) {
        int sum=0;
        for(int n : arr){
            sum+=n;
        }
        if(sum%2==1){
            return false;
        }
        t=new Boolean[arr.length+1][sum+1];
        return subsetsum(arr,sum/2,arr.length);
    }

    private static boolean subsetsum(int[] arr, int sum,int n) {
        if(sum==0) return true;
        if(n==0) return false;
        if(t[n][sum]!=null) return t[n][sum];

        if(arr[n-1]<=sum){
            return t[n][sum]=subsetsum(arr,sum-arr[n-1],n-1) || subsetsum(arr,sum,n-1);
        } else if (arr[n-1]>sum) {

            return t[n][sum]=subsetsum(arr,sum,n-1);
        }
        return false;
    }
}
