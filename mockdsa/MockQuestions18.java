package mockdsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MockQuestions18 {
    public static void main(String[] args) {
        int [][]prerequist = {{0,1}};
        int numCourse=2;
        System.out.println(canFinish(prerequist,numCourse));
    }

    private static boolean canFinish(int[][] prerequist, int numCourse) {
        List<List<Integer>> adjMat = new ArrayList<>();

        for (int i=0;i<numCourse;i++)
        {
            adjMat.add(new ArrayList<>());
        }
        for (int i=0;i<prerequist.length;i++)
        {
            int a = prerequist[i][0];
            int b = prerequist[i][1];
            adjMat.get(b).add(a);
        }
        return topo(adjMat,numCourse);
    }

    private static boolean topo(List<List<Integer>> adjMat, int numCourse) {
        int indeg[] = new int[numCourse];

        for (int i=0;i<adjMat.size();i++)
        {
            for (int x :adjMat.get(i))
            {
                indeg[x]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<indeg.length;i++)
        {
            if(indeg[i]==0)
            {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            Integer curr = queue.poll();
            for (int n : adjMat.get(curr)){
                indeg[n]--;
                if(indeg[n]==0)
                {
                    queue.add(indeg[n]);
                }
            }
        }
        boolean flag = true;
        for(int i=0;i<indeg.length;i++)
        {
            if(indeg[i]!=0)
            {
                flag=false;
            }
        }
        return flag;
    }
}
