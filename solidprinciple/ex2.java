package solidprinciple;

public class ex2 {
    public interface  Engine{
        void start();
    }
    public static class PetrolEngine implements Engine{

        @Override
        public void start() {
            System.out.println("Petrol Engine Started......");
        }
    }

    public static class ElectricEngine implements Engine{

        @Override
        public void start() {
            System.out.println("Electric Engine started.......");
        }
    }

    public static class JetEngine implements Engine{

        @Override
        public void start() {
            System.out.println("Jet Engine started.....");
        }
    }

    public static class   Vehicle{
        private Engine engine;

        public Vehicle(Engine engine) {
            this.engine = engine;
        }
        public void move(){
            engine.start();
            System.out.println("vehicle is moving!");
        }
    }
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle(new ElectricEngine());
        v1.move();

        Vehicle v2 = new Vehicle(new PetrolEngine());
        v2.move();

        Vehicle v3 = new Vehicle(new JetEngine());
        v3.move();
    }
}
