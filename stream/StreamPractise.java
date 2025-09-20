package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractise {
    public static class Employee{
        int id;
        String name;
        String department;
        double salary;
        int age;

        public Employee(int id, String name, String department, double salary, int age) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "HR", 50000, 25),
                new Employee(2, "Bob", "IT", 70000, 30),
                new Employee(3, "Charlie", "IT", 60000, 28),
                new Employee(4, "David", "Finance", 55000, 26),
                new Employee(5, "Eva", "HR", 65000, 32),
                new Employee(6, "Frank", "Finance", 75000, 35)
        );

//        Q1
//        Double aDouble = employees.stream()
//                .map(Employee::getSalary)
//                .distinct()
//                .sorted(Comparator.reverseOrder())
//                .skip(1)
//                .findFirst()
//                .orElse(null);


//        Q2
//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(
//                                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                                Optional::get)));
//        System.out.println(collect);

//        Q3
//        Map<Boolean, List<Employee>> collect = employees.stream()
//                .collect(Collectors.partitioningBy(emp -> emp.salary > 60000,
//                        Collectors.collectingAndThen(Collectors.toList(), list ->
//                                list.stream()
//                                        .sorted(Comparator.comparingInt(Employee::getAge).reversed())
//                                        .toList())));


//Q4
//        List<List<Employee>> lists = employees.stream()
//                .flatMap(a -> employees.stream()
//                        .flatMap(b -> employees.stream()
//                                .map(c -> Arrays.asList(a, b, c))))
//                .filter(emp -> emp.get(0).getSalary() + emp.get(1).getSalary() + emp.get(2).getSalary() == 180000)
//                .distinct()
//                .sorted()
//                .toList();
//        System.out.println(lists);

//        Q5
//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(),
//                                list -> list.stream()
//                                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                                        .skip(1)
//                                        .findFirst()
//                                        .orElse(null))));
//        Q6
//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                                .limit(2)
//                                .toList())));

//        Q7
//        Map<String, String> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .map(Employee::getName)
//                                .collect(Collectors.joining(",")))));
//        System.out.println(collect);

//        Q8
//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .filter(emp -> emp.getAge() > 30)
//                                .collect(Collectors.averagingDouble(Employee::getSalary)))));
//        Q9
//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .filter(emp -> emp.getAge() < 30)
//                                .min(Comparator.comparingDouble(Employee::getSalary))
//                                .orElse(null))));

//        Q10
//        Map.Entry<String, Double> stringDoubleEntry = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)))
//                .entrySet()
//                .stream()
//                .max(Comparator.comparingDouble(Map.Entry::getValue))
//                .orElse(null);

//        Q11
//        String topAvg = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)))
//                .entrySet()
//                .stream()
//                .max(Comparator.comparingDouble(Map.Entry::getValue))
//                .get()
//                .getKey();
//
//        List<String> list = employees.stream()
//                .filter(emp -> emp.getDepartment().equals(topAvg))
//                .map(emp -> emp.getName())
//                .toList();
//        System.out.println(list);

//        Q12
//
//        String secondHighestDepartment = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)))
//                .entrySet()
//                .stream()
//                .sorted(Comparator.comparingDouble(Map.Entry::getValue)
//                        .reversed())
//                .skip(1)
//                .findFirst()
//                .get()
//                .getKey();
//        System.out.println(secondHighestDepartment);
//
//        List<String> list = employees.stream()
//                .filter(emp -> emp.getDepartment().equals(secondHighestDepartment))
//                .map(Employee::getName)
//                .sorted()
//                .toList();

//        Q13
//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                                Optional::get)));

//        Q14
//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                                .limit(2)
//                                .toList())));
//        Q15
//        Map<String, Map<String, Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.groupingBy(emp -> {
//                            if (emp.getAge() < 30) return "Young";
//                            else if (emp.getAge() > 30 && emp.getAge() < 40) return "Mid";
//                            else {
//                                return "Senior";
//                            }
//                        }, Collectors.collectingAndThen(Collectors.toList(), list ->
//                                list.stream()
//                                        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                                        .findFirst()
//                                        .get()))));

       int n =3;

//        Employee employee = employees.stream()
//                .distinct()
//                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                .skip(n-1)
//                .findFirst()
//                .orElse(null);

//        Q15
//        String s = "programming";
//
//        Map<Character, Long> collect = s.chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(e -> e.getValue() > 1)
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue
//                ));

