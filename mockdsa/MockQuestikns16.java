package mockdsa;

import java.util.*;

public class MockQuestikns16 {
    public static void main(String[] args) {
        int[] ratings = {4, 5, 1, 2, 5, 3, 4};
        int k=3;
        System.out.println(topKElement(ratings,k));
    }

    private static List<Integer> topKElement(int[] ratings, int k) {
        Map<Integer,Integer> freq = new HashMap<>();

        for (int n : ratings){
            freq.put(n,freq.getOrDefault(n,0)+1);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b)->freq.get(a)-freq.get(b));
        for(int n : freq.keySet())
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
