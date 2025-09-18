package multithreading;

import java.util.*;

public class DeadlockDetection {
    static class DeadLock{
        String thread;
        String resource;

        public DeadLock(String thread, String resource) {
            this.thread = thread;
            this.resource = resource;
        }
        public static boolean createGraph(List<DeadLock> deadLockList){
            Map<String,List<String>> graph = new HashMap<>();
            for(DeadLock d : deadLockList){
                graph.putIfAbsent(d.thread,new ArrayList<>());
                graph.get(d.thread).add(d.resource);
            }
            graph.computeIfAbsent("r1",k->new ArrayList<>()).add("t2");
            graph.computeIfAbsent("r2",k->new ArrayList<>()).add("t1");

            Set<String> visited = new HashSet<>();
            Set<String> recStack = new HashSet<>();

            for (String node : graph.keySet()){
                if(checkCycle(graph,visited,recStack,node)){
                    return true;
                }
            }
            return false;
        }

        private static boolean checkCycle(Map<String, List<String>> graph, Set<String> visited,Set<String>recStack,String node) {
            if(recStack.contains(node)) return true;
            if(visited.contains(node)) return false;

            visited.add(node);
            recStack.add(node);

            List<String> neighbors = graph.get(node);
            for (String neighbor : neighbors){
                if(checkCycle(graph,visited,recStack,neighbor)){
                    return true;
                }
            }
            recStack.remove(node);
            return false;
        }
    }
    public static void main(String[] args) {
        List<DeadLock> deadLockList = new ArrayList<>();
        deadLockList.add(new DeadLock("t1","r1"));
        deadLockList.add(new DeadLock("t2","r2"));

        boolean graph = DeadLock.createGraph(deadLockList);
        System.out.println(graph);
    }
}
