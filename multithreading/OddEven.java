package multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEven {
    static class PrintOddEven{
        int n=1;
        int max =10;

        public synchronized void printOdd() throws InterruptedException {
            while (n<=max){
                while (n%2!=1) wait();
                System.out.println(Thread.currentThread().getName() + " "+ n);
                n++;
                notifyAll();
            }
        }

        public synchronized void printEven() throws InterruptedException {
          while (n<=max){
              while (n%2!=0) wait();
              System.out.println(Thread.currentThread().getName()+ " "+ n);
              n++;
              notifyAll();
          }
        }
    }
    public static void main(String[] args) {
        PrintOddEven printOddEven = new PrintOddEven();
        Thread odd = new Thread(()->{
            try {
                printOddEven.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"odd");

        Thread even = new Thread(()->{
            try {
                printOddEven.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"even");

        odd.start();
        even.start();
    }

}
