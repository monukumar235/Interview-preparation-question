package singleton;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        List<Employee> results = employees.stream()
                .filter(e -> e.getDateOfJoining().isEqual(LocalDate.ofYearDay(2025, 01)))
                .toList();

//        List<Integer> list = Arrays.asList(1, 4, 5, 3, 7, 2, 8);
//        int target = 9;
//
//        Set<Integer> seen = new HashSet<>();

//        List<String> list1 = list.stream()
//                .flatMap(num -> {
//                    int com = target - num;
//                    if (seen.contains(com)) {
//                        return Stream.of("(" + com + "," + num + ")");
//                    }
//                    seen.add(num);
//                    return Stream.empty();
//                })
//                .toList();
//        System.out.println(list1);


//        List<Integer> list = Arrays.asList(2, 4, 6, 3, 7, 1, 5, 9, 8, 11);
//        int target = 10;
//
//        Set<Integer> seen = new HashSet<>();

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


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int target = 15;

        Set<String> collect = list.stream()
                .flatMap(a -> list.stream()
                        .flatMap(b -> list.stream()
                                .map(c -> Arrays.asList(a, b, c))))
                .filter(t -> t.get(0) + t.get(1) + t.get(2) == target)
                .map(t -> {
                    Collections.sort(t);
                    return "(" + t.get(0) + "," + t.get(1) + "," + t.get(2) + ")";
                })
                .collect(Collectors.toSet());
        System.out.println(collect);

    }
    public  class Employee{
        final int employeeId;
        final String employeeName;
       final LocalDate dateOfJoining;



        public int getEmployeeId() {
            return employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public LocalDate getDateOfJoining() {
            return dateOfJoining;
        }

        public Employee(int employeeId, String employeeName, LocalDate dateOfJoining) {
            this.employeeId = employeeId;
            this.employeeName = employeeName;
            this.dateOfJoining = dateOfJoining;
        }
    }
}
