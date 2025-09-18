package MachineLevelCode.RateLimiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerAndConsumerUsingBlockingQueue {
    public static class ProducerQA extends Thread {
        public final BlockingQueue<Integer> queue;

        public ProducerQA(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            int value = 0;
            while (true) {
                try {
                    queue.put(value);
                    System.out.println("Produced" + " " + value++);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

        public static class ComsumerQA extends Thread{
            public final BlockingQueue<Integer> queue;
            public ComsumerQA(BlockingQueue<Integer> queue) {
                this.queue = queue;
            }
            @Override
            public void run() {
                while (true){
                    Integer val = null;
                    try {
                        val = queue.take();
                        System.out.println("comsumed"+ " "+val);
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        new ProducerQA(queue).start();
        new ComsumerQA(queue).start();
    }
}