//        String s = "apple banana apple orange banana apple mango";
//
//        String[] words = s.split(" ");
//
//        List<Map.Entry<String, Long>> entries = Arrays.stream(words)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet()
//                .stream()
//                .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
//                .limit(3)
//                .toList();

//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                                Optional::get)));
//
//        Employee employee = collect.values()
//                .stream()
//                .min(Comparator.comparingDouble(Employee::getSalary))
//                .get();

//        Q1
//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(Employee::getAge)),
//                                Optional::get)));
//        Double collect1 = collect.values()
//                .stream()
//                .collect(Collectors.averagingDouble(Employee::getSalary));
//        System.out.println(collect1);

//        Q2
//        Employee employee = employees.stream()
//                .distinct()
//                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                .skip(2)
//                .findFirst()
//                .get();


//        Q3
//        String deptWithMaxAvgSal = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)))
//                .entrySet()
//                .stream()
//                .max(Map.Entry.comparingByValue())
//                .get()
//                .getKey();
//        List<String> list = employees.stream()
//                .filter(emp -> emp.getDepartment().equals(deptWithMaxAvgSal))
//                .map(emp -> emp.getName())
//                .sorted()
//                .toList();

//        Q4
//        Map<String, String> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                                .skip(1)
//                                .findFirst()
//                                .map(emp -> emp.getName())
//                                .orElse(null))));

//        Q5
//        Map<String, List<String>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                                .limit(2)
//                                .map(Employee::getName)
//                                .toList())));

//        Q6

//        Map<String, Double> collect = employees.stream()
//                .filter(emp -> emp.getAge() > 30)
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)));

//        Q7
//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)));
//
//        List<Employee> list = employees.stream()
//                .filter(emp -> emp.getSalary() > collect.get(emp.getDepartment()))
//                .toList();

//        Q8

//        Map<String, String> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingInt(Employee::getAge))
//                                .skip(1)
//                                .findFirst()
//                                .map(Employee::getName)
//                                .orElse(null))));

//        Q9

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                                .limit(2)
//                                .mapToInt(Employee::getAge)
//                                .average()
//                                .orElse(0.0))));
////        Q10
//        Map<String, Optional<String>> collect = employees.stream()
//                .filter(emp -> emp.getAge() > 30)
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .min(Comparator.comparingDouble(Employee::getSalary))
//                                .map(Employee::getName))));

//        Map<String, Long> collect = employees.stream()
//                .filter(emp -> emp.getAge() > 30)
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.counting()));

//        double avgSal = employees.stream()
//                .mapToDouble(Employee::getSalary)
//                .average()
//                .orElse(0.0);
//
//        String name = employees.stream()
//                .min(Comparator.comparingDouble(emp -> Math.abs(avgSal - emp.getSalary())))
//                .get()
//                .getName();
//        System.out.println(name);

//        List<String> list = employees.stream()
//                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                .map(Employee::getName)
//                .distinct()
//                .skip(2)
//                .findFirst()
//                .stream().toList();

//        int lenght = employees.stream()
//                .mapToInt(emp -> emp.getName().length()).max()
//                .getAsInt();
//        List<Employee> employees1 = employees.stream()
//                .filter(emp -> emp.getName().length() == lenght)
//                .toList();

//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
//        int target = 7;
//        Set<Integer> seen = new HashSet<>();
//
//        List<String> list1 = list.stream()
//                .flatMap(num -> {
//                    int com = target - num;
//                    if (seen.contains(com)) {
//                        return Stream.of("(" + com + "," + num + ")");
//                    }
//                    seen.add(num);
//                    return Stream.empty();
//                }).toList();
//        System.out.println(list1);

//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//
//        List<Integer> evenList = numbers.stream()
//                .filter(num -> num % 2 == 0)
//                .toList();

//        List<String> words = Arrays.asList("java", "stream", "api");
//
//        List<String> list = words.stream()
//                .map(a -> a.toUpperCase())
//                .toList();

//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//
//        Integer integer = numbers.stream()
//                .reduce(Integer::sum)
//                .get();

//        List<String> words = Arrays.asList("java", "is", "fun", "stream");
//
//        long count = words.stream()
//                .filter(s -> s.length() > 3)
//                .count();

//        List<Integer> numbers = Arrays.asList(1, 3, 5, 6, 7);
//
//        Integer integer = numbers.stream()
//                .filter(nums -> nums % 2 == 0)
//                .findFirst()
//                .get();

//        List<Integer> numbers = Arrays.asList(3, 1, 4, 5, 2);
//
//        List<Integer> list = numbers.stream()
//                .sorted(Collections.reverseOrder())
//                .toList();

//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 3);
        int target = 7;

