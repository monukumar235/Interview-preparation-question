package lld;

import multithreading.ProduceAndConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerAndConsumer {
    public static class ProducerConsumer{
        public int capacity=5;
        public Queue<Integer> queue = new LinkedList<>();
        public synchronized void producer(int item) throws InterruptedException {
            while (!queue.isEmpty()) wait();
            queue.add(item);
            System.out.println("produced "+item);
            notifyAll();
        }

        public synchronized void consume() throws InterruptedException {
            while (queue.isEmpty()) wait();
            Integer item = queue.poll();
            System.out.println("consumed "+item);
            notifyAll();
        }
    }
    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Thread thread = new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    producerConsumer.producer(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread1 = new Thread(()->{
            for (int i=0;i<10;i++){
                try {
                    producerConsumer.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        thread1.start();
    }
}
