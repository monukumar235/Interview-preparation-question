package mockdsa;

public class MockQuestions9 {
    public static void main(String[] args) {
        int [] arr ={1,8,6,2,5,4,8,3,7};
        System.out.println(maxWater(arr));
    }

    private static int maxWater(int[] arr) {
        int max=0;
        int low=0;
        int high = arr.length-1;
        while (low<high)
        {
            if(arr[low]<arr[high])
            {
                max=Math.max(max,Math.max(arr[low],arr[high])*(high-low));
                low++;
            }
            else{
                max=Math.max(max,Math.max(arr[low],arr[high])*(high-low));
                high--;
            }
        }
        return max;
    }
}
