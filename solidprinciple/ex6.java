package solidprinciple;

public class ex6 {
    public interface Vehicle{
        void start();
    }
    public static class Car implements Vehicle{

        @Override
        public void start() {
            System.out.println("Car is moving.....");
        }
    }

    public static class Bike implements  Vehicle{

        @Override
        public void start() {
            System.out.println("Bike is moving....");
        }
    }

    public static class VehicleFactory{
        public Vehicle createVehicle(String type){
            if(type ==null && type.isEmpty()) return null;
            switch (type.toLowerCase()){
                case "car":
                    return new Car();
                case "bike" :
                    return new Bike();
                default:
                    throw new IllegalArgumentException("Unknown Type " + type);
            }
        }
    }

    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle car = vehicleFactory.createVehicle("car");
        Vehicle bike = vehicleFactory.createVehicle("bike");
        car.start();
        bike.start();
//        vehicleFactory.createVehicle("cycle");

    }
}
