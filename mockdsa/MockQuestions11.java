package mockdsa;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MockQuestions11 {
    public static void main(String[] args) {
        int[] prices = {300, 150, 450, 200, 500, 100};
        int k=3;
        System.out.println(top3Prices(prices,k));
    }

    private static List<Integer> top3Prices(int[] prices, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int n:prices)
        {
            heap.add(n);
            if(heap.size()>k)
            {
                heap.poll();
            }
        }

        return new ArrayList<>(heap);
    }
}
