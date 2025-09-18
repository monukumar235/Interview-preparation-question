import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Employees {
    public static class Employee{
        public String name;
        public int salary;

        public Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }
    }
    public static void main(String[] args) {

        List<Employee> lists = Arrays.asList(new Employee("Monu",10000),
                new Employee("rahul",100),
                new Employee("Monu",1000));

//        lists.forEach(e->{
//            System.out.println("name"+" "+e.name + " " +"salary" + " " +e.salary);
//

        List<Employee> collect = lists.stream().sorted(Comparator.comparing(emp->emp.name,Comparator.reverseOrder())).collect(Collectors.toList());
//        for(Employee c : collect){
//            System.out.println(c.name);
//        }

        List<Employee> sortedByNameThenSal = lists.stream()
                .sorted(Comparator.comparing((Employee e) -> e.name)
                        .thenComparingInt((Employee e) -> e.salary)).collect(Collectors.toList());

        for(Employee emp : sortedByNameThenSal){
            System.out.println(emp.name + " "+ emp.salary);
        }
    }
}
