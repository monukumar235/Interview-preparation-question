package multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockResolutionUsingReetantLOck {
    public static final Lock lock1 = new ReentrantLock();
    public static final Lock lock2 = new ReentrantLock();
    public static void main(String[] args) {
        Runnable task1=()->accuriedLock(lock1,lock2);
        Runnable task2 = ()->accuriedLock(lock1,lock2);

        Thread t1 = new Thread(task1, "Thread-1");
        Thread t2 = new Thread(task2, "Thread-2");
        t1.start();
        t2.start();
    }

    private static void accuriedLock(Lock lock1, Lock lock2) {
        while(true){
            boolean gotFirst=false;
            boolean gotSecond=false;

            try {
                 gotFirst = lock1.tryLock(100, TimeUnit.MILLISECONDS);
                 gotSecond = lock2.tryLock(100,TimeUnit.MILLISECONDS);
                 if(gotFirst && gotSecond){
                     System.out.println(Thread.currentThread().getName()+" "+" accuries both the lock!");
                     break;
                 }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                if(gotFirst) lock1.unlock();
                if(gotSecond)lock2.unlock();
            }
        }
    }
}
