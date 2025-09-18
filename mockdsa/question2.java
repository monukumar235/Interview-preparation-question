package mockdsa;

public class question2 {
    public static void main(String[] args) {
        String s ="0000";
        System.out.println(findMaxOnes(s));
    }

    private static int findMaxOnes(String s) {
        char[] charArrays = s.toCharArray();
        int noOfOnes=0;
        int [] arr = new int[charArrays.length];
        int k=0;
        for(int i=0;i<charArrays.length;i++){
            if(charArrays[i]=='1'){
                noOfOnes++;
                arr[k++]=-1;
            }
            else if(charArrays[i]=='0'){
                arr[k++] =1;
            }
        }
        return noOfOnes + maxSubArraySum(arr);
    }

    private static int maxSubArraySum(int[] arr) {
        int max = arr[0];
        int curr =0;
        for(int i=0;i<arr.length;i++){
            curr+=arr[i];
            max = Math.max(max,curr);
            if(curr<0){
                curr=0;
            }
        }
        return max;
    }
}
