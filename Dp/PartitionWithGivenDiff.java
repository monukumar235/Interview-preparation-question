package Dp;

public class PartitionWithGivenDiff {
    public static Integer[][]t;
    public static void main(String[] args) {
        int arr[]={5, 2, 6, 4};
        int diff=3;
        System.out.println(countPartitions(arr,diff));
    }

    private static int countPartitions(int[] arr, int diff) {
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        int s1=(diff+sum)/2;
        t= new Integer[arr.length+1][s1+1];
        return countSubsetSum(arr,s1,arr.length);
    }

    private static int countSubsetSum(int[] arr, int sum,int n) {
        if(n==0) {
            return sum == 0 ? 1 : 0;
        }
        if(t[n][sum]!=null) return t[n][sum];

        if(arr[n-1]<=sum){
            return t[n][sum]= countSubsetSum(arr,sum-arr[n-1],n-1) + countSubsetSum(arr,sum,n-1);
        }
        else if(arr[n-1]>sum){
            return t[n][sum] = countSubsetSum(arr,sum,n-1);
        }
        return 0;
    }
}
