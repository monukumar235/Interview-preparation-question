package multithreading;

public class EvenOdd {
    public static class PrintEvenOdd{
        boolean isOdd = true;
        public synchronized void printOdd(int n) throws InterruptedException {
            while (!isOdd) wait();
            System.out.println("Odd"+" "+n);
            isOdd=false;
            notifyAll();
        }

        public synchronized  void printEven(int n) throws InterruptedException {
            while (isOdd) wait();
            System.out.println("Even"+" "+n);
            isOdd=true;
            notifyAll();
        }
    }
    public static void main(String[] args) {
        final int limit =10;
        PrintEvenOdd printEvenOdd = new PrintEvenOdd();

        Thread one = new Thread(()->{
            for (int i=1;i<limit;i=i+2){
                try {
                    printEvenOdd.printOdd(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 2; i < limit; i = i + 2) {
                try {
                    printEvenOdd.printEven(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        one.start();
        second.start();

    }
}
