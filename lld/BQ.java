package lld;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BQ {
    public static class BlockingQueu{
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);

        public void produce(int item) throws InterruptedException {
            blockingQueue.put(item);
            System.out.println("Produced "+Thread.currentThread().getName()+" "+item);
        }
        public void comsume() throws InterruptedException {
            Integer item = blockingQueue.take();
            System.out.println("comsumed "+Thread.currentThread().getName()+" "+item);
        }

    }
    public static void main(String[] args) {
        BlockingQueu bq = new BlockingQueu();
        Thread task = new Thread(()->{
            for (int i=0;i<10;i++)
            {
                try {
                    bq.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"task-1");

        Thread task1 = new Thread(()->{
            for (int i=0;i<10;i++)
            {
                try {
                    bq.comsume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"task-2");
        task.start();
        task1.start();
    }
}
