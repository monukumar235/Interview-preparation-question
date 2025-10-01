package stream;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PractiseAgain {
    static class DepartmentSummary {
        long totalEmployees;
        double averageSalary;
        List<String> top2Names;

        public DepartmentSummary(long totalEmployees, double averageSalary, List<String> top2Names) {
            this.totalEmployees = totalEmployees;
            this.averageSalary = averageSalary;
            this.top2Names = top2Names;
        }

        @Override
        public String toString() {
            return "Count: " + totalEmployees + ", AvgSalary: " + averageSalary + ", Top2: " + top2Names;
        }
    }

    public static class DepartmentStats{
        long count;
        double avg;
        Employee max;

        public DepartmentStats(long count, double avg, Employee max) {
            this.count = count;
            this.avg = avg;
            this.max = max;
        }
    }
    public static class Employee{
        public int id;
        public String name;
        public double salary;
        public String department;

        public Employee(int id, String name, double salary, String department) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.department = department;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public String getDepartment() {
            return department;
        }
    }
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5,1,2,9,7,9,5);

//        Integer secondLargest = list.stream()
//                .distinct()
//                .sorted(Comparator.reverseOrder())
//                .skip(1)
//                .findFirst()
//                .orElse(null);
//        System.out.println(secondLargest);

        List<String> list1 = Arrays.asList("apple", "ant", "banana", "bat", "ball", "cat", "carrot");

//        Map<Character, String> collect = list1.stream()
//                .collect(Collectors.groupingBy(a -> a.charAt(0),
//                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(String::length)),
//                                Optional::get)));
//        System.out.println(collect);

        List<Employee> employees = Arrays.asList(new Employee(1, "Alice", 75000, "IT"),
                new Employee(2, "Bob", 82000, "IT"),
                new Employee(3, "Charlie", 60000, "HR"),
                new Employee(4, "David", 95000, "Finance"),
                new Employee(5, "Eve", 72000, "Finance"),
                new Employee(6, "Frank", 64000, "HR"),
                new Employee(7, "Grace", 88000, "IT"),
                new Employee(8, "Heidi", 97000, "Finance"));

//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                                Optional::get)));
//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)));
//        collect.forEach((a,b)->{
//            System.out.println(a+" "+b);
//        });

//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), l -> l.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                                        .reversed())
//                                .limit(2)
//                                .toList())));

//        Map<String, DepartmentStats> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), l -> {
//                            int count = l.size();
//                            double avg = l.stream().mapToDouble(Employee::getSalary).average().getAsDouble();
//                            Employee employee = l.stream().max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
//                            return new DepartmentStats(count, avg, employee);
//                        })));
//
//        String s = employees.stream()
//                .filter(emp -> emp.getSalary() > 70000)
//                .sorted(Comparator.comparing(Employee::getDepartment)
//                        .thenComparing(Comparator.comparingDouble(Employee::getSalary)
//                                .reversed()))
//                .map(Employee::getName)
//                .collect(Collectors.joining(","));
//        System.out.println(s);


//        Map<String, Map<String, Long>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.groupingBy(emp -> {
//                            if (emp.getSalary() < 65000) return "LOW";
//                            else if (emp.getSalary() > 65000 && emp.getSalary() < 85000) return "MEDIUM";
//                            else {
//                                return "HIGH";
//                            }
//                        }, Collectors.counting())));

//        Map<String, List<String>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), l -> l.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                                        .reversed())
//                                .limit(3)
//                                .map(Employee::getName)
//                                .toList())));

//        Map<String, DepartmentSummary> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), l -> {
//                            long total = l.size();
//                            double avg = l.stream().mapToDouble(Employee::getSalary).average().getAsDouble();
//                            List<String> top2Name = l.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(2).map(Employee::getName).toList();
//                            return new DepartmentSummary(total, avg, top2Name);
//                        })));
//        System.out.println(collect);

//        List<Integer> list2 = employees.stream()
//                .filter(emp -> emp.getName().startsWith("A") || emp.getName().startsWith("B"))
//                .map(Employee::getId)
//                .toList();
//        System.out.println(list2);

//        double sum = employees.stream()
//                .filter(emp -> emp.getDepartment().equals("IT") && emp.getSalary() > 75000)
//                .mapToDouble(Employee::getSalary)
//                .sum();
//        System.out.println(sum);

//        Optional<Employee> hr = employees.stream()
//                .filter(emp -> emp.getDepartment().equalsIgnoreCase("HR"))
//                .min(Comparator.comparingDouble(Employee::getSalary));

//        Map<String, List<String>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), l -> l.stream()
//                                .sorted(Comparator.comparing(Employee::getName))
//                                .map(Employee::getName)
//                                .toList())));
//        System.out.println(collect);

//        double asDouble = employees.stream()
//                .filter(emp -> emp.getName().length() > 4)
//                .mapToDouble(Employee::getSalary)
//                .average()
//                .getAsDouble();
//        System.out.println(asDouble);

//        Map<String, Map<String, List<String>>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.groupingBy(emp -> {
//                            if (emp.getSalary() <= 80000) return "LOW";
//                            else return "HIGH";
//                        }, Collectors.collectingAndThen(Collectors.toList(), l -> l.stream()
//                                .map(Employee::getName)
//                                .toList()))));

//        Map<String, List<String>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), l -> l.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                                        .reversed())
//                                .limit(2)
//                                .map(Employee::getName)
//                                .toList())));

//        Map<String, DepartmentStats> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), l -> {
//                            int count = l.size();
//                            double avg = l.stream().mapToDouble(Employee::getSalary).average().orElse(0);
//                            Employee employee = l.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).findFirst().orElse(null);
//                            return new DepartmentStats(count, avg, employee);
//                        })));

//        Map<String, DepartmentSummary> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), l -> {
//                            int count = l.size();
//                            double avgSal = l.stream().mapToDouble(Employee::getSalary).average().orElse(0);
//                            List<String> top2Name = l.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(2).map(Employee::getName).toList();
//                            return new DepartmentSummary(count, avgSal, top2Name);
//                        })));

//        Map<String, Map<String, List<String>>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.groupingBy(emp -> {
//                            if (emp.getSalary() <= 65000) return "LOW";
//                            else if (emp.getSalary() > 65001 && emp.getSalary() < 85000) return "MEDIUM";
//                            else return "HIGH";
//                        }, Collectors.collectingAndThen(Collectors.toList(), l -> l.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                                        .reversed())
//                                .limit(2)
//                                .map(Employee::getName)
//                                .toList()))));


    }

}