//        Set<List<Integer>> collect = list.stream()
//                .flatMap(a -> list.stream()
//                        .flatMap(b -> list.stream()
//                                .map(c -> {
//                                    List<Integer> t = Arrays.asList(a, b, c);
//                                    Collections.sort(t);
//                                    return t;
//                                })))
//                .filter(t -> t.get(0) + t.get(1) + t.get(2) == target)
//                .collect(Collectors.toSet());
//        System.out.println(collect);
//
//        Set<Integer> seen = new HashSet<>();
//
//        List<String> list1 = list.stream()
//                .flatMap(num -> {
//                    int compliment = target - num;
//                    if (seen.contains(compliment)) {
//                        return Stream.of("(" + compliment + "," + num + ")");
//                    }
//                    seen.add(num);
//                    return Stream.empty();
//                })
//                .toList();

//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                                Optional::get)));

        List<List<Integer>> nested =new ArrayList<>();
        nested.add(new ArrayList<>(Arrays.asList(1,2,3)));
        nested.add(new ArrayList<>(Arrays.asList(4,5,6)));
        nested.add(new ArrayList<>(Arrays.asList(2,3)));

        int target1 =5;
        HashSet<Integer> set = new HashSet<>();

//        Set<List<Integer>> collect1 = nested.stream()
//                .flatMap(List::stream)
//                .flatMap(num -> {
//                    int com = target1 - num;
//                    List<Integer> pair = Arrays.asList(Math.min(com, num), Math.max(com, num));
//                    if (set.contains(com)) {
//                        return Stream.of(pair);
//                    }
//                    set.add(num);
//                    return Stream.empty();
//                })
//                .collect(Collectors.toSet());
//        System.out.println(collect1);

//        List<Integer> collect = nested.stream()
//                .flatMap(List::stream)
//                .filter(num -> num % 2 == 0)
//                .map(num -> num * num)
//                .collect(Collectors.toList());
//        System.out.println(collect);


        List<String> words = new ArrayList<>(Arrays.asList("a","ab","cd","abc"));
        int a=3;

//        List<String> collect = words.stream()
//                .flatMap(w1 -> words.stream()
//                        .map(w2 -> w1 + w2))
//                .filter(s -> s.length() == a)
//                .collect(Collectors.toList());
//        System.out.println(collect);

//        List<Integer> collect = list.stream()
//                .map(num -> num * num)
//                .sorted()
//                .distinct()
//                .collect(Collectors.toList());
//        System.out.println(collect);

//        List<String> stringList = new ArrayList<>(Arrays.asList("Apple", "Banana", "Avocado", "Mango", "Apricot"));
//        String res = stringList.stream()
//                .filter(str -> str.startsWith("A"))
//                .collect(Collectors.joining(","));
//        System.out.println(res);

//        Map<String, Long> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.counting()));

//        int x =5500;
//        List<String> collect = employees.stream()
//                .filter(emp -> emp.getSalary() > x)
//                .map(Employee::getName)
//                .collect(Collectors.toList());

//        Integer integer = nested.stream()
//                .flatMap(List::stream)
//                .filter(num -> num % 2 == 1)
//                .reduce(Integer::sum)
//                .get();
        Set<Integer> seen = new HashSet<>();
//        List<Integer> pair = new ArrayList<>();

        Set<List<Integer>> collect = list.stream()
                .flatMap(num -> {
                    int compliment = target - num;
                    List<Integer> pair = Arrays.asList(Math.min(num, compliment), Math.max(num, compliment));
                    if (seen.contains(compliment)) {
                        return Stream.of(pair);
                    }
                    seen.add(num);
                    return Stream.empty();
                })
                .collect(Collectors.toSet());
//        System.out.println(collect);

//        Set<String> collect = list.stream()
//                .flatMap(num -> {
//                    int com = target - num;
//                    if (seen.contains(com)) {
//                        return Stream.of("(" + com + "," + num + ")");
//                    }
//                    seen.add(num);
//                    return Stream.empty();
//                })
//                .collect(Collectors.toSet());
//        System.out.println(collect);

//        int num=2;
//        List<Employee> collect = employees.stream()
//                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                .limit(num)
//                .collect(Collectors.toList());
//
//        Set<List<Integer>> collect = list.stream()
//                .flatMap(lst1 -> list.stream()
//                        .flatMap(lst2 -> list.stream()
//                                .map(lst3 -> {
//                                    List<Integer> t = Arrays.asList(lst1, lst2, lst3);
//                                    Collections.sort(t);
//                                    return t;
//                                })))
//                .filter(t -> t.get(0) + t.get(1) + t.get(2) == target)
//                .collect(Collectors.toSet());
//        System.out.println(collect);

        String s = "swiss";

