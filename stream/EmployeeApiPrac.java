package stream;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public  class EmployeeApiPrac {
    static class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;
        private int age;
        private String gender;

        public int getId() {
            return id;
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

        public  int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        Employee(int id, String name, String department, double salary, int age, String gender) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.age = age;
            this.gender = gender;
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(101, "Alice", "IT", 75000, 28, "Female"),
                new Employee(102, "Bob", "HR", 45000, 32, "Male"),
                new Employee(103, "Charlie", "Finance", 80000, 45, "Male"),
                new Employee(104, "Diana", "IT", 95000, 30, "Female"),
                new Employee(105, "Ethan", "Sales", 40000, 25, "Male"),
                new Employee(106, "Fiona", "Finance", 70000, 29, "Female"),
                new Employee(107, "George", "HR", 50000, 41, "Male"),
                new Employee(108, "Hannah", "IT", 85000, 35, "Female"),
                new Employee(109, "Ian", "Sales", 60000, 38, "Male"),
                new Employee(110, "Jenny", "Finance", 72000, 27, "Female"),
                new Employee(111, "Kevin", "IT", 50000, 23, "Male"),
                new Employee(112, "Laura", "Sales", 65000, 34, "Female"),
                new Employee(113, "Michael", "Finance", 90000, 39, "Male"),
                new Employee(114, "Nina", "HR", 47000, 26, "Female"),
                new Employee(115, "Oscar", "Sales", 55000, 42, "Male")
        );

////        1)
//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                                Optional::get)));
////        System.out.println(collect.get("IT").getName());
//
////        Q2
//        Map<String, Double> avgSalByGender = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getGender,
//                        Collectors.averagingDouble(Employee::getSalary)));
////        System.out.println(avgSalByGender.get("Male"));
//
////        Q3
//        List<Employee> employeeSorted = employees.stream()
//                .sorted(Comparator.comparing(Employee::getDepartment)
//                        .thenComparing(Employee::getSalary, Comparator.reverseOrder())
//                        .thenComparing(Employee::getName)).toList();
////        employeeSorted.forEach(e->{
////            System.out.println(e.name + " "+ e.salary);
////        });
//
////        Q4
//        Employee employee = employees.stream()
//                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                        .reversed())
//                .skip(1).findFirst().get();
////        System.out.println(employee.getName()+" "+employee.getSalary());
//
////        Q5
//        Map<String, Long> empFreq = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
////        System.out.println(empFreq);
//
////        Q6
//        Employee emp = employees.stream()
//                .filter(employee1 -> employee1.getDepartment().equals("IT"))
//                .min(Comparator.comparingInt(Employee::getAge)).get();
////        System.out.println(emp.getName()+" "+emp.getAge());
//
//
        Map<Employee, List<String>> employeeDepartments = new HashMap<>();
        employeeDepartments.put(employees.get(0), Arrays.asList("IT", "Finance"));   // Alice
        employeeDepartments.put(employees.get(1), Arrays.asList("HR"));             // Bob
        employeeDepartments.put(employees.get(2), Arrays.asList("Finance", "Sales"));// Charlie
        employeeDepartments.put(employees.get(3), Arrays.asList("IT"));             // Diana
        employeeDepartments.put(employees.get(4), Arrays.asList("Sales", "IT"));
//
////        Q7
//        List<String> list = employeeDepartments.entrySet().stream()
//                .filter(entry -> entry.getValue().size() > 1)
//                .map(entry -> entry.getKey().getName())
//                .toList();
////        System.out.println(list);
//
////        Q8
//        List<Employee> employees1 = employees.stream()
//                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                        .reversed())
//                .limit(3)
//                .toList();
//
////        employees1.forEach(e->{
//////            System.out.println(e.getName()+ " "+ e.getSalary());
////        });
//
////        Q9
//        Map<Boolean, List<Employee>> results = employees.stream()
//                .collect(Collectors.partitioningBy(e -> e.getSalary() > 50000));
////        System.out.println(results.get(true).get(0).getName());
//
//
////        Q10
//        Map.Entry<String, Long> stringLongEntry = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .max(Comparator.comparingLong(Map.Entry::getValue))
//                .orElse(null);
////        System.out.println(stringLongEntry);
//
////        Q11
//        Map<String, Double> collect1 = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingInt(Employee::getAge)));
////        System.out.println(collect1);
//
////        Q12
//        List<Employee> employees2 = employees.stream()
//                .filter(e -> {
//                    char firstChar = Character.toUpperCase(e.getName().charAt(0));
//                    return (firstChar == 'A' || firstChar == 'E' || firstChar == 'I' || firstChar == 'O' || firstChar == 'U') && e.getSalary() > 60000;
//                }).toList();
////        employees2.forEach(e->{
////            System.out.println(e.getName()+ " "+e.getSalary());
////        });
////        q13
//        String collect2 = employees.stream()
//                .map(Employee::getName)
//                .collect(Collectors.joining(","));
////        System.out.println(collect2);
//
////        Q14
//        int N =3;
//        Employee employee1 = employees.stream()
//                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                        .reversed())
//                .skip(N - 1)
//                .findFirst()
//                .orElse(null);
////        System.out.println(employee1.getName());
//
////        Q15
//        boolean res = employees.stream()
//                .filter(e -> e.getDepartment().equals("HR"))
//                .allMatch(e -> e.getSalary() > 40000);
////        System.out.println(res);
//
////        Q16
//        Employee employee2 = employees.stream()
//                .filter(e -> e.getDepartment().equals("IT"))
//                .findAny()
//                .orElse(null);
////        System.out.println(employee2.getName());
////        Q17
//        Map<String, Map<String, List<Employee>>> collect3 = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getGender,
//                        Collectors.groupingBy(Employee::getDepartment)));
//
//
//        double totalSum = employees.stream()
//                .mapToDouble(Employee::getSalary)
//                .sum();
////        System.out.println(totalSum);
//
////        Q19
//        employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                Collectors.maxBy(Comparator.comparingInt(Employee::getAge))));
//
////        Q20
//        double avgSal = employees.stream()
//                .mapToDouble(Employee::getSalary)
//                .average()
//                .orElse(0);
//        List<Employee> employees3 = employees.stream()
//                .filter(e -> e.getSalary() > avgSal)
//                .toList();
//        System.out.println(employees3);

//        Q1
//        Employee employee = employees.stream()
//                .sorted(Comparator.comparing(Employee::getAge))
//                .skip(1)
//                .findFirst()
//                .orElse(null);
//        System.out.println(employee.getName());

//        Q2
//        List<Employee> employeeList = employees.stream()
//                .filter(e -> e.getName().endsWith("n") && e.getSalary() > 70000)
//                .toList();
//        employeeList.forEach(
//                e->{
//                    System.out.println(e.getName()+" "+e.getSalary());
//                }
//        );

//        Q3
//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.summingDouble(Employee::getSalary)));
//        System.out.println(collect);

//        Q4
//        List<String> list = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment))
//                .entrySet()
//                .stream()
//                .filter(entry -> entry.getValue().size() >= 3)
//                .map(entry->entry.getKey())
//                .toList();
//        System.out.println(list);

//        Q5
//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(e -> {
//                    if (e.getAge() >= 20 && e.getAge() <= 30) return "20-30";
//                    else if (e.getAge() >= 31 && e.getAge() <= 40) return "31-40";
//                    else return "41+";
//                }));

//        Employee employee = employees.stream()
//                .max(Comparator.comparingInt(e->e.getName().length()))
//                .orElse(null);
//        System.out.println(employee.getName());

//        List<Employee> employees1 = employees.stream()
//                .filter(e -> e.getName().chars()
//                        .filter(ch -> ch == 'a' || ch == 'A').count() > 1)
//                .toList();
//        System.out.println(employees1);

//        Map<String, Double> male = employees.stream()
//                .filter(e -> e.getGender().equals("Male"))
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)));
//        System.out.println(male);

