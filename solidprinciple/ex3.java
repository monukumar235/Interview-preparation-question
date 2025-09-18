package solidprinciple;

public class ex3 {
    public interface  Printer{
        void print();
    }
    public interface Scanner{
        void scan();
    }
    public static class  OldMachine implements Printer{

        @Override
        public void print() {
            System.out.println("Old Machine can only print...");
        }
    }
    public static class NewMachine implements Printer,Scanner{

        @Override
        public void print() {
            System.out.println("New Machine can Print.....");
        }

        @Override
        public void scan() {
            System.out.println("New Machine can Scan.....");
        }
    }
    public static void main(String[] args) {
        Printer p1 = new OldMachine();
        p1.print();
        NewMachine p2 = new NewMachine();
        p2.print();
        p2.scan();
    }
}
