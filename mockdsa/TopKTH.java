package mockdsa;

import java.util.*;

public class TopKTH {
    public static void main(String[] args) {
        int [] nums={1,1,1,2,2,3};
        int k=2;

        int[] ints = topKFrequent(nums, k);
        for (int i :ints){
            System.out.println(i);
        }
    }

    private static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> freq= new HashMap<>();

        for(int i=0;i<nums.length;i++){
            freq.put(nums[i],freq.getOrDefault(nums[i],0)+1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->freq.get(a)-freq.get(b));

        for(int num: freq.keySet()){
            minHeap.add(num);
            if(minHeap.size()>k) minHeap.poll();
        }
        List<Integer> res = new ArrayList<>(minHeap);
        Collections.reverse(res);
        int [] r = new int[res.size()];
        for(int i=0;i<res.size();i++){
            r[i]=res.get(i);
        }
        return r;
    }
}
