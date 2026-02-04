package mockdsa;

public class MockQuestion5 {
    public static void main(String[] args) {
        int [] arr ={1,2,3,4};
        int[] res = findProdExecptSelf(arr);
        for (int i=0;i<res.length;i++)
        {
            System.out.print(res[i]+" ");
        }
    }

    private static int[] findProdExecptSelf(int[] arr) {
        int prefix=1;
        int suffix =1;
        int n = arr.length;
        int [] res = new int[n];

        for(int i=0;i<n;i++)
        {
            res[i]=prefix;
            prefix*=arr[i];
        }
        for(int i=n-1;i>=0;i--){
            res[i]*=suffix;
            suffix*=arr[i];
        }
        return res;
    }
}
