package multithreading;
import java.util.LinkedList;
import java.util.Queue;

public class ProduceAndConsumer {
    public static class ProducerAndConsumers
    {
        Queue<Integer> queue = new LinkedList<>();
        int capacity =5;

        public synchronized void produces(int item) throws InterruptedException {
            while(queue.size()==capacity){
                wait();
            }
            queue.add(item);
            System.out.println("Produced" + " "+ item);
            notifyAll();
        }

        public synchronized void consume() throws InterruptedException {
            while (queue.isEmpty()) wait();
            Integer item = queue.poll();
            System.out.println("consumed"+" "+item);
            notifyAll();
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumers producerAndConsumers = new ProducerAndConsumers();
        Thread produced =new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    producerAndConsumers.produces(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread consume = new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    producerAndConsumers.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        produced.start();
        consume.start();
    }
}
