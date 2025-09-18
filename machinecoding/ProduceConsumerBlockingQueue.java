package machinecoding;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProduceConsumerBlockingQueue {
    public static class MessageQueue{
        private final BlockingQueue<Integer> queue;
        MessageQueue(int cap){
            this.queue = new ArrayBlockingQueue<>(cap);
        }
        public void produce(int val) throws InterruptedException {
            queue.put(val);
            System.out.println("Produced "+ val);
        }

        public int consume() throws InterruptedException {
            Integer take = queue.take();
            System.out.println("Consumed "+take);
            return take;
        }
    }

    public static class Producer extends Thread{
        private final MessageQueue messageQueue;
        Producer(MessageQueue messageQueue){
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            int val=0;
            try {
                while (true){
                    messageQueue.produce(val++);
                    Thread.sleep(500);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }

        }
    }

    public static class Consumer extends Thread{
        private final MessageQueue messageQueue;
        Consumer(MessageQueue messageQueue){
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            try{
                while (true){
                    messageQueue.consume();
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);
        Producer producer = new Producer(messageQueue);
        Consumer consumer = new Consumer(messageQueue);

        producer.start();
        consumer.start();
    }
}
