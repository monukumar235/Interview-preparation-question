package mockdsa;

public class MockQuestion22 {
    static Boolean[][]t;
    public static void main(String[] args) {
//        Input: arr[] = [3, 34, 4, 12, 5, 2], sum = 9
//        Output: True
        int arr[] ={3,34,4,12,5,2};
        int sum=9;
        System.out.println(isPossible(arr,sum));
    }

    private static boolean isPossible(int[] arr, int sum) {
        int n =arr.length;
        t = new Boolean[n+1][sum+1];
        return subSetSum(arr,sum,n);
    }

    private static boolean subSetSum(int[] arr, int sum, int n) {
        if(n==0){
            return sum==0? true:false;
        }
        if(t[n][sum]!=null) return t[n][sum];
        if(arr[n-1]<=sum){
            return t[n][sum]= subSetSum(arr,sum-arr[n-1],n-1) || subSetSum(arr,sum,n-1);
        }
        else{
            return t[n][sum]= subSetSum(arr,sum,n-1);
        }
    }
}