//        Map<Boolean, List<Employee>> collect = employees.stream()
//                .collect(Collectors.partitioningBy(e -> e.getSalary() > 75000));

//        List<Employee> employees1 = employees.stream()
//                .sorted(Comparator.comparing(Employee::getGender)
//                        .thenComparing(Employee::getName))
//                .toList();


//        Map<String, Long> collect = employees.stream()
//                .collect(Collectors.groupingBy(e -> {
//                    if (e.getSalary() < 50000) return "50K";
//                    else if (e.getSalary() >= 50000 && e.getSalary() <= 70000) return "50K-70K";
//                    else return "70k-90k";
//                }, Collectors.counting()));
//        System.out.println(collect);

//        employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                Collectors.minBy(Comparator.comparingInt(Employee::getAge))));

//        List<String> list = employees.stream()
//                .map(e -> e.getName().toUpperCase())
//                .sorted(Comparator.reverseOrder())
//                .toList();
//        System.out.println(list);

//        boolean res = employees.stream()
//                .filter(e -> e.getDepartment().equals("IT"))
//                .allMatch(e -> e.getSalary() < 40000);
//        System.out.println(res);

//        double sum = employees.stream()
//                .filter(e -> e.getGender().equals("Male") && e.getDepartment().equals("Finance"))
//                .mapToDouble(Employee::getSalary)
//                .sum();
//        System.out.println(sum);

