package mockdsa;

public class SecondLargest {
    public static void main(String[] args) {
        int []arr={12,35,1,10,34,1};
        System.out.println(secondLargest(arr));
    }

    private static int secondLargest(int[] arr) {
        int largest=Integer.MIN_VALUE;
        int second=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>largest){
                second=largest;
                largest=arr[i];
            }
            else if(arr[i]>second && arr[i]!=largest){
                second=arr[i];
            }
        }
        return second==Integer.MIN_VALUE ? -1 : second  ;
    }
}
