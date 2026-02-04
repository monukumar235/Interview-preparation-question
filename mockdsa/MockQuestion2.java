package mockdsa;

public class MockQuestion2 {
    public static void main(String[] args) {
        int[]arr={0,1,0,3,12};
        moveZeroAtEnd(arr);
        print(arr);
    }

    private static void print(int[] arr) {
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    private static void moveZeroAtEnd(int[] arr) {
        int pos=0;
        for(int n :arr){
            if(n!=0){
                arr[pos++]=n;
            }
        }
        for(int i=pos;i<arr.length;i++){
            arr[i]=0;
        }
    }
}
