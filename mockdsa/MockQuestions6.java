package mockdsa;

public class MockQuestions6 {
    public static void main(String[] args) {
        int [] arr ={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubarraySum(arr));
    }

    private static int maxSubarraySum(int[] arr) {
        int max =arr[0];
        int curr = 0;
        for(int i=0;i<arr.length;i++)
        {
            curr+=arr[i];
            max = Math.max(max,curr);
            if(curr<0){
                curr=0;
            }
        }
        return max;
    }
}
