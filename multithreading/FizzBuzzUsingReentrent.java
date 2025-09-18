package multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzzUsingReentrent {
    static class FizzBuzz{
        int n=1;
        int max =15;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void printNumber(){
            while (n<=max){
               lock.lock();
               try{
                   while (n%3==0 || n%5==0){
                       condition.await();
                   }
                   System.out.println(n);
                   n++;
                   condition.signalAll();
               }
               catch (Exception e){
                   Thread.currentThread().interrupt();
               }
               finally {
                   lock.unlock();
               }
            }
        }
        public void printFuzz(){
            while (n<=max){
                lock.lock();
                try{
                    while (!(n%3==0 && n%5!=0)) condition.await();
                    System.out.println("Fizz");
                    n++;
                    condition.signalAll();
                }catch (Exception e){
                    Thread.currentThread().interrupt();
                }
                finally {
                    lock.unlock();
                }
            }
        }
        public void printBuzz(){
            while (n<=max)
            {
                lock.lock();
                try{
                    while (!(n%3!=0 && n%5==0))
                    {
                        condition.await();
                    }
                    System.out.println("Buzz");
                    n++;
                    condition.signalAll();
                }catch (Exception e){
                    Thread.currentThread().interrupt();
                }
                finally {
                    lock.unlock();
                }
            }
        }
        public void printFizzBuzz(){
            while (n<=max)
            {
                lock.lock();
                try{
                   while (!(n%3==0 && n%5==0))
                   {
                       condition.await();
                   }
                    System.out.println("FizzBuzz");
                   n++;
                   condition.signalAll();
                }
                catch (Exception e){
                    Thread.currentThread().interrupt();
                }
                finally {
                    lock.unlock();
                }
            }
        }
    }
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();

        Thread t1 = new Thread(()->{
            fizzBuzz.printNumber();
        });
        Thread t2 = new Thread(()->{
            fizzBuzz.printBuzz();
        });
        Thread t3 = new Thread(()->{
            fizzBuzz.printFuzz();
        });
        Thread t4 = new Thread(()->{
            fizzBuzz.printFizzBuzz();
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
