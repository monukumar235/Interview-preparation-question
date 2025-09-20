package singleton;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class prc {
    public static void main(String[] args) {
//        Q1 First Non-Repeated Character

        String s = "swiss";

        Character key = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .get()
                .getKey();
//        System.out.println(key);

//        Q2 Character Frequency Map

        String s1 = "banana";

        LinkedHashMap<Character, Long> collect = s1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));

//        Q3 Longest Word in a Sentence

        String s2 = "Java streams make coding fun";

        String[] words = s2.split(" ");

        int maxLength = Arrays.stream(words)
                .mapToInt(w -> w.length()).max()
                .getAsInt();

        List<String> collect1 = Arrays.stream(words)
                .filter(w -> w.length() == maxLength)
                .collect(Collectors.toList());
//        System.out.println(collect1);

//        Q4 Anagram Check (Two Strings)

        String s3 = "listen";
        String s4 = "silent";

        s3 = s3.toLowerCase().replaceAll("\\s","");
        s4 = s4.toLowerCase().replaceAll("\\s","");

        String collect2 = s3.chars()
                .sorted()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
        String collect3 = s4.chars()
                .sorted()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
//        if(collect2.equals(collect3)){
//            System.out.println("Analog");
//        }else{
//            System.out.println("not analog");
//        }

//        Q5 Filter Words by Length

        List<String> list = Arrays.asList("java","stream","fun");
        int length=4;

        List<String> collect4 = list.stream()
                .filter(w -> w.length() == length)
                .collect(Collectors.toList());
//        System.out.println(collect4);

//        Q6 String Concatenation Pairs

        List<String> wordList = Arrays.asList("a","b","c");

        List<String> collect5 = wordList.stream()
                .flatMap(w1 -> wordList.stream()
                        .map(w2 -> w1 + w2))
                .collect(Collectors.toList());
//        System.out.println(collect5);

//        Q7 Pair Sum Unique Pairs
        List<Integer> list1 = Arrays.asList(1,2,3,4,3);
        int target =5;
        Set<Integer> seen = new HashSet<>();

        Set<List<Integer>> collect6 = list1.stream()
                .flatMap(num -> {
                    int compliment = target - num;
                    List<Integer> pair = Arrays.asList(Math.min(compliment, num), Math.max(compliment, num));
                    if (seen.contains(compliment)) {
                        return Stream.of(pair);
                    }
                    seen.add(num);
                    return Stream.empty();
                })
                .collect(Collectors.toSet());
//        System.out.println(collect6);

//        Q8 Count of Pairs with Target Sum
        int sum = list1.stream()
                .mapToInt(num -> {
                    int compliment = target - num;
                    if (seen.contains(compliment)) {
                        return 1;
                    }
                    seen.add(num);
                    return 0;
                })
                .sum();
//        System.out.println(sum);

//        Q9 Triplet Sum Unique Triplets

        int target1=8;

        Set<List<Integer>> collect7 = list1.stream()
                .flatMap(w1 -> list1.stream()
                        .flatMap(w2 -> list1.stream()
                                .map(w3 -> {
                                    List<Integer> t = Arrays.asList(w1, w2, w3);
                                    Collections.sort(t);
                                    return t;
                                })))
                .filter(t -> t.get(0) + t.get(1) + t.get(2) == target1)
                .collect(Collectors.toSet());
//        System.out.println(collect7);

//        Q10 Max Pair Sum

        int asInt = list1.stream()
                .flatMapToInt(w1 -> list1.stream()
                        .mapToInt(w2 -> w1 + w2))
                .max()
                .getAsInt();
//        System.out.println(asInt);

//        Q11 Flatten & Filter Nested List

        List<List<Integer>> nested = new ArrayList<>();
        nested.add(new ArrayList<>(Arrays.asList(1,2,3)));
        nested.add(new ArrayList<>(Arrays.asList(4,6)));
        nested.add(new ArrayList<>(Arrays.asList(6)));

        List<Integer> collect8 = nested.stream()
                .flatMap(List::stream)
                .filter(num -> num % 2 == 0)
                .distinct()
                .map(num -> num * num)
                .collect(Collectors.toList());
//        System.out.println(collect8);

//        Q12 Pair Sum from Nested List
        int target2=8;

        Set<List<Integer>> collect9 = nested.stream()
                .flatMap(List::stream)
                .flatMap(num -> {
                    int compliment = target2 - num;
                    List<Integer> pair = Arrays.asList(Math.min(compliment, num), Math.max(compliment, num));
                    if (seen.contains(compliment)) {
                        return Stream.of(pair);
                    }
                    seen.add(num);
                    return Stream.empty();
                })
                .collect(Collectors.toSet());
//        System.out.println(collect9);
    }

}
