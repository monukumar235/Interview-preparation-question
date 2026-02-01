package mockdsa;

public class SearchInMAx {
    public static void main(String[] args) {
        int[][]mat ={{1,2,4,8},{10,11,12,13},{14,20,30,40}};
        int target =10;
        System.out.println(searcgMatrix(mat,target));
    }

    private static boolean searcgMatrix(int[][] mat, int target) {
        int row = mat.length;
        int col = mat[0].length;
        for (int[]arr : mat){
            if (arr[0]<=target && arr[col-1]>=target){
                if (bs(arr,target)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean bs(int[] arr, int target) {
        int low=0;
        int high = arr.length;
        while(low<high)
        {
            int mid = low+(high-low)/2;
            if(arr[mid]==target){
                return true;
            }
            else if(arr[mid]<target){
                low=mid+1;
            }
            else{
                high = mid;
            }
        }
        return false;
    }
}
