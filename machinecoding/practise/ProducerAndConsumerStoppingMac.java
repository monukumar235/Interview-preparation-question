package machinecoding.practise;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProducerAndConsumerStoppingMac {
    public static class MessageQueue{
        int cap;
        BlockingQueue<Integer> blockingQueue;
        MessageQueue(int cap){
            blockingQueue = new ArrayBlockingQueue<>(cap);
        }
        public void produces(int item) throws InterruptedException {
            blockingQueue.put(item);
            System.out.println("Produced "+item);
        }
        public int consume() throws InterruptedException {
            Integer val = blockingQueue.take();
            System.out.println("Consumed "+val);
            return val;
        }
    }

    public static class Producers extends Thread{
        public MessageQueue messageQueue;
        AtomicBoolean isRunning;
        Producers(MessageQueue messageQueue,AtomicBoolean isRunning){
            this.isRunning = isRunning;
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            int val =0;
            while (isRunning.get()){
                try
                {
                    messageQueue.produces(val++);
                    Thread.sleep(500);
                }catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    public static class Consumer extends Thread{
        public  MessageQueue messageQueue;
        AtomicBoolean isRunning;
        Consumer(MessageQueue messageQueue,AtomicBoolean isRunning){
            this.messageQueue = messageQueue;
            this.isRunning =isRunning;
        }

        @Override
        public void run() {
            while (isRunning.get()){
                try{
                    int consume = messageQueue.consume();
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MessageQueue messageQueue = new MessageQueue(3);
        AtomicBoolean isRunning = new AtomicBoolean(true);

        Producers producers = new Producers(messageQueue,isRunning);
        Consumer consumer = new Consumer(messageQueue,isRunning);

        producers.start();
        consumer.start();

        Thread.sleep(10000);

        isRunning.set(false);

        producers.interrupt();
        consumer.interrupt();

        producers.join();
        consumer.join();

    }
}
