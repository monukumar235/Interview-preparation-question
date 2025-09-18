package machinecoding.practise;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerAndConsumerMessageQueue {
    public static class MessageQueue{
        int cap;
        BlockingQueue<Integer>  queue;
        MessageQueue(int cap){
            queue = new ArrayBlockingQueue<>(cap);
        }
        public void produce(int item) throws InterruptedException {
            queue.put(item);
            System.out.println("Produced "+item);
        }
        public int consume() throws InterruptedException {
            Integer val = queue.take();
            System.out.println("Consumed "+val);
            return val;
        }
    }
    public static class Producer extends Thread{
        public MessageQueue messageQueue;
        Producer(MessageQueue messageQueue){
            this.messageQueue=messageQueue;
        }

        @Override
        public void run() {
            int item=0;
            while (true){
                try{
                    messageQueue.produce(item++);
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();;
                }
            }
        }
    }
    public static class Consumer extends Thread{
        MessageQueue messageQueue;
        Consumer(MessageQueue messageQueue){
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            while (true){
                try{
                    messageQueue.consume();
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(3);

        Producer producer = new Producer(messageQueue);
        Consumer consumer = new Consumer(messageQueue);

        producer.start();
        consumer.start();

    }
}
