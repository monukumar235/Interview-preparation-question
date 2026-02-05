package mockdsa;

import java.util.ArrayList;
import java.util.List;

public class MockQuestions20 {
    public static void main(String[] args) {
        int[][] edges ={{0,1},{1,2},{3,4}};
        int n =5;
//        n = 5, edges = [[0,1],[1,2],[3,4]]
        System.out.println(noOfConnectedComponent(edges,n));
    }

    private static int noOfConnectedComponent(int[][] edges, int n) {
        List<List<Integer>> adjMat = new ArrayList<>();

        for(int i=0;i<n;i++)
        {
            adjMat.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++)
        {
            int a = edges[i][0];
            int b = edges[i][1];
            adjMat.get(a).add(b);
            adjMat.get(b).add(a);
        }
        int [] visited = new int[n];
        int res =0;
        for(int i=0;i<n;i++)
        {
            if(visited[i]==0){
                dfs(i,visited,adjMat);
                res++;
            }
        }
        return res;
    }

    private static void dfs(int node, int[] visited, List<List<Integer>> adjMat) {
        visited[node]=1;
        for(int n : adjMat.get(node))
        {
            if(visited[n]==0)
            {
                dfs(n,visited,adjMat);
            }
        }
    }
}
