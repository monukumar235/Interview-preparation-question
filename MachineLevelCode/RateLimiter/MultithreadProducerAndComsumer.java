package MachineLevelCode.RateLimiter;

import java.util.LinkedList;
import java.util.Queue;

public class MultithreadProducerAndComsumer {
    static class Buffer {
        public final int capacity = 5;
        Queue<Integer> queue = new LinkedList<>();

        public synchronized void produce(int value) throws InterruptedException {
            while (queue.size() == capacity) {
                wait();
            }
            queue.add(value);
            System.out.println("Produced" + "" +value++);
            notify();
        }

        public synchronized int comsumed() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            Integer ans = queue.poll();
            System.out.println("consumed" + " " + ans);
            notify();
            return ans;
        }
    }

    static class Producer extends Thread{
        public final Buffer buffer;
        Producer(Buffer buffer) {
            this.buffer = buffer;
        }
        @Override
        public void run() {
          int value=0;
          while (true){
              try {
                  buffer.produce(value++);
                  Thread.sleep(100);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
        }
    }

    static class Consumer extends Thread{
        public final Buffer buffer;

        Consumer(Buffer buffer) {
            this.buffer = buffer;
        }
        @Override
        public void run() {
            while (true)
            {
                try {
                    buffer.comsumed();
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new Producer(buffer).start();
        new Consumer(buffer).start();
    }
}
