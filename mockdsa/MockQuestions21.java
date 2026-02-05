package mockdsa;

public class MockQuestions21 {
     static  Boolean[][]t;
    public static void main(String[] args) {
//        Input: nums = [1,2,3,4]
        int [] arr = {1,2,3,4};
        System.out.println(canPartition(arr));
    }

    private static boolean canPartition(int[] arr) {
        int n =arr.length;
        int sum=0;
        for(int a : arr){
            sum+=a;
        }
        if(sum%2==1){
            return false;
        }
        t = new Boolean[n+1][sum/2+1];
        return kanpSack(arr,n,sum/2);
    }

    private static boolean kanpSack(int[] arr, int n, int sum) {
        if(n==0){
            return sum==0 ? true : false;
        }
        if(t[n][sum]!=null) return t[n][sum];
        if(arr[n-1]<=sum)
        {
            return t[n][sum]= kanpSack(arr,n-1,sum-arr[n-1]) || kanpSack(arr,n-1,sum);
        }
        else{
            return t[n][sum]= kanpSack(arr,n-1,sum);
        }
    }

}
