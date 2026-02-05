package mockdsa;

import java.util.ArrayList;
import java.util.List;

public class MockQuestions19 {
    public static void main(String[] args) {
        int[][] edges={{0,1},{1,2},{2,3},{1,3},{1,4}};
//        [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
        int n =5;
        System.out.println(validTree(edges,n));
    }

    private static boolean validTree(int[][] edges, int n) {
        List<List<Integer>> adjMat = new ArrayList<>();

        for (int i=0;i<n;i++)
        {
            adjMat.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++)
        {
            int a= edges[i][0];
            int b =edges[i][1];
            adjMat.get(a).add(b);
            adjMat.get(b).add(a);
        }
        int [] visited = new int[n];
        if(isCycle(0,visited,adjMat,-1)){
            return false;
        }
        for(int i=0;i<visited.length;i++)
        {
            if(visited[i]==0)
            {
                return false;
            }
        }
        return true;
    }

    private static boolean isCycle(int node, int[] visited, List<List<Integer>> adjMat, int parent) {
        visited[node]=1;
        for(int n : adjMat.get(node)){
            if(visited[n]==0)
            {
                if (isCycle(n,visited,adjMat,node)){
                    return true;
                }
            }else if(n!=parent)
            {
                return true;
            }
        }
        return false;
    }
}
