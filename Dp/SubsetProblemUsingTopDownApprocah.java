package Dp;

public class SubsetProblemUsingTopDownApprocah {
    public static Boolean [][]t;
    public static void main(String[] args) {
        int arr[]={3, 34, 4, 12, 5, 2};
        int sum=30;
        t=new Boolean[arr.length+1][sum+1];
        System.out.println(subSet(arr,sum,arr.length));
    }

    private static boolean subSet(int[] arr, int sum, int n) {
        if(sum==0){
            return  true;
        }
        if(n==0){
            return false;
        }
        if(t[n][sum]!=null) return t[n][sum];

        if(arr[n-1]<=sum){
            return t[n][sum]= subSet(arr,sum-arr[n-1],n-1) || subSet(arr,sum,n-1);
        }
        else if(arr[n-1]>sum){
            return t[n][sum]=subSet(arr,sum,n-1);
        }
        return false;
        
    }
}
