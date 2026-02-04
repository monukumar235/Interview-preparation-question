package mockdsa;

import java.util.*;
import java.util.stream.Collectors;

public class MockQuestions10 {
    static class Employee {
        String name;
        String dept;
        double salary;

        public Employee(String name, String dept, double salary) {
            this.name = name;
            this.dept = dept;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public String getDept() {
            return dept;
        }

        public double getSalary() {
            return salary;
        }
        // getters and setters
    }

    public static void main(String[] args) {
        int [] arr={1,9,2,3,5,3,1};
        System.out.println(secondLargest(arr));
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 90000),
                new Employee("Bob", "HR", 75000),
                new Employee("Charlie", "IT", 120000),
                new Employee("David", "HR", 80000),
                new Employee("Eve", "Finance", 95000),
                new Employee("Frank", "Finance", 110000)
        );

        Map<String, Employee> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                        Optional::get
                )));

        Map<String, Double> avg = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDept, Collectors.averagingDouble(Employee::getSalary)));
    }

    private static int secondLargest(int[] arr) {
        int largest = -1;
        int second =-1;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]>largest)
            {
                second=largest;
                largest=arr[i];
            }
            else if (arr[i]>second && arr[i]!=largest)
            {
                second=arr[i];
            }
        }
        return second;
    }
}
