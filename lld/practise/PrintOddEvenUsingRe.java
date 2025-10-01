package lld.practise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintOddEvenUsingRe {
    public static class print{
        int n=1;
        int max =10;
        ReentrantLock lock = new ReentrantLock();
        Condition oddTurn = lock.newCondition();
        Condition evenTurn = lock.newCondition();

        public void printEven()
        {
            lock.lock();
            try{
                while (n<=max){
                    if(n%2!=0){
                        evenTurn.await();
                    }else{
                        System.out.println(Thread.currentThread().getName()+" "+n);
                        n++;
                        oddTurn.signal();
                    }
                }
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }
            finally {
                lock.unlock();
            }
        }
        public void printOdd(){
            lock.lock();
            try{
                while (n<=max){
                    if(n%2==0){
                        oddTurn.await();
                    }else{
                        System.out.println(Thread.currentThread().getName()+" "+n);
                        n++;
                        evenTurn.signal();
                    }
                }
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }
            finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        print p = new print();
        Thread even = new Thread(()->{
            p.printEven();
        },"even");

        Thread odd = new Thread(()->{
            p.printOdd();
        },"odd");

        even.start();
        odd.start();
    }
}
