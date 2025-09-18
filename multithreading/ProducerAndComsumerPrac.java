package multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerAndComsumerPrac {
    static class ProdAndCon{
        Queue<Integer> queue = new LinkedList<>();
        int cap =5;

        public synchronized void producer(int item) throws InterruptedException {
            while (queue.size()==cap)
            {
                wait();
            }
            queue.add(item);
            System.out.println(Thread.currentThread().getName()+ "produced number: "+item);
            notifyAll();
        }
        public synchronized void consumed() throws InterruptedException {
            while (queue.isEmpty()){
                wait();
            }
            Integer item = queue.poll();
            System.out.println(Thread.currentThread().getName()+ " consumed item "+item);
            notifyAll();
        }
    }
    public static void main(String[] args) {
        ProdAndCon prodAndCon = new ProdAndCon();

        Thread t1 = new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    prodAndCon.producer(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"producerThread");

        Thread t2 = new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    prodAndCon.consumed();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"consumerThread");
        t1.start();
        t2.start();

    }
}
