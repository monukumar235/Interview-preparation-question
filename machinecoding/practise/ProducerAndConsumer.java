package machinecoding.practise;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerAndConsumer {
    public static class Buffer{
        int capacity;
        Queue<Integer> queue = new LinkedList<>();
        Buffer(int capacity){
            this.capacity=capacity;
        }
        public synchronized  void producer(int item) throws InterruptedException {
            while (!queue.isEmpty()) wait();
            queue.add(item);
            System.out.println("Produced "+item);
            notifyAll();
        }
        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) wait();
            Integer val = queue.poll();
            System.out.println("Consumed "+val);
            notifyAll();
            return val;
        }
    }
    public static class Producer extends Thread{
       public Buffer buffer ;

       Producer(Buffer buffer){
           this.buffer=buffer;
       }

        @Override
        public void run() {
            int item=0;
            while (true){
                try{
                    buffer.producer(item++);
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    public static class Consumer extends Thread{
        public Buffer buffer;
        Consumer(Buffer buffer){
            this.buffer =buffer;
        }

        @Override
        public void run() {
            while (true){
                try{
                    int item = buffer.consume();
                    System.out.println("Consumed "+item);
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    public static void main(String[] args) {
        Buffer buffer = new Buffer(3);

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        producer.start();
        consumer.start();
    }
}
