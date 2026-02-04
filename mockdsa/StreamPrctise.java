package mockdsa;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamPrctise {
    static class Item {
        private String name;
        private double price;

        public Item(String name,Double price) {
            this.name = name;
            this.price=price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
// constructor + getters
    }

    static class Order {
        private List<Item> items;

        // constructor + getters

        public Order(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }
    }

    public static class Employee{
        int id;
        String name;
        String dept;
        double salary;
        Employee(int id, String name, String dept, double salary) {
            this.id = id;
            this.name = name;
            this.dept = dept;
            this.salary = salary;
        }

        // getters (optional for streams)
        public int getId() { return id; }
        public String getName() { return name; }
        public String getDept() { return dept; }
        public double getSalary() { return salary; }
    }
    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 2, 3, 5);
//        HashSet<Integer> seen = new HashSet<>();
//        List<Integer> res = list.stream()
//                .filter(x -> !seen.add(x))
//                .collect(Collectors.toList());
//        System.out.println(res);

//        List<Integer> res = list.stream()
//                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(e -> e.getValue() > 1)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());
//        System.out.println(res);

        String str = "swiss";

//        Character character = str.chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(x -> x, LinkedHashMap::new, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(e -> e.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .orElse(null);
//        System.out.println(character);


        List<Employee> employees = Arrays.asList(
                new Employee(1, "Amit", "IT", 70000),
                new Employee(2, "Rohit", "IT", 90000),
                new Employee(3, "Neha", "HR", 60000),
                new Employee(4, "Priya", "HR", 80000),
                new Employee(5, "Karan", "Finance", 75000),
                new Employee(6, "Sonal", "Finance", 95000),
                new Employee(7, "Vikas", "IT", 85000)
        );

        Map<String, Employee> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDept
                        , Collectors.collectingAndThen(Collectors.toList(),
                                lst -> lst.stream()
                                        .sorted(Comparator.comparing(Employee::getSalary).reversed())
                                        .skip(1)
                                        .findFirst()
                                        .get())));

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(
//                        Collectors.toList(),
//                        lst -> lst.stream()
//                                .collect(Collectors.averagingDouble(Employee::getSalary))
//                )));

//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(
//                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                        Optional::get
//                )));

//        Map<String, Optional<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(e -> e.getDept(), Collectors.maxBy(Comparator.comparingDouble(emp -> emp.getSalary()))));

//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(
//                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                        Optional::get
//                )));

//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(
//                        Collectors.toList(),
//                        lists -> lists.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                                        .reversed())
//                                .limit(2)
//                                .collect(Collectors.toList())
//                )));

//        List<Integer> nums = Arrays.asList(3, 5, 7, 3, 9, 5, 11);

//        Integer integer = nums.stream()
//                .collect(Collectors.groupingBy(x -> x, LinkedHashMap::new, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(x -> x.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .orElse(-1);
//        System.out.println(integer);

//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDept,
//                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                                Optional::get)));

//        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//
//        Map<Boolean, Integer> collect = nums.stream()
//                .collect(Collectors.partitioningBy(x -> x % 2 == 0, Collectors.summingInt(Integer::intValue)));

//        List<String> items = List.of("glass", "frame", "glass", "lens", "frame", "glass");
//
//        Map<String, Long> collect = items.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

//        String s = "swiss";
//        Character ans = s.chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(x -> x.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .orElse(null);
//
//        List<Order> orders = List.of(
//                new Order(List.of("frame", "lens", "cloth")),
//                new Order(List.of("lens", "case")),
//                new Order(List.of("frame", "screw", "lens"))
//        );
//
//        orders.stream()
//                .flatMap(order->order.getItems().stream())
//                .distinct()
//                .collect(Collectors.toList());


//        List<Order> orders = List.of(
//                new Order(List.of("frame", "lens")),          // 2 items
//                new Order(List.of("lens")),                   // 1 item
//                new Order(List.of("frame", "screw", "lens")),// 3 items
//                new Order(List.of("lens", "case")),          // 2 items
//                new Order(List.of("frame"))                   // 1 item
//        );

//        Map<Integer, Long> collect = orders.stream()
//                .map(order -> order.getItems().size())
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

//        List<Order> orders = List.of(
//                new Order(List.of("frame", "lens")),          // 2 items → false
//                new Order(List.of("lens")),                   // 1 item  → false
//                new Order(List.of("frame", "screw", "lens")),// 3 items → true
//                new Order(List.of("lens", "case")),          // 2 items → false
//                new Order(List.of("frame", "stand", "case")) // 3 items → true
//        );

//        orders.stream()
//                .collect(Collectors.partitioningBy(order->order.getItems().size()>2));
//        String s = orders.stream()
//                .flatMap(order -> order.getItems().stream())
//                .distinct()
//                .collect(Collectors.joining(","));

//        List<Item> items = List.of(
//                new Item("frame", 250.0),
//                new Item("lens", 300.0),
//                new Item("case", 150.0),
//                new Item("screw", 50.0)
//        );

//        Optional<Item> max = items.stream()
//                .max(Comparator.comparingDouble(item -> item.getPrice()));
//        System.out.println(max);
//
//        double sum = items.stream()
//                .mapToDouble(item -> item.getPrice())
//                .sum();

//        Map<String, List<Item>> collect = items.stream()
//                .collect(Collectors.groupingBy(item -> {
//                    if (item.getPrice() < 100) return "Cheaper";
//                    else if (item.getPrice() <= 100 && item.getPrice() <= 300) return "Moderate";
//                    else return "High";
//                }));

//        List<Integer> numbers = List.of(5, 12, 7, 18, 20, 3, 10);
//
//        List<Integer> list = numbers.stream()
//                .filter(num -> num % 2 == 0 && num > 10)
//                .toList();

//        List<Integer> numbers = List.of(2, 3, 4, 6, 9);
//        int sum = numbers.stream()
//                .filter(num -> num % 3 == 0)
//                .map(num -> num * num)
//                .mapToInt(Integer::intValue)
//                .sum();
//        System.out.println(sum);

//        List<String> sentences = List.of(
//                "hello world",
//                "world of java",
//                "hello java"
//        );
//
//        List<String> collect = sentences.stream()
//                .flatMap(sentence->Arrays.stream(sentence.split(" ")))
//                .distinct()
//                .collect(Collectors.toList());
//        System.out.println(collect);
//
//        List<String> words = List.of("frame","lens","case","stand","screw");
//
//        Map<Integer, List<String>> collect = words.stream()
//                .collect(Collectors.groupingBy(strs -> strs.length(), Collectors.toList()));

//        List<Order> orders = List.of(
//                new Order(List.of("frame", "lens")),
//                new Order(List.of("lens", "case", "stand")),
//                new Order(List.of("frame", "screw", "lens", "case")),
//                new Order(List.of("case"))
//        );
//        long count = orders.stream()
//                .filter(item -> item.getItems().size() > 2)
//                .count();
//        System.out.println(count);

//        List<String> products = List.of("frame", "lens", "fabric", "case");

//        Map<Boolean, List<String>> f = products.stream()
//                .collect(Collectors.partitioningBy(product -> product.startsWith("f")));

//        List<String> names = List.of("Monu", "Ravi", "Ankit");

//        names.stream()
//                .collect(Collectors.joining(","));

        List<Order> orders = List.of(
                new Order(List.of(
                        new Item("frame", 250.0),
                        new Item("lens", 300.0)
                )),
                new Order(List.of(
                        new Item("case", 150.0),
                        new Item("lens", 300.0)
                ))
        );

//        List<String> list = orders.stream()
//                .flatMap(order -> order.getItems().stream())
//                .map(item -> item.getName())
//                .distinct()
//                .toList();

//        List<Item> items = orders.stream()
//                .flatMap(order -> order.getItems().stream())
//                .sorted(Comparator.comparingDouble(Item::getPrice).reversed())
//                .limit(3)
//                .toList();
    }
}
