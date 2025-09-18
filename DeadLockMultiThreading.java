import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

class DeadLockDetector implements Runnable{
    private final ThreadMXBean mxBean=ManagementFactory.getThreadMXBean();
    @Override
    public void run() {
        while (true){
            long[] deadlockedThreads = mxBean.findDeadlockedThreads();
            if (deadlockedThreads!=null){
                System.err.println("DeadLock found");
                ThreadInfo[] threadInfo = mxBean.getThreadInfo(deadlockedThreads, true, true);
                for(ThreadInfo threadInfos : threadInfo){
                    System.out.println(threadInfos);
                }
                break;
            }
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){

            }
        }
    }
}
public class DeadLockMultiThreading {

    static class Resource{};

    public static final Resource resource1 =new Resource();
    public  static final Resource resource2 =new Resource();
    public static void main(String[] args) {
        Thread t1 = new Thread(()->
        {
            synchronized (resource1){
                System.out.println("Thread 1: Locked Resource 1");
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){

                }
                synchronized (resource2){
                    System.out.println("Thread 1 :Locked resouce 2");
                }
            }
        });

        Thread t2 = new Thread(()->
        {
            synchronized (resource2){
                System.out.println("Thread 2 :Locked resource 2");
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){}
                synchronized (resource1){
                    System.out.println("Thread 2:Locked Resource 1");
                }
            }
        });
        t1.start();
        t2.start();

        new Thread(new DeadLockDetector(),"DeadLockDetector").start();

    }
}
