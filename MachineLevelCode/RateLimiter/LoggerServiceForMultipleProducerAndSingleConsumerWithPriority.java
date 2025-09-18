package MachineLevelCode.RateLimiter;

import java.time.LocalDateTime;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoggerServiceForMultipleProducerAndSingleConsumerWithPriority {
    public static class LoggerMessage implements Comparable<LoggerMessage>  {
        String mess;
        String producer;
        LocalDateTime localDateTime;
        int priority;

        public LoggerMessage(String mess, String producer, int priority) {
            this.mess = mess;
            this.producer = producer;
            this.localDateTime = LocalDateTime.now();
            this.priority = priority;
        }
        @Override
        public String toString() {
            return "["+ producer +"]" + localDateTime +mess + "Priority" + priority;
        }

        @Override
        public int compareTo(LoggerMessage o) {
            // Lower number means higher priority
            return Integer.compare(this.priority, o.priority);
        }
    }

    public static class Producer extends Thread{
        public final PriorityBlockingQueue<LoggerMessage> queue;
        public String name;
        AtomicBoolean atomicBoolean;
        int priority;
        public Producer(PriorityBlockingQueue<LoggerMessage> queue,String name,AtomicBoolean atomicBoolean,int priority) {
            this.queue = queue;
            this.name=name;
            this.atomicBoolean=atomicBoolean;
            this.priority=priority;
        }

        @Override
        public void run() {
           int count=0;
            while (atomicBoolean.get()){
                try {
                    queue.put(new LoggerMessage("#~"+count++,name,priority));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class comsumer extends Thread{
       public final PriorityBlockingQueue<LoggerMessage>queue;
       public AtomicBoolean atomicBoolean;
        public comsumer(PriorityBlockingQueue<LoggerMessage> queue,AtomicBoolean atomicBoolean) {
            this.queue = queue;
            this.atomicBoolean=atomicBoolean;
        }

        @Override
        public void run() {
            while (atomicBoolean.get() || !queue.isEmpty()){
                try {
                    LoggerMessage take = queue.take();
                    System.out.println("Consumed" + take);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<LoggerMessage> queue =new PriorityBlockingQueue<>(10);
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        Producer producer = new Producer(queue,"producer-1,",atomicBoolean,Thread.MAX_PRIORITY);
        Producer producer1 = new Producer(queue,"producer-2",atomicBoolean,Thread.MIN_PRIORITY);
        Producer producer2 = new Producer(queue,"producer-3",atomicBoolean,Thread.NORM_PRIORITY);

        comsumer comsumer = new comsumer(queue,atomicBoolean);

        producer.start();
        producer1.start();
        producer2.start();
        comsumer.start();

        Thread.sleep(5000);
        atomicBoolean.set(false);
        producer.interrupt();
        producer1.interrupt();
        producer2.interrupt();
        comsumer.interrupt();

    }
}
