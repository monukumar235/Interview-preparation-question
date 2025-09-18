package multithreading;

public class ABCPrint {
    public static class PrintABC{
        int turn=0;
        public synchronized void printA() throws InterruptedException {
            while (turn%3!=0) wait();
            System.out.println("A");
            turn++;
            notifyAll();
        }

        public synchronized void printB() throws InterruptedException {
            while (turn%3!=1) wait();
            System.out.println("B");
            turn++;
            notifyAll();
        }
        public synchronized void printC() throws InterruptedException {
            while (turn%3!=2) wait();
            System.out.println("C");
            turn++;
            notifyAll();
        }
    }
    public static void main(String[] args) {
        PrintABC print = new PrintABC();
        int size=5;
        Thread thread = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                try {
                    print.printA();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                try {
                    print.printB();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                try {
                    print.printC();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        thread1.start();
        thread2.start();
    }
}
