package mockdsa;

public class question4 {
    public static void main(String[] args) {
        int[] arr={1, 2, 2, 3, 4};
        int target =5;
        System.out.println(getCount(arr,target));
    }

    public static int getCount(int[]arr,int target){
        int low=0;
        int high = arr.length-1;
        int count=0;
        while (low<high){
            int sum = arr[low]+arr[high];
            if(sum==target){
                count++;
                low++;
                high--;
            }
            else if(sum<target){
                low++;
            }
            else{
                high--;
            }
        }
        return count;
    }
}
