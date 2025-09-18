package multithreading;

import java.util.*;

public class questioni {
    public static void main(String[] args) {
        String s ="catsanddog";
        List<String > wordList = Arrays.asList("cat","cats","and","sand","dog");
        wordBreak(s,wordList).forEach((a)->{
            System.out.println(a);
        });
    }

    private static List<String> wordBreak(String s, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        Map<Integer,List<String>> t = new HashMap<>();
        return dfs(s,wordSet,0,t);
    }

    private static List<String> dfs(String s, HashSet<String> wordSet, int start, Map<Integer, List<String>> t) {
        if(t.containsKey(start)){
            return t.get(start);
        }
        List<String> res = new ArrayList<>();
        if(start==s.length()){
            res.add("");
            return res;
        }
        for(int j=start+1;j<=s.length();j++){
            String word = s.substring(start, j);
            if(wordSet.contains(word)){
                List<String> words = dfs(s, wordSet, j, t);
                for(String sub : words){
                    StringBuilder sb = new StringBuilder();
                    sb.append(word);
                    if(!sub.isEmpty()){
                        sb.append(" ").append(sub);
                    }
                    res.add(sb.toString());
                }
            }
        }
        t.put(start,res);
        return res;
    }
}
