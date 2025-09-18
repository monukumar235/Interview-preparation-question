package solidprinciple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LIskovPrinciple {
    public static class Vehicle{
        public int NoOfWheels(){
            return 2;
        }
    }

    public static class HasEngine extends Vehicle{
        public Boolean hasEngine(){
            return true;
        }
    }
    public static class Car extends HasEngine {
        @Override
        public int NoOfWheels() {
            return 4;
        }
    }
    public static class Bike extends HasEngine{

    }
    public static class Bycycle extends Vehicle{
    }
    public static void main(String[] args) {
        List<HasEngine> vehicles = new ArrayList<>();
        vehicles.add(new HasEngine());
        vehicles.add(new HasEngine());
//        vehicles.add(new Bycycle());

        for(HasEngine vehicle : vehicles){
            System.out.println(vehicle.hasEngine());
        }
    }
}
