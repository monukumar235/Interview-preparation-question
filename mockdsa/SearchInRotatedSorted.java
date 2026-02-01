package mockdsa;

public class SearchInRotatedSorted {
    public static void main(String[] args) {
        int[]arr={3,5,6,0,1,2};
        int target=0;
        System.out.println(search(arr,target));
    }

    private static int search(int[] arr, int target) {
        int low=0;
        int high=arr.length-1;
        while (low<high){
            int mid= low+(high-low)/2;
            if(arr[mid]>arr[high]){
                low=mid+1;
            }
            else{
                high=mid;
            }
        }
        int pivot =low;
        int res = binarySearch(arr,0,pivot-1,target);
        if(res!=-1){
            return res;
        }
        return binarySearch(arr,pivot,arr.length-1,target);
    }

    private static int binarySearch(int[] arr, int low, int high,int target) {
        while (low<high){
            int mid = low+(high-low)/2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]<target)
            {
                low=mid+1;
            }
            else{
                high=mid;
            }
        }
        return -1;
    }
}
