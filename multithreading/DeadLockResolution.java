package multithreading;

public class DeadLockResolution {
    public static final Object lock1= new Object();
    public static final Object lock2 =new Object();
    public static void main(String[] args) {
        Runnable task1=()->accuriesLock(lock1,lock2);
        Runnable task2 =()->accuriesLock(lock1,lock2);

        new Thread(task1,"Thread-1").start();
        new Thread(task2,"Thread-2").start();
    }

    private static void accuriesLock(Object lock1, Object lock2) {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+" "+"accuries lock-1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName()+" "+"accuries lock-2");
            }
        }

    }
}
