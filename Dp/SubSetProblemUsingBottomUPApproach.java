package Dp;

public class SubSetProblemUsingBottomUPApproach {
    public static void main(String[] args) {
        int []arr={3, 34, 4, 12, 5, 2};
        int sum=30;
        System.out.println(subsetSum(arr,sum));
    }

    private static boolean subsetSum(int[] arr, int sum) {
        int n=arr.length;
        boolean [][]t = new boolean[n+1][sum+1];

        for(int i=0;i<n+1;i++) {
            for (int j = 0; j < sum + 1; j++) {
                if (i == 0) {
                    t[i][j] = false;
                }
                if (j == 0) {
                    t[i][j] = true;
                }
            }
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<sum+1;j++){
                if(arr[i-1]<=j){
                    t[i][j]= t[i-1][j-arr[i-1]] || t[i-1][j];
                }
                else {
                    t[i][j]=t[i-1][j];
                }
            }
        }
        return t[n][sum];
    }
}
