package mockdsa;

public class subssetSum {
    static Boolean[][]t;
    public static void main(String[] args) {
        int []arr={3,34,4,12,5,2};
        int sum=9;
        System.out.println(findSubset(arr,sum));
    }

    private static boolean findSubset(int[] arr, int sum) {
        int n = arr.length;
        t= new Boolean[n+1][sum+1];
        return dfss(arr,n,sum);
    }

    private static boolean dfss(int[] arr, int n, int sum) {
        if(n==0){
            return sum==0?true:false;
        }
        if(t[n][sum]!=null) return t[n][sum];
        if(arr[n-1]<=sum){
            return t[n][sum]= dfss(arr,n-1,sum-arr[n-1]) || dfss(arr,n-1,sum);
        }
        else {
            return t[n][sum]= dfss(arr,n-1,sum);
        }
    }
}
