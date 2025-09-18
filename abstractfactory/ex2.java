package abstractfactory;

public class ex2 {
    public interface  Bike{
        void design();
    }
    public interface Car{
        void design();
    }
    public static class HondaBike implements Bike{

        @Override
        public void design() {
            System.out.println("Honda Bike...");
        }
    }

    public static class HondaCar implements  Car{

        @Override
        public void design() {
            System.out.println("Honda Car....");
        }
    }
    public static class BMWCar implements Car{

        @Override
        public void design() {
            System.out.println("BMW car....");
        }
    }
    public static class BMWBike implements Bike{

        @Override
        public void design() {
            System.out.println("BMW Bike.....");
        }
    }

    public interface BrandFactory{
        Car createCar();
        Bike createBike();
    }

    public static class HondaFactory implements BrandFactory{

        @Override
        public Car createCar() {
            return new HondaCar();
        }

        @Override
        public Bike createBike() {
            return new HondaBike();
        }
    }

    public static class BMWFactory implements BrandFactory{

        @Override
        public Car createCar() {
            return new BMWCar();
        }

        @Override
        public Bike createBike() {
            return new BMWBike();
        }
    }

    public static void main(String[] args) {
        BrandFactory honda =new HondaFactory();

        Bike bike = honda.createBike();
        bike.design();

        Car car = honda.createCar();
        car.design();


    }
}
