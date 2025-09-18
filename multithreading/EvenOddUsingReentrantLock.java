package multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EvenOddUsingReentrantLock {
    static class EvenOdd{
        int n=1;
        int max;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        EvenOdd(int max){
            this.max = max;
        }
        public void printEven()  {
            lock.lock();
            try{
                while (n<max){
                    while (n%2==1) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " "+n);
                    n++;
                    condition.signalAll();
                }
            }finally {
                lock.unlock();
            }
        }
        public void printOdd()   {
            lock.lock();
            try{
                while (n<max)
                {
                    while (n%2==0) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " "+n);
                    n++;
                    condition.signalAll();
                }

            }finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        EvenOdd evenOdd = new EvenOdd(10);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                evenOdd.printEven();
            }
        };
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                evenOdd.printOdd();
            }
        };
        Thread even = new Thread(task,"even");
        Thread odd = new Thread(task2,"odd");
        even.start();
        odd.start();
    }
}
