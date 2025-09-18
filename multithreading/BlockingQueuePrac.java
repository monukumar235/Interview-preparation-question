package multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueuePrac {
    static class BlockingQueues{
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        public  void produce(int item) throws InterruptedException {
            queue.put(item);
            System.out.println(Thread.currentThread().getName() + " produced number "+item);
            Thread.sleep(500);
        }
        public  void consume() throws InterruptedException {
            Integer item = queue.take();
            System.out.println(Thread.currentThread().getName()+ "consumed "+item);
            Thread.sleep(800);
        }
    }
    public static void main(String[] args) {
        BlockingQueues queues = new BlockingQueues();
        Thread t1 = new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    queues.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"producer-thread");
        Thread t2 = new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    queues.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"comsumer-thread");
        t1.start();
        t2.start();
    }
}
