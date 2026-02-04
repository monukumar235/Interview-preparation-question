package mockdsa;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamPractise {
    static class Employee {
        int id;
        String department;
        double salary;

        public Employee(int id, String department, double salary) {
            this.id = id;
            this.department = department;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }
    }

    public static void main(String[] args) {
//        Input:  [4, 2, 2, 5, 7, 8, 8, 1]
//        Output: [2, 4, 8]
//        List<Integer> list = Arrays.asList(4,2,2,5,7,8,8,1);
//
//        List<Integer> collect = list.stream()
//                .filter(n -> n % 2 == 0)
//                .distinct()
//                .sorted()
//                .collect(Collectors.toList());
//        System.out.println(collect);

//        Input:  "swiss"
//        Output: 'w'

//        String input = "swiss";
//
//        Character character = input.chars()
//                .mapToObj(ch -> (char) ch)
//                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(e -> e.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .orElse(null);
//        System.out.println(character);

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Tech", 80000),
                new Employee(2, "Tech", 95000),
                new Employee(3, "HR", 60000),
                new Employee(4, "HR", 75000),
                new Employee(5, "Sales", 70000),
                new Employee(6, "Sales", 90000)
        );

//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(
//                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                        Optional::get
//                )));

//        Input:  [10, 20, 20, 30, 40]
//        Output: 30

//        List<Integer> list = new ArrayList<>(Arrays.asList(10,20,20,30,40));

//        Integer integer = list.stream()
//                .distinct()
//                .sorted(Comparator.reverseOrder())
//                .skip(1)
//                .findFirst()
//                .get();
//        System.out.println(integer);

//        Input:  ["java", "spring", "java", "stream", "spring", "java"]
//        Output: {java=3, spring=2, stream=1}

//        List<String> items= Arrays.asList("java","spring","java","stream","spring","java");

//        Map<String, Long> collect = items.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

//        Input:  [[1,2], [3,4], [5]]
//        Output: [1,2,3,4,5]

//        List<List<Integer>> list = Arrays.asList(
//                Arrays.asList(1,2),
//                Arrays.asList(3,4),
//                Arrays.asList(5)
//        );
//
//        List<Integer> list1 = list.stream()
//                .flatMap(List::stream)
//                .toList();

//        Input:  [1,2,3,2,4,5,1,6]
//        Output: [1,2]

//        List<Integer> list = Arrays.asList(1,2,3,2,4,5,1,6);

//        List<Integer> collect = list.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(e -> e.getValue() > 1)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(
//                                Collectors.toList(),
//                                list -> list.stream()
//                                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                                        .limit(2)
//                                        .mapToDouble(Employee::getSalary)
//                                        .sum()
//                        )));

//        Input: [1, 2, 3, 4]
//        Output: 24


//        List<Integer> list = Arrays.asList(1,2,3,4);
//
//        Optional<Integer> reduce = list.stream()
//                .filter(n -> n % 2 == 0)
//                .map(n -> n * n)
//                .reduce(Integer::sum);

//        Integer integer = list.stream()
//                .reduce(1,(a,b)->a*b);
//        System.out.println(integer);

//        Input: [10, 40, 150, 200, 90]
//        Output: 150
//        List<Integer> list = Arrays.asList(10,40,150,200,90);

//        Optional<Integer> first = list.stream()
//                .filter(num -> num > 100)
//                .findFirst();

//        List<Integer> list = Arrays.asList();

//        List<Integer> list1 = list.stream()
//                .distinct()
//                .toList();
//        System.out.println(list1);
//
//        Set<Integer> seen = new HashSet<>();
//
//        list.stream()
//                .filter(n -> seen.add(n))
//                .forEach(System.out::println);

    }
}
