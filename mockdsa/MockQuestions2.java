package mockdsa;

import java.util.PriorityQueue;

public class MockQuestions2 {
    public static void main(String[] args) {
        int[] arr={3,2,1,5,6,4};
        int k=2;
        System.out.println(kthLargest(arr,k));
    }

    private static int kthLargest(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int i=0;i<arr.length;i++)
        {
            heap.offer(arr[i]);
            if(heap.size()>k){
                heap.poll();
            }
        }
        return heap.peek();
    }
}
