package mockdsa;
//Given an integer array nums and an integer k, return the k most frequent elements in any order. If multiple elements have the same frequency, any order among them is acceptable.
//
//
//        nums = [1,1,1,2,2,3], k = 2 → [1,2]
//        nums = [1], k = 1 → [1]

import java.util.*;

public class prac2 {
    public static void main(String[] args) {
        int []arr ={1};
        int k=1;
        int[] res = mostFreqElement(arr,k);
        for(int i=0;i<res.length;i++)
        {
            System.out.print(res[i]);
        }
    }

    private static int[] mostFreqElement(int[] arr, int k) {
        Map<Integer,Integer> freq = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            freq.put(arr[i],freq.getOrDefault(arr[i],0)+1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->freq.get(a)-freq.get(b));

        for(int a : freq.keySet()){
            minHeap.add(a);
            if(minHeap.size()>k){
                minHeap.poll();
            }
        }
        List<Integer> list = new ArrayList<>(minHeap);
        int [] res = new int[list.size()];
        for(int i=0;i<list.size();i++)
        {
            res[i]=list.get(i);
        }
        return res;
    }
}
