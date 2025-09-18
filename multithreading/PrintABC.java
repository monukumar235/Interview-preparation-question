package multithreading;

public class PrintABC {
    static class Print{
        int turn =0;
        int max =9;
        public synchronized void printA() throws InterruptedException {
           while (turn<=max){
               while (turn%3!=0){
                   wait();
               }
               System.out.println(Thread.currentThread().getName() + " "+ "A");
               turn++;
               notifyAll();
           }
        }

        public synchronized  void printB() throws InterruptedException {
            while (turn<=max){
                while (turn%3!=1)
                {
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + " "+ "B");
                turn++;
                notifyAll();
            }
        }

        public synchronized void printC() throws InterruptedException {
            while (turn<=max){
                while (turn%3!=2)
                {
                    wait();
                }
                System.out.println(Thread.currentThread().getName()+ " "+ "C");
                turn++;
                notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        Print print = new Print();
        Thread printA = new Thread(()->{
            try {
                print.printA();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"ThreadA");

        Thread printB = new Thread(()->{
            try {
                print.printB();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"ThreadB");

        Thread printC = new Thread(()->{
            try {
                print.printC();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"ThreadC");

        printA.start();
        printB.start();
        printC.start();
    }
}
