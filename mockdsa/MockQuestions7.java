package mockdsa;

public class MockQuestions7 {
    public static void main(String[] args) {
        int []arr ={2,3,-2,4};
        System.out.println(maxProductSubArray(arr));
    }

    private static int maxProductSubArray(int[] arr) {
        int max=arr[0];
        int prefix=0;
        int suffix=0;
        int n = arr.length;
        for(int i=0;i<arr.length;i++)
        {
            prefix = arr[i]*(prefix==0 ? 1 : prefix);
            suffix = arr[n-1-i]*(suffix==0 ? 1 : suffix);
            max =Math.max(max,Math.max(prefix,suffix));
        }
        return max;
    }
}
