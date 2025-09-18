package mockdsa;

public class MissingNumber {
    public static void main(String[] args) {
        int []arr={1,2,4,5,6};
        System.out.println(findMissingNumber(arr));
    }

    private static int findMissingNumber(int[] arr) {
        int n=arr.length;

        int NNSum= (n+1)*(n+2)/2;
        int sum=0;
        for(int num :arr){
            sum+=num;
        }
        return NNSum-sum;
    }
}