//        List<Employee> employees1 = employees.stream()
//                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
//                .limit(2)
//                .toList();
//        System.out.println(employees1);

//        Map<String, Map<String, Long>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.groupingBy(Employee::getGender, Collectors.counting())));

//        List<Employee> employees1 = employees.stream()
//                .filter(e -> ispalindrome(e.getName()))
//                .toList();
////        employees1.forEach(e->{
//            System.out.println(e.getName());
//        });

//        String sales = employees.stream()
//                .filter(e -> e.getDepartment().equals("Sales"))
//                .map(Employee::getName)
//                .collect(Collectors.joining(","));
//        System.out.println(sales);
//
//        double maxSal = employees.stream()
//                .mapToDouble(Employee::getSalary)
//                .max()
//                .orElse(0);
//        List<Employee> employees1 = employees.stream()
//                .filter(e -> e.getSalary() == maxSal)
//                .toList();


//        Map<String, Optional<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
//
//        collect.forEach((a,b)->{
//            System.out.println(a+ " "+ b.get().getSalary());
//        });

//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                                        .reversed())
//                                .limit(3)
//                                .toList())));
//        collect.forEach((a,b)->{
//            System.out.println(a);
//            for (int i=0;i<b.size();i++){
//                System.out.print(b.get(i).getSalary());
//            }
//        });

//        Map<String, Double> collect = employees.stream()
//                .filter(emp->emp.getSalary()>50000)
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)));
//        System.out.println(collect);

//        Map<String, Map<String, Double>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.summarizingDouble(Employee::getSalary),
//                                stats -> Map.of("min", stats.getMin(),
//                                        "max", stats.getMax(),
//                                        "avg", stats.getAverage()))
//                ));

//        List<Employee> employees1 = employees.stream()
//                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                        .reversed())
//                .limit(5)
//                .toList();
//        employees1.forEach((a)-> System.out.println(a.getName()+ " "+a.getSalary()));

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)));
//
//        List<Employee> employees1 = employees.stream()
//                .filter(emp -> emp.getSalary() > collect.get(emp.getDepartment()))
//                .toList();
//        employees1.forEach(e->{
//            System.out.println(e.getName() + " " +e.getSalary());
//        });

//        Map<String, Map<String, Long>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.groupingBy(emp -> {
//                            if (emp.getSalary() < 50000) return "low";
//                            else if (emp.getSalary() > 50000 && emp.getSalary() < 100000) return "Medium";
//                            else return "high";
//                        }, Collectors.counting())));

//        Map<Double, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getSalary,
//                        Collectors.averagingDouble(Employee::getSalary)));

