package mockdsa;

public class question3 {
    public static void main(String[] args) {
        int arr[]={1, 2, 3, 4, 5};
        System.out.println(findAPairCountOpt(arr));
    }

    private static int findAPairCountOpt(int[] arr) {
        int even =0;
        int odd=0;
        for(int n : arr){
            if(n%2==0){
                even++;
            }
            else{
                odd++;
            }
        }
        int evenPair = even*(even-1)/2;
        int oddPair = odd*(odd-1)/2;
        return evenPair + oddPair;
    }

    private static int findAPairCount(int[] arr) {
        int count=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                int sum =arr[i]+arr[j];
                if(sum%2==0){
                    count++;
                }
            }
        }
        return count;
    }
}
