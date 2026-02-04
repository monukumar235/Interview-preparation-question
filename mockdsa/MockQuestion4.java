package mockdsa;

import java.util.PriorityQueue;

public class MockQuestion4 {
    public static void main(String[] args) {
        int [] arr = {3,2,1,5,6,4};
        int k=2;
        System.out.println(KthLargest(arr,k));
    }

    private static int KthLargest(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i=0;i<arr.length;i++)
        {
            minHeap.add(arr[i]);
            if(minHeap.size()>k){
                minHeap.remove();
            }
        }
        return minHeap.peek();
    }
}
