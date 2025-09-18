package abstractfactory;

public class Vehicle {
    public interface Engine{
        void design();
    }
    public interface Tyre{
        void manufacture();
    }
    public static  class CarEngine implements Engine{

        @Override
        public void design() {
            System.out.println("Car design.....");
        }
    }
    public static class CarTyre implements Tyre{

        @Override
        public void manufacture() {
            System.out.println("Car Tyre.....");
        }
    }
    public static class BikeEngine implements Engine{

        @Override
        public void design() {
            System.out.println("Bike design.......");
        }
    }
    public static class BikeTyre implements Tyre{

        @Override
        public void manufacture() {
            System.out.println("Bike Tyre........");
        }
    }

    public  interface VehicleFatory{
        Engine createEngine();
        Tyre createTrye();
    }

    public static class CarFactory implements VehicleFatory{

        @Override
        public Engine createEngine() {
            return new CarEngine();
        }

        @Override
        public Tyre createTrye() {
            return new CarTyre();
        }
    }
    public static class BikeFactory implements VehicleFatory
    {

        @Override
        public Engine createEngine() {
            return new BikeEngine();
        }
        @Override
        public Tyre createTrye() {
         return new BikeTyre();
        }
    }
    public static void main(String[] args) {

        VehicleFatory car = new CarFactory();

        Engine engine = car.createEngine();
        engine.design();

        Tyre trye = car.createTrye();
        trye.manufacture();


    }
}
