package multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueWithMultipleConAndProd {
    static class MultipleProdAndCon{
        BlockingQueue<Integer> queue;
        MultipleProdAndCon(BlockingQueue<Integer> queue)
        {
            this.queue = queue;
        }
        public void producer(int item) throws InterruptedException {
            queue.put(item);
            System.out.println(Thread.currentThread().getName() + "produced "+item);
            Thread.sleep(500);
        }
        public void consume() throws InterruptedException {
            Integer item = queue.take();
            System.out.println(Thread.currentThread().getName()+" consumed"+ item);
            Thread.sleep(800);
        }
    }
    public static void main(String[] args) {
        BlockingQueue<Integer > queue = new ArrayBlockingQueue<>(5);
        MultipleProdAndCon multipleProdAndCon = new MultipleProdAndCon(queue);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    try {
                        multipleProdAndCon.producer(i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<7;i++){
                    try {
                        multipleProdAndCon.consume();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread t1 = new Thread(task,"producer-1");
        Thread t2 = new Thread(task,"producer-2");
        Thread c1 = new Thread(task2,"consumer-1");
        Thread c2 = new Thread(task2,"consumer-2");
        Thread c3 = new Thread(task2,"consumer-3");

        t1.start();
        t2.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
