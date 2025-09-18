package multithreading;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadLock {
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    static class DeadLockDetector{
        public static void deadLockDetector(){
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
            if(deadlockedThreads!=null){
                System.out.println("DeadLock Detected");

                ThreadInfo[] threadInfo = threadMXBean.getThreadInfo(deadlockedThreads);
                for (ThreadInfo info : threadInfo){
                    System.out.println(info.getLockName());
                    System.out.println(info.getLockInfo());
                    System.out.println(info.getThreadId());
                }

            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread-1 Accuried lock-1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println(" Thread-1 Accuried lock-2");
                }
            }
        },"Thread-1");

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("THread-2 Accuried lock-2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1) {
                    System.out.println("Thread-2 Accuried lock-1");
                }
            }
        },"Thread-2");

        thread1.start();
        thread2.start();

        Thread.sleep(1000);
        DeadLockDetector.deadLockDetector();
    }
}
