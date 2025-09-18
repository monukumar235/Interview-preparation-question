package MachineLevelCode.RateLimiter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LoggerService {
    public static class LoggerProducer extends Thread{
        public final BlockingQueue<LocalDateTime> queue;
        public LoggerProducer(BlockingQueue<LocalDateTime> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    LocalDateTime now = LocalDateTime.now();
                    queue.put(now);
                    System.out.println("Produced" + " "+ now.toString());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class ConsumerQA extends Thread{
        public final BlockingQueue<LocalDateTime> queue;

        public ConsumerQA(BlockingQueue<LocalDateTime> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    LocalDateTime localDate = queue.take();
                    System.out.println("Consumed"+ " "+localDate);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void main(String[] args) {
        BlockingQueue<LocalDateTime> queue = new ArrayBlockingQueue<>(10);
        new LoggerProducer(queue).start();
        new ConsumerQA(queue).start();
    }
}
