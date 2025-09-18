package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiPrec {
    static class  employee{
        String name;
        String department;
        double salary;
        int age;
        String gender;

        public employee(String name, String department, double salary, int age, String gender) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        public int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }
    }
    public static void main(String[] args) {
        List<employee> employees = Arrays.asList(
                new employee("Alice", "IT", 70000, 28, "Female"),
                new employee("Bob", "HR", 50000, 35, "Male"),
                new employee("Charlie", "Finance", 80000, 40, "Male"),
                new employee("David", "IT", 90000, 30, "Male"),
                new employee("Eve", "Finance", 85000, 45, "Female"),
                new employee("Frank", "HR", 48000, 50, "Male"),
                new employee("Grace", "IT", 75000, 27, "Female"),
                new employee("Hank", "Sales", 62000, 32, "Male"),
                new employee("Ivy", "Sales", 61000, 26, "Female"),
                new employee("Jack", "Finance", 79000, 29, "Male")
        );

        List<employee> ageGreaterThan30 = employees.stream()
                .filter(e -> e.getAge() > 30)
                .toList();
//        for (employee e : ageGreaterThan30){
//            System.out.println(e.getName());
//        }

        List<String> nameUpperCase = employees.stream()
                .map(e -> e.getName().toUpperCase())
                .toList();
//        for (String e : nameUpperCase){
//            System.out.println(e);
//        }

        List<String> distinct = employees.stream()
                .map(employee::getDepartment)
                .distinct()
                .toList();



//        System.out.println(distinct);

        List<employee> employeeList = employees.stream()
                .filter(e -> e.getName().startsWith("A"))
                .toList();

//       for (employee e : employeeList){
//           System.out.println(e.getName());
//       }

        List<employee> sortedByName = employees.stream()
                .sorted(Comparator.comparing(employee::getName))
                .toList();

//        for (employee e : sortedByName){
//            System.out.println(e.getName());
//        }

        List<employee> sortedByDeptAndSal = employees.stream()
                .sorted(Comparator.comparing(employee::getDepartment)
                        .thenComparing(employee::getSalary))
                .toList();
//        for (employee e: sortedByDeptAndSal){
//            System.out.println(e.getName());
//            System.out.println(e.getSalary());
//        }

        List<employee> filterAndSort = employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .sorted(Comparator.comparingInt(employee::getAge))
                .toList();
//        for (employee e : filterAndSort){
//            System.out.println(e.getAge());
//            System.out.println(e.getSalary());
//        }

        Map<String, List<employee>> emplyeeGroupByGender = employees.stream()
                .collect(Collectors.groupingBy(employee::getGender));

        List<employee> maleEmp = emplyeeGroupByGender.get("Male");
//        for (employee e :maleEmp){
//            System.out.println(e.getGender());
//        }

        Map<String, Long> collect = employees.stream()
                .collect(Collectors.groupingBy(employee::getDepartment, Collectors.counting()));

//        System.out.println(collect.get("IT"));


        Map<String, Double> employsaldeptwise = employees.stream()
                .collect(Collectors.groupingBy(employee::getDepartment, Collectors.summingDouble(employee::getSalary)));

//        System.out.println(employsaldeptwise.get("HR"));

        Map<String, Optional<employee>> empsalDept = employees.stream()
                .collect(Collectors.groupingBy(employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(employee::getSalary))));
//        Optional<employee> hr = empsalDept.get("HR");
//        employee employee = hr.get();
//        System.out.println(employee.getName());

        Map<String, List<String>> listMap = employees.stream()
                .collect(Collectors.groupingBy(employee::getDepartment,
                        Collectors.mapping(employee::getName, Collectors.toList())));

//        listMap.forEach((dept,name)->{
//            System.out.println(dept + " "+name);
//        });

        Map<Boolean, List<employee>> partitions = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 50000));

//        System.out.println(partitions.get(true));
//        System.out.println(partitions.get(false));

        Map<String, Integer> map = employees.stream()
                .collect(Collectors.toMap(
                        employee::getName,
                        employee::getAge
                ));
//        System.out.println(map.get("Bob"));

        Double sumSal = employees.stream()
                .collect(Collectors.summingDouble(employee::getSalary));
//        System.out.println(sumSal);

        Double avgSal = employees.stream()
                .collect(Collectors.averagingDouble(employee::getSalary));
//        System.out.println(avgSal);

        Optional<employee> optionalEmployee = employees.stream()
                .max(Comparator.comparing(e->e.getName().length()));
        employee emp = optionalEmployee.get();
//        System.out.println(employee.getName());

        Optional<employee> min = employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("finance"))
                .filter(e -> e.getGender().equalsIgnoreCase("male"))
                .min(Comparator.comparingInt(e -> e.getAge()));
//        System.out.println(min.get().getName());


        List<employee> employeeStream = employees.stream()
                .filter(e -> e.getAge() > 30).collect(Collectors.toList());
//        for (employee e: employeeStream){
//            System.out.println(e.getName());
//        }

        List<String> nameList = employees.stream()
                .map(e -> e.getName().toLowerCase())
                .collect(Collectors.toList());

//        for (String name : nameList){
//            System.out.println(name);
//        }

        long count = employees.stream()
                .filter(e -> e.getDepartment()
                        .equalsIgnoreCase("IT"))
                .count();
//        System.out.println(count);

        List<String> strings = employees.stream()
                .map(e -> e.getDepartment())
                .distinct()
                .toList();
//        System.out.println(strings);


        List<Boolean> booleans = employees.stream()
                .map(e -> e.getSalary() > 100000)
                .toList();
//        System.out.println(booleans);

        List<employee> employees1 = employees.stream()
                .sorted(Comparator.comparingDouble(employee::getSalary)
                        .reversed())
                .toList();
//        for (employee e :employees1){
//            System.out.println(e.getSalary());
//        }

        List<employee> employeeSoredAndSal = employees.stream()
                .filter(e -> e.getSalary() > 70000)
                .sorted(Comparator.comparing(employee::getName))
                .toList();
//        for (employee e : employeeSoredAndSal){
//            System.out.println(e.getName()+ " " + " " + e.getSalary());
//        }

        List<employee> employees2 = employees.stream()
                .filter(e -> e.getGender().equalsIgnoreCase("male"))
                .filter(e -> e.getDepartment().equalsIgnoreCase("sales"))
                .toList();
//        for (employee e : employees2){
//            System.out.println(e.getGender() + " "+ " "+ e.getDepartment());
//        }

        Map<String, Long> collect1 = employees.stream()
                .collect(Collectors.groupingBy(employee::getDepartment, Collectors.counting()));

//        System.out.println(collect1.get("HR"));

        Double female = employees.stream()
                .filter(e -> e.getGender().equalsIgnoreCase("female"))
                .collect(Collectors.averagingDouble(employee::getAge));
//        System.out.println(female);


        Map<String, Double> collect2 = employees.stream()
                .collect(Collectors.groupingBy(employee::getDepartment, Collectors.averagingDouble(employee::getSalary)));

//        System.out.println(collect2.get("HR"));


    }

}
