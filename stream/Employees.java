package stream;

import java.lang.annotation.ElementType;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Employees {
    public static class Employee{
        String name;
        String department;
        double salary;
        Employee(String name,String department,double salary){
            this.name=name;
            this.department=department;
            this.salary=salary;
        }
        public String getName() {
            return name;
        }
        public String getDepartment() {
            return department;
        }
        public  double getSalary() {
            return salary;
        }
    }
    public static void main(String[] args) {
        List<Employee> employeeList = Arrays.asList(
                new Employee("Monu","Backend",1300000),
                new Employee("swati","Backend",1700000),
                new Employee("rupam","Frontent",1500000),
                new Employee("Juhi","Frontent",1800000)
        );

        Map<String, List<Employee>> employeeMap = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        List<Employee> employee = employeeMap.get("Backend");

//        for (Employee e : employee){
//            System.out.println(e.getName());
//        }



        Map<String, Employee> departmentByMaxSalary = employeeList
                .stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                                Optional::get
                        )
                ));

        Optional<Double> third = employeeList.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst();
        System.out.println(third.get());


//        Employee employee1 = departmentByMaxSalary.get("Backend");
//        System.out.println(employee1.getName());
//        Employee employee2 = departmentByMaxSalary.get("Frontent");
//        System.out.println(employee2.getName());

        List<Employee> collect = employeeList
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary)
                        .thenComparing(Employee::getName))
                .collect(Collectors.toList());

//        for (Employee e :collect){
//            System.out.println(e.getName());
//            System.out.println(e.getSalary());
//        }

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,9);

        Map<Boolean, List<Integer>> evenOddList = numbers.stream()
                .collect(Collectors.partitioningBy(e -> e % 2 == 0));
        List<Integer> evenList = evenOddList.get(true);
        List<Integer> oddList = evenOddList.get(false);
//        System.out.println(evenList);
//        System.out.println(oddList);

        Map<String, Double> collect1 = employeeList.stream()
                .collect(Collectors.toMap(
                        Employee::getName,
                        Employee::getSalary
                ));
        Double monu = collect1.get("Monu");
        Double swati = collect1.get("swati");
//        System.out.println(monu);
//        System.out.println(swati);
    }
}
