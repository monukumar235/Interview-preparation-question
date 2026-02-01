package mockdsa;

public class searchMinInSortedRotated {
    public static void main(String[] args) {
        int [] arr={3,4,5,6,1,2};
        System.out.println(findMin(arr));
    }

    private static int findMin(int[] arr) {
        int low=0;
        int high=arr.length-1;
        while (low<high)
        {
            int mid = low+(high-low)/2;
            if(arr[mid]>arr[high])
            {
                low=mid+1;
            }
            else {
                high=mid;
            }
        }
        return arr[low];
    }
}
