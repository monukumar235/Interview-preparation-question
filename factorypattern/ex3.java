package factorypattern;

public class ex3 {
    public interface Vehicle{
        void display();
    }

    public static class Car implements Vehicle{

        @Override
        public void display() {
            System.out.println("Car class...");
        }
    }
    public static class Bike implements Vehicle{

        @Override
        public void display() {
            System.out.println("Bike class...");
        }
    }

    public static class Truck implements Vehicle{

        @Override
        public void display() {
            System.out.println("Truck class");
        }
    }
    public static class VehicleFactory{
        public Vehicle getInstance(String type,int speed){
            if(type==null || type.isEmpty()) return null;
            if(type.equalsIgnoreCase("car")){
                return new Car();
            }
            else if(type.equalsIgnoreCase("bike")){
                return new Bike();
            }
            else if(type.equalsIgnoreCase("truck")){
                return  new Truck();
            }
            else{
                if(speed==20){
                    return new Truck();
                }
                if(speed == 40){
                    return new Car();
                }
                if (speed ==70)
                {
                    return new Bike();
                }
            }
            return null;
        }
    }
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle car = vehicleFactory.getInstance("car", 60);
        if(car!=null)
        {
            car.display();
        }
        Vehicle bike = vehicleFactory.getInstance("bike", 80);
        if(bike!=null)
        {
            bike.display();
        }
    }
}