//        Character key = s.chars()
//                .mapToObj(str -> (char) str)
//                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(e -> e.getValue() == 1)
//                .findFirst()
//                .get()
//                .getKey();
//        System.out.println(key);

        List<Integer> list1 = new ArrayList<>(Arrays.asList(10, 5, 20, 20, 4, 5, 15));

//        Integer integer = list1.stream()
//                .distinct()
//                .sorted(Collections.reverseOrder())
//                .skip(2)
//                .findFirst()
//                .orElse(null);
//        System.out.println(integer);

//        Integer integer = list1.stream()
//                .distinct()
//                .sorted()
//                .skip(1)
//                .findFirst()
//                .orElse(null);
//        System.out.println(integer);


//        Character key = s.chars()
//                .mapToObj(str -> (char) str)
//                .collect(Collectors.groupingBy(w -> w, LinkedHashMap::new, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(e -> e.getValue() == 1)
//                .findFirst()
//                .get()
//                .getKey();

        String s1 = "banana";

//        LinkedHashMap<Character, Long> collect1 = s1.chars()
//                .mapToObj(str -> (char) str)
//                .collect(Collectors.groupingBy(w -> w, LinkedHashMap::new, Collectors.counting()));

        String word = "Java streams make coding fun";

        String[] s2 = word.split(" ");

//        int maxLenght = Arrays.stream(s2)
//                .mapToInt(w -> w.length()).max()
//                .getAsInt();
//        List<String> collect1 = Arrays.stream(s2)
//                .filter(str -> str.length() == maxLenght)
//                .collect(Collectors.toList());

//        String s3 = "listen";
//        String s4 = "silent";
//
//        s3  = s3.toLowerCase().replace("\\s", "");
//        s4 = s4.toLowerCase().replace("\\s","");
//
//        String collect1 = s3.chars()
//                .mapToObj(c -> (char) c)
//                .sorted()
//                .map(String::valueOf)
//                .collect(Collectors.joining());
//
//        String collect2 = s4.chars()
//                .mapToObj(c -> (char) c)
//                .sorted()
//                .map(String::valueOf)
//                .collect(Collectors.joining());
//        if (collect1.equals(collect2)){
//            System.out.println("analog");
//        }

        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3));
        int targets=5;


//        long count = list2.stream()
//                .mapToInt(num -> {
//                    int compliment = targets - num;
//                    if (seen.contains(compliment)) {
//                        return 1;
//                    }
//                    seen.add(num);
//                    return 0;
//                })
//                .sum();
//        System.out.println(count);

        List<String> words1 = new ArrayList<>(Arrays.asList("a","b","c"));
        int lenght=2;

//        List<String> collect1 = words1.stream()
//                .flatMap(w1 -> words1.stream()
//                        .map(w2 -> w1 + w2))
//                .filter(w -> w.length() == lenght)
//                .collect(Collectors.toList());
//        System.out.println(collect1);

//        List<String> collect1 = words1.stream()
//                .flatMap(w1 -> words1.stream()
//                        .map(w2 -> w1 + w2))
//                .collect(Collectors.toList());
//        System.out.println(collect1);

//        Set<List<Integer>> collect1 = list2.stream()
//                .flatMap(num -> {
//                    int compliment = targets - num;
//                    List<Integer> pair = Arrays.asList(Math.min(num, compliment), Math.max(num, compliment));
//                    if (seen.contains(compliment)) {
//                        return Stream.of(pair);
//                    }
//                    seen.add(num);
//                    return Stream.empty();
//                })
//                .collect(Collectors.toSet());
//        System.out.println(collect1);

//        Set<List<Integer>> collect1 = list2.stream()
//                .flatMap(l1 -> list2.stream()
//                        .flatMap(l2 -> list2.stream()
//                                .map(l3 -> {
//                                    List<Integer> t = Arrays.asList(l1, l2, l3);
//                                    Collections.sort(t);
//                                    return t;
//                                })))
//                .filter(t -> t.get(0) + t.get(1) + t.get(2) == targets)
//                .collect(Collectors.toSet());
//        System.out.println(collect1);


//        List<Integer> collect1 = nested.stream()
////                .flatMap(List::stream)
////                .filter(num -> num % 2 == 0)
////                .sorted()
////                .map(num -> num * num)
////                .collect(Collectors.toList());
////        System.out.println(collect1);
    }
}
