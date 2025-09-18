package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamPrec {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Map<Integer, Long> freq = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Integer third = list.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .get();
//        System.out.println(third);

        Integer evenSum1 = list.stream()
                .filter(n -> n % 2 == 0)
                .reduce(Integer::sum)
                .get();
//        System.out.println(evenSum1);

        Integer an = list.stream()
                .min(Integer::compare).get();
        System.out.println(an);

        List<Integer> even = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
//        System.out.println(even);

        List<String> names = Arrays.asList("john", "alice", "bob");
        List<String> nameUpper = names.stream().map(String::toUpperCase).collect(Collectors.toList());
//        System.out.println(nameUpper);

        List<String> words = Arrays.asList("apple", "banana", "apricot", "grape");

        long count = words.stream().filter(s->s.startsWith("g")).count();
//        System.out.println(count);

        List<String> data = Arrays.asList("a", null, "b", "", "c");
        List<String> ans = data.stream()
                .filter(Objects::nonNull)
                .filter(s->!s.isEmpty())
                .collect(Collectors.toList());
//        System.out.println(ans);


        List<Integer> numbers = Arrays.asList(1,2,4,5);

        Integer max = numbers.stream().max(Integer::compare).get();
//        System.out.println(max);

        Integer evenSum = numbers
                .stream()
                .filter(n->n%2==0)
                .reduce(Integer::sum)
                .get();
//        System.out.println(evenSum);

        List<String> sortedList = words.stream().sorted().collect(Collectors.toList());
//        System.out.println(sortedList);


        List<String> fruits = Arrays.asList("apple", "banana", "cherry","apple");
        String stringWithComma = fruits.stream().collect(Collectors.joining(","));
//        System.out.println(stringWithComma);

        List<Integer> num = Arrays.asList(1,2,3,4,1,2);

        List<Integer> collect = num
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
//        System.out.println(collect);

    }


}
