package multithreading;

public class DeadLockPrac {
    public  static final Object lock1 = new Object();
    public  static final Object lock2 = new Object();
    public static void main(String[] args) {

         Runnable task = new Runnable() {
             @Override
             public void run() {
                 synchronized (lock1){
                     System.out.println(Thread.currentThread().getName()+" accuired lock-1");
                     try {
                         Thread.sleep(5000);
                     } catch (InterruptedException e) {
                         Thread.currentThread().interrupt();                     }
                 }
                 System.out.println(Thread.currentThread().getName()+ " trying to accuire lock-2");
                 synchronized (lock2){
                     System.out.println(Thread.currentThread().getName()+ "accuired lock-2");

                 }
             }
         };

         Runnable task2 = new Runnable() {
             @Override
             public void run() {
                 synchronized (lock2){
                     System.out.println(Thread.currentThread().getName()+ "accuried lock-2");
                     try {
                         Thread.sleep(1000);
                     } catch (InterruptedException e) {
                         Thread.currentThread().interrupt();
                     }
                 }
                 System.out.println(Thread.currentThread().getName()+" trying to accuires lock-1");
                 synchronized (lock1){
                     System.out.println(Thread.currentThread().getName() + " accuired lock-1");
                 }
             }
         };

         Thread t1 = new Thread(task,"t1");
         Thread t2 = new Thread(task2,"t2");

         t1.start();
         t2.start();
    }
}
