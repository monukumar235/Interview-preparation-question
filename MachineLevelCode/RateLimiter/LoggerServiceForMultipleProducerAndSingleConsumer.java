package MachineLevelCode.RateLimiter;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoggerServiceForMultipleProducerAndSingleConsumer {
    public static class LogerMessage
    {
        String producer;
        LocalDateTime localDateTime;
        String mes;
        public LogerMessage(String producer, String mes) {
            this.producer = producer;
            this.localDateTime = LocalDateTime.now();
            this.mes = mes;
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        public String getMes() {
            return mes;
        }

        public void setMes(String mes) {
            this.mes = mes;
        }


    }

    public static class Producer extends Thread{
        public final BlockingQueue<LogerMessage> queue;
        String name;
        AtomicBoolean atomicBoolean;

        public Producer(BlockingQueue<LogerMessage> queue,String name,AtomicBoolean atomicBoolean) {
            this.queue = queue;
            this.name =name;
            this.atomicBoolean=atomicBoolean;
        }

        @Override
        public void run() {
            int count=0;
            while (atomicBoolean.get()){
                try {
                    queue.put(new LogerMessage(name,"#~"+count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class comsumer extends Thread{
       public final BlockingQueue<LogerMessage> queue;
       AtomicBoolean atomicBoolean;
        public comsumer(BlockingQueue<LogerMessage> queue,AtomicBoolean atomicBoolean) {
            this.queue = queue;
            this.atomicBoolean=atomicBoolean;
        }

        @Override
        public void run() {
            while (atomicBoolean.get() || !queue.isEmpty()){
                try {
                    LogerMessage take = queue.take();
                    System.out.println(take.getProducer() + " "+take.getMes()+" "+take.getLocalDateTime());
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<LogerMessage> queue = new ArrayBlockingQueue<>(10);
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        Producer p1 =new Producer(queue,"producer-1",atomicBoolean);
        Producer p2 =new Producer(queue,"producer-2",atomicBoolean);
        Producer p3=new Producer(queue,"producer-3",atomicBoolean);

        comsumer c1 = new comsumer(queue,atomicBoolean);

        p1.start();
        p2.start();
        p3.start();
        c1.start();

        Thread.sleep(5000);

        atomicBoolean.set(false);
        p1.interrupt();
        p2.interrupt();
        p3.interrupt();
        c1.interrupt();
        p1.join();
        p2.join();
        p3.join();
        c1.join();

    }
}
