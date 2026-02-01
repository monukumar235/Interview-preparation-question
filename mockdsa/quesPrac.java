package mockdsa;

public class quesPrac {
//    Input: arr[] = {2, 3, 7, 8, 10}, sum = 11
//    Output: True
//    Explanation: There is a subset (3, 8) with sum 11.
//
//
//    Input: arr[] = {3, 34, 4, 12, 5, 2}, sum = 9
//    Output: True
//    Explanation: There are 2 subsets (3, 4, 2) & (4, 5) that add up to 9.
    public static void main(String[] args) {
        int arr[] ={2,3,7,8,10};
        int sum =11;
        System.out.println(subSetSum(arr,sum,arr.length));
    }

    private static boolean subSetSum(int[] arr, int sum, int n) {
        if (n==0){
            return false;
        }
        if(sum ==0){
            return true;
        }
        if (arr[n-1]<=sum){
            return subSetSum(arr,sum-arr[n-1],n-1) || subSetSum(arr,sum,n-1);
        }
        else{
            return subSetSum(arr,sum,n-1);
        }
    }
}

