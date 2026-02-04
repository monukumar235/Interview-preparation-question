package mockdsa;

public class findDup {
    public static void main(String[] args) {
        int[] arr={1,2,3,2,2};
        System.out.println(findDuplicate(arr));
    }

    private static int findDuplicate(int[] arr) {
        int slow = arr[0];
        int fast = arr[0];
        while (true)
        {
            slow=arr[slow];
            fast=arr[arr[fast]];
            if(slow==fast) {
                break;
            }
        }
        slow=arr[0];
        while (slow!=fast){
            slow=arr[slow];
            fast=arr[fast];
        }
        return slow;
    }
}
