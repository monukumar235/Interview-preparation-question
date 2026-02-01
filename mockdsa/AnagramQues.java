package mockdsa;

import java.util.*;

public class AnagramQues {
    public static void main(String[] args) {
        List<String> words= new ArrayList<>(Arrays.asList("lump","eat","me","tea","em","plum"));
        List<List<String>> groupAnagram = findGroupAnagram(words);
        System.out.println(groupAnagram);
    }

    private static List<List<String>> findGroupAnagram(List<String> words) {
        Map<String,List<String>> map = new HashMap<>();
        for(String word : words){
            int []count = new int[26];
            for(int i=0;i<word.length();i++){
                count[word.charAt(i)-'a']++;
            }
            String key = Arrays.toString(count);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(word);
        }
        return new ArrayList<>(map.values());
    }
}
