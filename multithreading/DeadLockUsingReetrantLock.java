package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockUsingReetrantLock {

    public static final Lock lock1 = new ReentrantLock();
    public static final Lock lock2 = new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            lock1.lock();
            System.out.println("Thread-1 accuired lock-1");
            try {
                Thread.sleep(1000);
                lock2.lock();
                System.out.println("Thread-1 accuried lock-2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock2.unlock();
                lock1.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock2.lock();
            System.out.println("Thread-2 accuries lock-2");
            try {
                Thread.sleep(1000);
                lock1.lock();
                System.out.println("Thread-2 accuries lock-1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        });
        t1.start();
        t2.start();
    }
}
