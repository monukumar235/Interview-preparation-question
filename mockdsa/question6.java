package mockdsa;

public class question6 {
    public static void main(String[] args) {
        int arr[]={2,1,5,1,3,2};
        int k=3;
        System.out.println(findMax(arr,k));
    }

    private static int findMax(int[] arr, int k) {
        int maxSum = 0;
        int currSum=0;
        int left=0;
        for(int right=0;right<arr.length;right++){
            currSum+=arr[right];
            while (right-left+1==k){
                maxSum = Math.max(maxSum,currSum);
                currSum-=arr[left];
                left++;
            }
        }
        return maxSum;
    }
}
