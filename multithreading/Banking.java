package multithreading;

import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banking {
    int balance =100;
    Lock lock = new ReentrantLock();
    public  void withdrawn(int amount){
        System.out.println(Thread.currentThread().getName()+ " is attempting to withdraw amount "+ amount);
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                if(balance>=amount){
                    System.out.println(Thread.currentThread().getName()+ " is withdrawing amount "+amount);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    finally {
                        lock.unlock();
                    }
                    balance-=amount;
                    System.out.println(Thread.currentThread().getName() + " remaining balance is "+balance);
                }
            }
            else{
                System.out.println(Thread.currentThread().getName() + " Unable to accuire lock!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Banking banking = new Banking();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                banking.withdrawn(50);
            }
        };
        Thread t1 = new Thread(task,"t1");
        Thread t2 = new Thread(task,"t2");
        t2.start();
        t1.start();
    }
}
