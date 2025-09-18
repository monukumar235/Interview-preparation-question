package mockdsa;

import java.util.HashSet;

public class LongestConsequtibeSubsequence {
    static class unionfind{
        int []parent;
        int []size;
        int []rank;
        unionfind(int n){
            parent=new int[n];
            size=new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
                size[i]=1;
            }
        }

        public int find(int x){
            if(parent[x]!=x){
                parent[x]=find(parent[x]);
            }
            return parent[x];
        }

        void union(int x,int y){
            int rootX=find(x);
            int rootY=find(y);
            if (rootX!=rootY){
                parent[rootY]=rootX;
                size[rootX]+=size[rootY];
            }
        }
        int getSize(){
            int max =0;
            for(int s:size){
                max=Math.max(max,s);
            }
            return max;
        }
    }
    public static void main(String[] args) {
        int[]arr={100, 4, 200, 1, 3, 2};
        System.out.println(longest(arr));
    }

    private static int longest(int[] arr) {
        HashSet<Integer>set = new HashSet<>();
        unionfind uf =new unionfind(arr.length);
        for(int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        for(int i=0;i<arr.length;i++){
            if(set.contains(arr[i]+1)){
                uf.union(arr[i],arr[i]+1);
            }
        }
        return uf.getSize();
    }
}
