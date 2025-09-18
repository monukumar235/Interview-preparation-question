import java.sql.SQLOutput;
import java.util.*;

public class DeadLockDetectionUsingDAG1 {
    public static void main(String[] args) {
        String [][] mat={{"P1","R1"},{"R1","P2"},{"P2","R2"},{"R2","P1"}};
        Map<String,List<String>> adjMat =new HashMap<>();

        for (int i=0;i<mat.length;i++){
            String a =mat[i][0];
            String b=mat[i][1];
            adjMat.putIfAbsent(a,new ArrayList<>());
            adjMat.get(a).add(b);
        }
        boolean detect = detect(adjMat);
        System.out.println(detect);
    }

    private static boolean detect(Map<String, List<String>> adjMat) {
        Set<String> visited= new HashSet<>();
        Set<String> rec = new HashSet<>();
        for(String node : adjMat.keySet()){
            if(!visited.contains(node)){
                if(dfs(node,visited,rec,adjMat)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(String node, Set<String> visited, Set<String> rec, Map<String, List<String>> adjMat) {
        visited.add(node);
        rec.add(node);
        List<String> neighbors = adjMat.get(node);
        for (String neighbor : neighbors){
            if(!visited.contains(neighbor)){
                if(dfs(neighbor,visited,rec,adjMat)){
                    return true;
                }
            }
            else if(rec.contains(neighbor)){
                return true;
            }
        }
        rec.remove(node);
        return false;
    }
}
