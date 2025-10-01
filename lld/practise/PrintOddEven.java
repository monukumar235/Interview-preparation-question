package lld.practise;

public class PrintOddEven {
    static class PrintOddAndEven
    {
        int n=1;
        int max =10;
        public synchronized void printEven() throws InterruptedException {
            while (n<=max)
            {
                while (n%2!=0) wait();
                System.out.println(Thread.currentThread().getName()+" " +n);
                n++;
                notifyAll();
            }
        }
        public synchronized void printOdd() throws InterruptedException {
            while (n<=max)
            {
                while (n%2!=1) wait();
                System.out.println(Thread.currentThread().getName()+" " +n);
                n++;
                notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        PrintOddAndEven printOddAndEven = new PrintOddAndEven();
        Thread even = new Thread(()->{
            try {
                printOddAndEven.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"even");

        Thread odd = new Thread(()->{
            try {
                printOddAndEven.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"odd");

        even.start();
        odd.start();
    }
}
