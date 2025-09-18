import java.util.*;

public class DeadLockDetectionUsingDAG {
    static class Graph{
        Map<String, List<String>> adjMat=new HashMap<>();
        void addEdge(String u, String v){
            adjMat.putIfAbsent(u,new ArrayList<>());
            adjMat.get(u).add(v);
        }
    }
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("P1","R1");
        graph.addEdge("R1","P2");
        graph.addEdge("R2","P1");
        graph.addEdge("P2","R2");


        boolean deadLock = detectDeadLock(graph);
        System.out.println(deadLock);
    }

    private static boolean detectDeadLock(Graph graph) {
        Set<String> visited = new HashSet<>();
        Set<String> recStack = new HashSet<>();

        for(String node : graph.adjMat.keySet()){
            if(!visited.contains(node)){
                if(dfs(node,visited,recStack,graph)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(String node, Set<String> visited, Set<String> recStack, Graph graph) {
        visited.add(node);
        recStack.add(node);
        List<String> neighbors = graph.adjMat.getOrDefault(node, new ArrayList<>());
        for(String neighbor :neighbors){
            if(!visited.contains(neighbor)){
                boolean dfs = dfs(neighbor, visited, recStack, graph);
                if(dfs){
                    return  true;
                }
            }else if(recStack.contains(neighbor)){
                return true;
            }
        }
        recStack.remove(node);
        return false;
    }
}
