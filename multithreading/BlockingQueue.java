package multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue {
    public static class BlockingQueueLock
    {
        int capacity;
        Lock lock=new ReentrantLock();
        Condition notFull = lock.newCondition();
        Condition notEmpty = lock.newCondition();
        Queue<Integer> queue = new LinkedList<>();

        BlockingQueueLock(int n){
            this.capacity=n;
        }

        public void enqueue(int item){
            lock.lock();
            try{
                while (queue.size()==capacity) notFull.await();
                queue.add(item);
                System.out.println("Enqueue"+ " "+ item);
                notEmpty.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
        public int dequeu(){
            lock.lock();
            try{
                while (queue.isEmpty()) notEmpty.await();
                Integer dequeued = queue.remove();
                System.out.println("Dequeued"+" "+dequeued);
                notFull.signalAll();
                return dequeued;
            }
            catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        BlockingQueueLock blockingQueueLock = new BlockingQueueLock(3);
        Thread produced = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                blockingQueueLock.enqueue(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread consume = new Thread(() -> {
            for(int i=0;i<10;i++){
                int dequeu = blockingQueueLock.dequeu();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        produced.start();
        consume.start();
    }
}