//        Map<String, Map<Double, Long>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.groupingBy(Employee::getSalary,
//                                Collectors.counting())));
//        List<String> employees1 = employees.stream()
//                .filter(emp -> emp.getDepartment().equals("IT") && emp.getSalary() > 50000)
//                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                .map(employee -> employee.getName())
//                .toList();

//        Map<Boolean, Double> collect = employees.stream()
//                .collect(Collectors.partitioningBy(employee -> employee.getSalary() > 50000,
//                        Collectors.summingDouble(Employee::getSalary)));

//        List<String> list = employees.stream()
//                .filter(emp -> emp.getDepartment().equals("IT"))
//                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                .limit(3)
//                .map(Employee::getName)
//                .toList();
//        System.out.println(list);

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingDouble(Employee::getSalary)));

//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
//                                Optional::get)));

//        double sum = employees.stream()
//                .filter(emp -> emp.getAge() > 30)
//                .mapToDouble(Employee::getSalary)
//                .sum();
//        System.out.println(sum);

//        Map<Boolean, List<Employee>> collect = employees.stream()
//                .collect(Collectors.partitioningBy(emp -> emp.getSalary() > 50000));


//        Map<String, List<String>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                                .map(Employee::getName)
//                                .toList())));
//        System.out.println(collect);

//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                                .limit(2)
//                                .findFirst()
//                                .orElse(null))));

//        List<String> list = employees.stream()
//                .sorted(Comparator.comparing(Employee::getDepartment)
//                        .thenComparingDouble(Employee::getSalary)
//                        .reversed())
//                .map(Employee::getName)
//                .toList();
//        System.out.println(list);
//
//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> {
//                            int minAge = list.stream()
//                                    .mapToInt(Employee::getAge)
//                                    .min()
//                                    .orElse(0);
//                            return list.stream().filter(emp -> emp.getAge() == minAge)
//                                    .mapToDouble(Employee::getSalary)
//                                    .average()
//                                    .orElse(0.0);
//                        })
//                ));
//        System.out.println(collect);

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> {
//                            int maxAge = list.stream()
//                                    .mapToInt(Employee::getAge)
//                                    .max()
//                                    .orElse(0);
//                            return list.stream()
//                                    .filter(employee -> employee.getAge() == maxAge)
//                                    .mapToDouble(Employee::getSalary)
//                                    .max()
//                                    .orElse(0.0);
//                        })));
//        System.out.println(collect);

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> {
//                            return list.stream()
//                                    .mapToDouble(Employee::getSalary)
//                                    .min()
//                                    .orElse(0.0);
//                        })));
//        System.out.println(collect);

//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                                        .reversed())
//                                .limit(2)
//                                .toList())));

//        double v = employees.stream()
//                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                .limit(3)
//                .mapToDouble(Employee::getSalary)
//                .average()
//                .orElse(0.0);
//        System.out.println(v);
//
//        Map<String, Optional<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(emp -> {
//                    if (emp.getAge() <= 30) return "30_and_below";
//                    else return "above_30";
//                }, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                        .max(Comparator.comparingDouble(Employee::getSalary)))));

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .mapToDouble(Employee::getSalary)
//                                .sum())));
//        System.out.println(collect);


//        Employee employee = employees.stream()
//                .sorted(Comparator.comparingDouble(Employee::getSalary))
//                .skip(1)
//                .findFirst()
//                .orElse(null);

//        double it = employees.stream()
//                .filter(e -> e.getDepartment().equals("IT") && e.getAge() > 30)
//                .mapToDouble(Employee::getSalary)
//                .average()
//                .orElse(0.0);

//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                                        .reversed())
//                                .limit(3)
//                                .toList())));

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.averagingInt(Employee::getAge)));

//        double v = employees.stream()
//                .mapToDouble(Employee::getSalary)
//                .max()
//                .orElse(0.0);

//        Employee employee = employees.stream()
//                .max(Comparator.comparingDouble(Employee::getSalary))
//                .orElse(null);

//        Map<Boolean, List<Employee>> collect = employees.stream()
//                .collect(Collectors.partitioningBy(emp -> emp.getSalary() > 50000));

