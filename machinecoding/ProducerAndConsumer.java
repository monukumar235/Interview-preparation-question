package machinecoding;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerAndConsumer {
    public  static class Buffer{
        int cap;
        Queue<Integer> queue = new LinkedList<>();

        public Buffer(int cap) {
            this.cap = cap;
        }

        public synchronized void produce(int item) throws InterruptedException {
            while (!queue.isEmpty())
            {
                wait();
            }
            queue.add(item);
            System.out.println("Produced "+ item);
            notifyAll();
        }

        public synchronized  int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            int item = queue.poll();
            System.out.println("Consumed "+ item);
            notifyAll();
            return item;
        }
    }
    static class Producer extends Thread{
        private final Buffer buffer;
        Producer(Buffer buffer) {
            this.buffer = buffer;
        }
        @Override
        public void run() {
         int val=0;
         while (true){
             try {
                 buffer.produce(val++);
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
             }
         }
        }
    }

    static class Consumer extends Thread{
        private final  Buffer buffer;
        Consumer(Buffer buffer){
            this.buffer=buffer;
        }

        @Override
        public void run() {
            while (true){
                try {
                    buffer.consume();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        producer.start();
        consumer.start();
    }
}
