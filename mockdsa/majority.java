package mockdsa;

public class majority {
    public static void main(String[] args) {
        int[]arr={2,2,1,1,1,2,2};
        System.out.println(majorityEle(arr));
    }

    private static int majorityEle(int[] arr) {
        int count=1;
        int can=arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]==can){
                count++;
            }
            else {
                count--;
                if(count<0){
                    count=1;
                    can=arr[i];
                }
            }
        }
        count=0;
        for (int n :arr){
            if(n==can){
                count++;
            }
        }
        return count>arr.length/2 ? can :-1;
    }
}
