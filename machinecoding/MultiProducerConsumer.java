package machinecoding;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class MultiProducerConsumer {
    public static class MessageQueue{
        private final BlockingQueue<Integer> queue;
        MessageQueue(int cap){
            this.queue = new ArrayBlockingQueue<>(cap);
        }
        public void produces(int val,int producesId) throws InterruptedException {
            queue.put(val);
            System.out.println("Producer - " +producesId+ " Produced "+ val);
        }
        public int consumer(int consumerId) throws InterruptedException {
            Integer val = queue.take();
            System.out.println("Consumer- "+consumerId+" consumed "+val);
            return val;
        }
    }
    public static class Producers extends Thread{
        private final MessageQueue messageQueue;
        private int producerId;
        Producers(MessageQueue messageQueue,int producersId){
            this.messageQueue=messageQueue;
            this.producerId=producersId;
        }

        @Override
        public void run() {
            try{
                int val=0;
                while (true){
                    messageQueue.produces(val++,producerId);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500,1000));
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    public static class Consumer extends Thread{
        private final MessageQueue queue;
        private int consumerId;

        Consumer(MessageQueue messageQueue,int consumerId){
            this.consumerId=consumerId;
            this.queue=messageQueue;
        }

        @Override
        public void run() {
            try{
                while (true){
                    queue.consumer(consumerId);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500,1000));
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(3);

        int noOfConsumer =3;
        int noOfPublisher =2;

        for (int i=0;i<noOfPublisher;i++){
            new Producers(messageQueue,i).start();
        }
        for(int i=0;i<noOfConsumer;i++){
            new Consumer(messageQueue,i).start();
        }

    }
}