//        Map<String, Long> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.counting()));

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.summingDouble(Employee::getSalary)));

//        Map<String, List<String>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                                        .reversed())
//                                .map(Employee::getName)
//                                .toList())));

//        Map<String, Employee> collect = employees.stream()
////                .collect(Collectors.groupingBy(Employee::getDepartment,
////                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
////                                .sorted(Comparator.comparingDouble(Employee::getSalary)
////                                        .reversed())
////                                .skip(1)
////                                .findFirst()
////                                .orElse(null))));

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> {
//                            int minAge = list.stream()
//                                    .mapToInt(Employee::getAge)
//                                    .min()
//                                    .orElse(0);
//                            return list.stream()
//                                    .filter(emp -> emp.getAge() == minAge)
//                                    .mapToDouble(Employee::getSalary)
//                                    .average()
//                                    .orElse(0.0);
//                        })));

//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> {
//                            double minSal = list.stream()
//                                    .mapToDouble(Employee::getSalary)
//                                    .min()
//                                    .orElse(0.0);
//                            return list.stream().filter(emp -> emp.getSalary() == minSal).toList();
//                        })));

//        double v = employees.stream()
//                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                .limit(3)
//                .mapToDouble(Employee::getSalary)
//                .average()
//                .orElse(0.0);

//        Map<String, Optional<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(emp -> {
//                    if (emp.getAge() <= 30) return "30_below";
//                    else return "30_above";
//                }, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

//        Optional<Map.Entry<String, Double>> max = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.summingDouble(Employee::getSalary)))
//                .entrySet()
//                .stream()
//                .max(Map.Entry.comparingByValue());

//        Map<String, List<Employee>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary)
//                                        .reversed())
//                                .limit(2)
//                                .toList())));

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .filter(emp -> emp.getAge() > 30)
//                                .mapToDouble(Employee::getSalary)
//                                .average()
//                                .orElse(0.0))));

//        Stream<Employee> sorted = employees.stream()
//                .sorted(Comparator.comparing(Employee::getDepartment)
//                        .thenComparingDouble(Employee::getSalary)
//                        .reversed());

//        Optional<Map.Entry<String, Long>> max = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.counting()))
//                .entrySet()
//                .stream()
//                .max(Map.Entry.comparingByValue());

//        Map<String, Map<String, Double>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.groupingBy(emp -> {
//                            if (emp.getAge() <= 30) return "Young";
//                            else if (emp.getAge() > 30) return "Experienced";
//                            else return null;
//                        }, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .mapToDouble(Employee::getSalary)
//                                .average()
//                                .orElse(0.0)))));

//        Map<String, Map<String, Double>> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.summarizingDouble(Employee::getSalary),
//                                stats -> Map.of("min", stats.getMin(),
//                                        "max", stats.getMax(),
//                                        "avg", stats.getAverage(),
//                                        "total", stats.getSum()))));

//        Map<String, Double> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .mapToDouble(Employee::getSalary)
//                                .average()
//                                .orElse(0.0))));
//        List<Employee> employees1 = employees.stream()
//                .filter(emp -> emp.getSalary() > collect.get(emp.getDepartment()))
//                .toList();

//        Map<String, Employee> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.collectingAndThen(Collectors.toList(), list -> list.stream()
//                                .sorted(Comparator.comparingDouble(Employee::getSalary))
//                                .skip(1)
//                                .findFirst()
//                                .orElse(null))));

//        List<String> list = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.summingDouble(Employee::getSalary)))
//                .entrySet()
//                .stream()
//                .sorted(Map.Entry.<String,Double>comparingByValue().reversed())
//                .limit(3)
//                .map(Map.Entry::getKey)
//                .toList();

//        Map<Double, Long> collect = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getSalary,
//                        Collectors.counting()));
//        List<Employee> employees1 = employees.stream()
//                .filter(emp -> collect.get(emp.getSalary()) > 1)
//                .toList();

    }


    private static boolean ispalindrome(String name) {
        int low =0;
        int high =name.length()-1;
        while(low<high){
            if(name.charAt(low)!=name.charAt(high)){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

}
